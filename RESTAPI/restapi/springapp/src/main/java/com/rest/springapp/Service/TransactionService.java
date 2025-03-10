package com.rest.springapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.entities.Transaction;
import com.rest.springapp.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // Updated to support pagination
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = getTransactionById(id);
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setStatus(transactionDetails.getStatus()); // Update only amount and status
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatus(status);
    }

    public List<Transaction> getTransactionsGreaterThan(Double amount) {
        return transactionRepository.findTransactionsGreaterThan(amount);
    }
}  // <<--- Correct closing brace
