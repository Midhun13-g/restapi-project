package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u")
    Page<User> findAllUsers(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.phoneNumber = :phoneNumber WHERE u.id = :id")
    void updateUser(@Param("id") Long id, @Param("name") String name, @Param("email") String email, @Param("phoneNumber") String phoneNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);
}
