package com.rest.springapp.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import com.rest.springapp.Service.TransactionService;
import com.rest.springapp.entities.Transaction;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    // Updated to support pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<Transaction> transactionPage = transactionService.getAllTransactions(PageRequest.of(page, size, Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(sortDir)))));
        return ResponseEntity.ok(transactionPage);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Transaction>> getTransactionsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(transactionService.getTransactionsByStatus(status));
    }

    @GetMapping("/greaterThan")
    public ResponseEntity<List<Transaction>> getTransactionsGreaterThan(@RequestParam Double amount) {
        return ResponseEntity.ok(transactionService.getTransactionsGreaterThan(amount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
