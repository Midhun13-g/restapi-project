package com.rest.springapp.repository;

import com.rest.springapp.entities.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Find admin by email
    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    Admin findByEmail(String email);

    // Find admins by role (paginated)
    @Query("SELECT a FROM Admin a WHERE a.role = :role")
    Page<Admin> findByRole(String role, Pageable pageable);

    // Find admins by name (partial match)
    @Query("SELECT a FROM Admin a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Admin> findByNameContaining(String name);
}
