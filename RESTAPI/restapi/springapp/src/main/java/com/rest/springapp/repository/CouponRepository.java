package com.rest.springapp.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.entities.Coupon;

import jakarta.transaction.Transactional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    // Search Coupons with multiple filters + pagination
    @Query("SELECT c FROM Coupon c WHERE " +
            "(:code IS NULL OR c.code LIKE %:code%) AND " +
            "(:description IS NULL OR c.description LIKE %:description%) AND " +
            "(:discountPercentage IS NULL OR c.discountPercentage = :discountPercentage) AND " +
            "(:expirationDate IS NULL OR c.expirationDate = :expirationDate) AND " +
            "(:redeemed IS NULL OR c.redeemed = :redeemed)")
    Page<Coupon> searchCoupons(String code, String description, Double discountPercentage, 
                               LocalDate expirationDate, Boolean redeemed, Pageable pageable);

    // Find by Code & Discount Percentage
    Optional<Coupon> findByCode(String code);

    // Delete by Code
    @Transactional
    void deleteByCode(String code);

    // ✅ FIXED: Update Coupon by ID & Code
    @Modifying
    @Transactional
    @Query("UPDATE Coupon c SET c.description = :description, c.discountPercentage = :discountPercentage, " +
           "c.expirationDate = :expirationDate, c.redeemed = :redeemed WHERE c.id = :id AND c.code = :code")
    int updateByIdAndCode(Long id, String code, String description, Double discountPercentage, 
                          LocalDate expirationDate, Boolean redeemed);
}
