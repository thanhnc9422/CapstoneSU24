package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.model.Role;
import fpt.CapstoneSU24.repository.RoleRepository;
import fpt.CapstoneSU24.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getAllRole")
    public ResponseEntity<?> getAllUser() {
        return roleService.getAllUsers();
    }
}
