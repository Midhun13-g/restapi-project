package com.rest.springapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest.springapp.entities.Cashback;

public interface CashbackRepository extends JpaRepository<Cashback, Long> {

    @Query("SELECT c FROM Cashback c WHERE c.id = :id")
    Optional<Cashback> findCashbackById(@Param("id") Long id);

    @Query("SELECT c FROM Cashback c WHERE c.description LIKE %:description%")
    Page<Cashback> findCashbacksByDescription(@Param("description") String description, Pageable pageable);
}
