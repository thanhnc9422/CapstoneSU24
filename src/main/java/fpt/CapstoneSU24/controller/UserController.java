package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.B03.B03_GetDataGridDTO;
import fpt.CapstoneSU24.dto.B03.B03_RequestDTO;
import fpt.CapstoneSU24.dto.UserProfileDTO;
import fpt.CapstoneSU24.dto.payload.UpdateStatusUserRequest;
import fpt.CapstoneSU24.model.Role;
import fpt.CapstoneSU24.model.User;
import fpt.CapstoneSU24.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@RestController
@RequestMapping("/api/user")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/getDataToTable")
    public ResponseEntity<?> getUsersByEmail(@RequestBody B03_RequestDTO userRequestDTO) {
        return userService.getUsersByEmail(userRequestDTO);
    }


    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@Valid @RequestBody UpdateStatusUserRequest req) {
        return userService.updateStatus(req.getId(), req.getStatus());
    }


    @PostMapping("/lockUser")
    public Boolean lockUser(@RequestBody String req) {
        return userService.lockUser(req);
    }


    //Update Table
    @PutMapping("/updateUserDescriptions")
    public ResponseEntity<String> updateUserDescriptions(@RequestBody List<B03_GetDataGridDTO> userUpdateRequests) {
        return  userService.updateUserDescriptions(userUpdateRequests);
    }

    //get Role
    @GetMapping("/getRoleByUserId")
    public ResponseEntity<Role> getRoleByUserId(@RequestParam int userId) {
        return  userService.getRoleByUserId(userId);
    }

    @PostMapping("/getUserById")
    public ResponseEntity<UserProfileDTO> getUserByUserID(@RequestBody String req)  {
        return userService.getUserByUserID(req);
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/getContract")
    public ResponseEntity<?> generateDoc() {
        return userService.generateDoc();
    }

//    @PostMapping("/updateCertification")
//    public ResponseEntity<String> updateCertification(String otp) {
//        return userService.updateCertification(otp);
//    }

    @PutMapping("/updateAvatar")
    public ResponseEntity<String> updateAvatar(@RequestParam("file") MultipartFile file) {
        return userService.updateAvatar(file);
    }
}





