package com.rest.springapp.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.entities.Cashback;
import com.rest.springapp.repository.CashbackRepository;

@Service
public class CashbackService {
    private final CashbackRepository cashbackRepository;

    public CashbackService(CashbackRepository cashbackRepository) {
        this.cashbackRepository = cashbackRepository;
    }

    public Cashback createCashback(Cashback cashback) {
        return cashbackRepository.save(cashback);
    }

    public Cashback getCashbackById(Long id) {
        return cashbackRepository.findCashbackById(id)
                .orElseThrow(() -> new RuntimeException("Cashback not found"));
    }

    public Page<Cashback> getAllCashbacks(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return cashbackRepository.findAll(pageable);
    }

    public Page<Cashback> getCashbacksByDescription(String description, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return cashbackRepository.findCashbacksByDescription(description, pageable);
    }

    public Cashback updateCashback(Long id, Cashback cashbackDetails) {
        Cashback cashback = getCashbackById(id);
        cashback.setDescription(cashbackDetails.getDescription());
        cashback.setAmount(cashbackDetails.getAmount());
        return cashbackRepository.save(cashback);
    }

    @Transactional
    public void updateCashbackAmount(Long id, Double amount) {
        cashbackRepository.updateCashbackAmountById(id, amount);
    }

    @Transactional
    public void updateCashbackDescription(Long id, String description) {
        cashbackRepository.updateCashbackDescriptionById(id, description);
    }

    public void deleteCashback(Long id) {
        cashbackRepository.deleteById(id);
    }

    @Transactional
    public void deleteCashbackByAmount(Double amount) {
        cashbackRepository.deleteCashbackByAmount(amount);
    }

    @Transactional
    public void deleteCashbackByDescription(String description) {
        cashbackRepository.deleteCashbackByDescription(description);
    }
}
