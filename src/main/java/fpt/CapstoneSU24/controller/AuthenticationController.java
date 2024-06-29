package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.ChangePasswordDto;
import fpt.CapstoneSU24.dto.payload.ForgotPasswordRequest;
import fpt.CapstoneSU24.dto.payload.RegisterRequest;
import fpt.CapstoneSU24.service.AuthenticationService;
import fpt.CapstoneSU24.service.JwtService;
import fpt.CapstoneSU24.dto.payload.LoginRequest;
import fpt.CapstoneSU24.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody RegisterRequest registerRequest) {
   return authenticationService.signup(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return authenticationService.login(loginRequest, response);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        return authenticationService.logout(response);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
     return authenticationService.changePassword(changePasswordDto);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgetPassword(@Valid @RequestBody ForgotPasswordRequest req) {
        return authenticationService.forgetPassword(req);
    }
}
