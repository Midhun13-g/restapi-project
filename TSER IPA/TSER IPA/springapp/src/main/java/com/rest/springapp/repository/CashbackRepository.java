package com.rest.springapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.entities.Cashback;

public interface CashbackRepository extends JpaRepository<Cashback, Long> {

    @Query("SELECT c FROM Cashback c WHERE c.id = :id")
    Optional<Cashback> findCashbackById(@Param("id") Long id);

    @Query("SELECT c FROM Cashback c WHERE c.description LIKE %:description%")
    Page<Cashback> findCashbacksByDescription(@Param("description") String description, Pageable pageable);

    // Custom Update: Update cashback amount by ID
    @Modifying
    @Transactional
    @Query("UPDATE Cashback c SET c.amount = :amount WHERE c.id = :id")
    void updateCashbackAmountById(@Param("id") Long id, @Param("amount") Double amount);

    // Custom Update: Update cashback description by ID
    @Modifying
    @Transactional
    @Query("UPDATE Cashback c SET c.description = :description WHERE c.id = :id")
    void updateCashbackDescriptionById(@Param("id") Long id, @Param("description") String description);

    // Custom Delete: Delete cashback by amount
    @Modifying
    @Transactional
    @Query("DELETE FROM Cashback c WHERE c.amount = :amount")
    void deleteCashbackByAmount(@Param("amount") Double amount);

    // Custom Delete: Delete cashback by description
    @Modifying
    @Transactional
    @Query("DELETE FROM Cashback c WHERE c.description = :description")
    void deleteCashbackByDescription(@Param("description") String description);
}
