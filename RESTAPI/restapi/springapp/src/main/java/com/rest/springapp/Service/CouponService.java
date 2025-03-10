package com.rest.springapp.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.entities.Coupon;
import com.rest.springapp.repository.CouponRepository;

import jakarta.transaction.Transactional;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Page<Coupon> searchCoupons(
            String code, String description, Double discountPercentage,
            LocalDate expirationDate, Boolean redeemed,
            int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return couponRepository.searchCoupons(code, description, discountPercentage, expirationDate, redeemed, pageable);
    }

    public Coupon getCouponById(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    public Page<Coupon> getAllCoupons(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return couponRepository.findAll(pageable);
    }

    public Coupon updateCoupon(Long id, Coupon couponDetails) {
        Coupon coupon = getCouponById(id);
        coupon.setCode(couponDetails.getCode());
        coupon.setDescription(couponDetails.getDescription());
        coupon.setDiscountPercentage(couponDetails.getDiscountPercentage());
        coupon.setExpirationDate(couponDetails.getExpirationDate());
        coupon.setRedeemed(couponDetails.isRedeemed());
        return couponRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    @Transactional
    public int updateCouponByIdAndCode(Long id, String code, Coupon couponDetails) {
        return couponRepository.updateByIdAndCode(id, code, couponDetails.getDescription(),
                couponDetails.getDiscountPercentage(), couponDetails.getExpirationDate(), couponDetails.isRedeemed());
    }

    public void deleteCouponByCode(String code) {
        couponRepository.deleteByCode(code);
    }
}
