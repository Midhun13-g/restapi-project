package com.rest.springapp.controller;

import java.time.LocalDate;

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

import com.rest.springapp.Service.CouponService;
import com.rest.springapp.entities.Coupon;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // Create Coupon
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.createCoupon(coupon));
    }

    // Get Coupon by ID
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
        return ResponseEntity.ok(couponService.getCouponById(id));
    }

    // Get All Coupons
    @GetMapping
    public ResponseEntity<Page<Coupon>> getAllCoupons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(couponService.getAllCoupons(page, size, sortBy, sortDir));
    }

    // Search Coupons
    @GetMapping("/search")
    public ResponseEntity<Page<Coupon>> searchCoupons(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double discountPercentage,
            @RequestParam(required = false) LocalDate expirationDate,
            @RequestParam(required = false) Boolean redeemed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        return ResponseEntity.ok(couponService.searchCoupons(
                code, description, discountPercentage, expirationDate, redeemed, page, size, sortBy, sortDir));
    }

    // Update Coupon by ID
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Long id, @RequestBody Coupon couponDetails) {
        return ResponseEntity.ok(couponService.updateCoupon(id, couponDetails));
    }

    // Update Coupon by ID & Code
    @PutMapping("/{id}/code")
    public ResponseEntity<Void> updateCouponByIdAndCode(
            @PathVariable Long id, @RequestParam String code, @RequestBody Coupon couponDetails) {
        int rowsUpdated = couponService.updateCouponByIdAndCode(id, code, couponDetails);
        if (rowsUpdated > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delete Coupon by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }

    // Delete Coupon by Code
    @DeleteMapping("/code/{code}")
    public ResponseEntity<Void> deleteCouponByCode(@PathVariable String code) {
        couponService.deleteCouponByCode(code);
        return ResponseEntity.noContent().build();
    }
}
