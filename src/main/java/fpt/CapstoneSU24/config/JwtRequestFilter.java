package fpt.CapstoneSU24.config;

import java.io.IOException;


import fpt.CapstoneSU24.model.AuthToken;
import fpt.CapstoneSU24.model.User;
import fpt.CapstoneSU24.repository.AuthTokenRepository;
import fpt.CapstoneSU24.repository.UserRepository;
import fpt.CapstoneSU24.service.JwtService;
import fpt.CapstoneSU24.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Cookie");
        String username = null;
        String jwtToken = null;
        String pevJwtToken = null;
        AuthToken authToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("jwt=")) {
            jwtToken = requestTokenHeader.substring(4);
            try {
                username = jwtService.extractUsername(jwtToken);
//                check matching in database
                User currentUser = userRepository.findOneByEmail(username);
                authToken = authTokenRepository.findOneById(currentUser.getUserId());

                pevJwtToken = authToken.getJwtHash();

                if(jwtToken.equals(pevJwtToken)){
                    //generate new token and save in database
                    String newJwtToken = jwtService.generateToken(currentUser, currentUser);
                    //save in cookie
                    response.setHeader(HttpHeaders.SET_COOKIE, jwtTokenUtil.setResponseCookie(newJwtToken).toString());
                }else {
                    logger.warn("JWT Token does not matching");
                    //set all session is null
                    authToken.setJwtHash(null);
                    authTokenRepository.save(authToken);
                    response.setHeader(HttpHeaders.SET_COOKIE, jwtTokenUtil.setResponseCookie(null).toString());
                }
            } catch (IllegalArgumentException e) {
                //delete jwt in browser
                System.out.println("Unable to get JWT Token");
                response.setHeader(HttpHeaders.SET_COOKIE, jwtTokenUtil.setResponseCookie(null).toString());

            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                //delete jwt in browser
                String email = JwtService.getSubFromExpiredJWT(jwtToken);
                try {
                    authToken = authTokenRepository.findOneById(userRepository.findOneByEmail(email).getUserId());
                    authToken.setJwtHash(null);
                    authTokenRepository.save(authToken);
                }catch (Exception ex){
                    response.setHeader(HttpHeaders.SET_COOKIE, jwtTokenUtil.setResponseCookie(null).toString());
                }
                // set cookie í null
            }
            }else{
                logger.warn("JWT Token does not exist");
            }
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtToken.equals(pevJwtToken)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}