package com.rest.springapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.entities.Retailer;
import com.rest.springapp.repository.RetailerRepository;

@Service
public class RetailerService {
    
    @Autowired
    private RetailerRepository retailerRepository;

    // Create a new retailer
    public Retailer createRetailer(Retailer retailer) {
        return retailerRepository.save(retailer);
    }

    // Get a retailer by ID
    public Retailer getRetailerById(Long id) {
        return retailerRepository.findById(id).orElse(null);
    }

    // Get all retailers
    public List<Retailer> getAllRetailers() {
        return retailerRepository.findAll();
    }

    // Get retailers with pagination and sorting
    public Page<Retailer> getRetailersWithPaginationAndSorting(int page, int size, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return retailerRepository.findAll(PageRequest.of(page, size, sort));
    }

    // Get retailers by category
    public List<Retailer> getRetailersByCategory(String category) {
        return retailerRepository.findByCategoryJPQL(category);
    }

    // Get retailers by name
    public List<Retailer> getRetailersByName(String name) {
        return retailerRepository.findByName(name);
    }

    // Update retailer by ID
    public Retailer updateRetailerById(Long id, Retailer retailerDetails) {
        Optional<Retailer> optionalRetailer = retailerRepository.findById(id);
        if (optionalRetailer.isPresent()) {
            Retailer retailer = optionalRetailer.get();
            retailer.setName(retailerDetails.getName());
            retailer.setEmail(retailerDetails.getEmail());
            retailer.setPhoneNumber(retailerDetails.getPhoneNumber());
            retailer.setAddress(retailerDetails.getAddress());
            retailer.setCategory(retailerDetails.getCategory());
            return retailerRepository.save(retailer);
        }
        return null;
    }

    // Update retailer by name
    @Transactional
    public void updateRetailerByName(String name, Retailer retailerDetails) {
        retailerRepository.updateRetailerByName(name, retailerDetails.getEmail(), retailerDetails.getPhoneNumber(), retailerDetails.getAddress(), retailerDetails.getCategory());
    }

    // Update retailer by category
    @Transactional
    public void updateRetailerByCategory(String category, String newCategory) {
        List<Retailer> retailers = retailerRepository.findByCategory(category);
        for (Retailer retailer : retailers) {
            retailer.setCategory(newCategory);
            retailerRepository.save(retailer);
        }
    }
    // Delete retailer by ID
    public void deleteRetailer(Long id) {
        retailerRepository.deleteById(id);
    }

    // Delete retailer by name
    @Transactional
    public void deleteRetailerByName(String name) {
        retailerRepository.deleteByName(name);
    }

    // Delete retailer by category
    @Transactional
    public void deleteRetailerByCategory(String category) {
        retailerRepository.deleteByCategory(category);
    }
}
