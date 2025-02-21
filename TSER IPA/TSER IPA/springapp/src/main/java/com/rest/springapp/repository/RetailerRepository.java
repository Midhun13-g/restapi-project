package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest.springapp.entities.Retailer;

public interface RetailerRepository extends JpaRepository<Retailer, Long> {
    
    // JPQL Query to search retailers by name (case insensitive)
    @Query("SELECT r FROM Retailer r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Retailer> searchByName(@Param("name") String name, Pageable pageable);
}
