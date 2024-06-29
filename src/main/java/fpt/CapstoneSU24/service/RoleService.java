package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.model.Role;
import fpt.CapstoneSU24.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> getAllUsers() {
        try {
            List<Role> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No roles found");
            }
            return ResponseEntity.ok(roles);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the roles: " + ex.getMessage());
        }
    }
}
