package com.rest.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.entities.Retailer;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {
    @Query("SELECT r FROM Retailer r WHERE r.category = ?1")
    List<Retailer> findByCategoryJPQL(String category);
    List<Retailer> findByCategory(String category);
    List<Retailer> findByName(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Retailer r WHERE r.name = ?1")
    void deleteByName(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Retailer r WHERE r.category = ?1")
    void deleteByCategory(String category);

    @Transactional
    @Modifying
    @Query("UPDATE Retailer r SET r.email = ?2, r.phoneNumber = ?3, r.address = ?4, r.category = ?5 WHERE r.name = ?1")
    void updateRetailerByName(String name, String email, String phoneNumber, String address, String category);

    @Transactional
    @Modifying
    @Query("UPDATE Retailer r SET r.category = :newCategory WHERE r.category = :category")
    void updateRetailerByCategory(@Param("category") String category, @Param("newCategory") String newCategory);
}

