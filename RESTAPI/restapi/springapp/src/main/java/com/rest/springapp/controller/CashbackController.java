package com.rest.springapp.controller;

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

import com.rest.springapp.Service.CashbackService;
import com.rest.springapp.entities.Cashback;

@RestController
@RequestMapping("/api/cashbacks")
public class CashbackController {
    private final CashbackService cashbackService;

    public CashbackController(CashbackService cashbackService) {
        this.cashbackService = cashbackService;
    }

    @PostMapping
    public ResponseEntity<Cashback> createCashback(@RequestBody Cashback cashback) {
        return ResponseEntity.ok(cashbackService.createCashback(cashback));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cashback> getCashbackById(@PathVariable Long id) {
        return ResponseEntity.ok(cashbackService.getCashbackById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Cashback>> getAllCashbacks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "description") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(cashbackService.getAllCashbacks(page, size, sortBy, direction));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Cashback>> getCashbacksByDescription(
            @RequestParam String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "description") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(cashbackService.getCashbacksByDescription(description, page, size, sortBy, direction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cashback> updateCashback(@PathVariable Long id, @RequestBody Cashback cashback) {
        return ResponseEntity.ok(cashbackService.updateCashback(id, cashback));
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<Void> updateCashbackAmount(@PathVariable Long id, @RequestParam Double amount) {
        cashbackService.updateCashbackAmount(id, amount);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<Void> updateCashbackDescription(@PathVariable Long id, @RequestParam String description) {
        cashbackService.updateCashbackDescription(id, description);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCashback(@PathVariable Long id) {
        cashbackService.deleteCashback(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-by-amount")
    public ResponseEntity<Void> deleteCashbackByAmount(@RequestParam Double amount) {
        cashbackService.deleteCashbackByAmount(amount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-by-description")
    public ResponseEntity<Void> deleteCashbackByDescription(@RequestParam String description) {
        cashbackService.deleteCashbackByDescription(description);
        return ResponseEntity.noContent().build();
    }
}
