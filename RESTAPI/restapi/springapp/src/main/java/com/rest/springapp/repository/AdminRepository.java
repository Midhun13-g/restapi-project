package com.rest.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.entities.Admin;


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

    // Update an admin with dynamic fields
    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET " +
           "a.name = COALESCE(:name, a.name), " +
           "a.email = COALESCE(:email, a.email), " +
           "a.phoneNumber = COALESCE(:phoneNumber, a.phoneNumber), " +
           "a.role = COALESCE(:role, a.role) " +
           "WHERE a.id = :id")
    int updateAdmin(Long id, String name, String email, String phoneNumber, String role);

    // Delete an admin by ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Admin a WHERE a.id = :id")
    int deleteAdminById(Long id);

    // Delete an admin by email
    @Modifying
    @Transactional
    @Query("DELETE FROM Admin a WHERE a.email = :email")
    int deleteAdminByEmail(String email);

    // Delete multiple admins by role
    @Modifying
    @Transactional
    @Query("DELETE FROM Admin a WHERE a.role = :role")
    int deleteAdminsByRole(String role);
}
