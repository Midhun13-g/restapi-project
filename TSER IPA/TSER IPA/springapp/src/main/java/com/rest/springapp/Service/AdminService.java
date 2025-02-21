package com.rest.springapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.entities.Admin;
import com.rest.springapp.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Create an admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Get all admins (paginated & sorted)
    public Page<Admin> getAllAdmins(int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, org.springframework.data.domain.Sort.by(
                sortDirection.equalsIgnoreCase("desc") ? org.springframework.data.domain.Sort.Order.desc(sortBy)
                        : org.springframework.data.domain.Sort.Order.asc(sortBy)
        ));
        return adminRepository.findAll(pageable);
    }

    // Get admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    // Get admin by email
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    // Get admins by role (paginated)
    public Page<Admin> getAdminsByRole(String role, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return adminRepository.findByRole(role, pageable);
    }

    // Get admins by name (partial match)
    public List<Admin> getAdminsByName(String name) {
        return adminRepository.findByNameContaining(name);
    }

    // Update an admin
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin.setPhoneNumber(updatedAdmin.getPhoneNumber());
        existingAdmin.setRole(updatedAdmin.getRole());
        return adminRepository.save(existingAdmin);
    }

    // Delete an admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
