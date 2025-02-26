package com.rest.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.springapp.Service.AdminService;
import com.rest.springapp.entities.Admin;


@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create an admin
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    // Get all admins (paginated & sorted)
    @GetMapping
    public ResponseEntity<Page<Admin>> getAllAdmins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return ResponseEntity.ok(adminService.getAllAdmins(page, size, sortBy, sortDirection));
    }

    // Get admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    // Get admin by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.getAdminByEmail(email));
    }

    // Get admins by role (paginated)
    @GetMapping("/role/{role}")
    public ResponseEntity<Page<Admin>> getAdminsByRole(
            @PathVariable String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(adminService.getAdminsByRole(role, page, size));
    }

    // Get admins by name (partial match)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Admin>> getAdminsByName(@PathVariable String name) {
        return ResponseEntity.ok(adminService.getAdminsByName(name));
    }

    // Update an admin (only update non-null fields)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        int updatedRows = adminService.updateAdmin(id, admin);
        if (updatedRows > 0) {
            return ResponseEntity.ok("Admin updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("No update made or admin not found.");
        }
    }

    // Delete an admin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

    // Delete an admin by email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteAdminByEmail(@PathVariable String email) {
        adminService.deleteAdminByEmail(email);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

    // Delete multiple admins by role
    @DeleteMapping("/role/{role}")
    public ResponseEntity<String> deleteAdminsByRole(@PathVariable String role) {
        adminService.deleteAdminsByRole(role);
        return ResponseEntity.ok("Admins with role '" + role + "' deleted successfully.");
    }
}
