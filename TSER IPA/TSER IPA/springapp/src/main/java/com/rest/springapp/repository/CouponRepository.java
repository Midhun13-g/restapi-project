package com.rest.springapp.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE " +
            "(:code IS NULL OR c.code LIKE %:code%) AND " +
            "(:description IS NULL OR c.description LIKE %:description%) AND " +
            "(:discountPercentage IS NULL OR c.discountPercentage = :discountPercentage) AND " +
            "(:expirationDate IS NULL OR c.expirationDate = :expirationDate) AND " +
            "(:redeemed IS NULL OR c.redeemed = :redeemed)")
    Page<Coupon> searchCoupons(
            String code,
            String description,
            Double discountPercentage,
            LocalDate expirationDate,
            Boolean redeemed,
            Pageable pageable);
}
