package com.rest.springapp.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.entities.Retailer;
import com.rest.springapp.repository.RetailerRepository;

@Service
public class RetailerService {
    private final RetailerRepository retailerRepository;

    public RetailerService(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    public Retailer createRetailer(Retailer retailer) {
        return retailerRepository.save(retailer);
    }

    public Retailer getRetailerById(Long id) {
        return retailerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retailer not found"));
    }

    public Page<Retailer> getAllRetailers(Pageable pageable) {
        return retailerRepository.findAll(pageable);
    }

    // ✅ JPQL Search Method (Find retailers by name)
    public Page<Retailer> searchRetailersByName(String name, Pageable pageable) {
        return retailerRepository.searchByName(name, pageable);
    }

    public Retailer updateRetailer(Long id, Retailer retailerDetails) {
        Retailer retailer = getRetailerById(id);
        retailer.setName(retailerDetails.getName());
        return retailerRepository.save(retailer);
    }

    public void deleteRetailer(Long id) {
        retailerRepository.deleteById(id);
    }
}
