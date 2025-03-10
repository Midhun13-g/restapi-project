package com.rest.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.springapp.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Get transactions by status
    @Query("SELECT t FROM Transaction t WHERE t.status = :status")
    List<Transaction> findByStatus(@Param("status") String status);

    // Get transactions greater than a certain amount
    @Query("SELECT t FROM Transaction t WHERE t.amount > :amount")
    List<Transaction> findTransactionsGreaterThan(@Param("amount") Double amount);
}
