package com.rest.springapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.springapp.Service.RetailerService;
import com.rest.springapp.entities.Retailer;

@RestController
@RequestMapping("/api/retailers")
@CrossOrigin(origins = "*") // Allow cross-origin requests
public class RetailerController {
    private final RetailerService retailerService;

    public RetailerController(RetailerService retailerService) {
        this.retailerService = retailerService;
    }

    // ✅ Create Retailer
    @PostMapping
    public ResponseEntity<Retailer> createRetailer(@RequestBody Retailer retailer) {
        return ResponseEntity.ok(retailerService.createRetailer(retailer));
    }

    // ✅ Get Retailer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Retailer> getRetailerById(@PathVariable Long id) {
        return ResponseEntity.ok(retailerService.getRetailerById(id));
    }

    // ✅ Get All Retailers with Pagination & Sorting
    @GetMapping
    public ResponseEntity<Page<Retailer>> getAllRetailers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Page<Retailer> retailerPage = retailerService.getAllRetailers(PageRequest.of(page, size, sort));
        return ResponseEntity.ok(retailerPage);
    }

    // ✅ Search Retailers by Name (Using JPQL)
    @GetMapping("/search")
    public ResponseEntity<Page<Retailer>> searchRetailersByName(
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Page<Retailer> retailerPage = retailerService.searchRetailersByName(name, PageRequest.of(page, size, sort));
        return ResponseEntity.ok(retailerPage);
    }

    // ✅ Update Retailer
    @PutMapping("/{id}")
    public ResponseEntity<Retailer> updateRetailer(@PathVariable Long id, @RequestBody Retailer retailer) {
        return ResponseEntity.ok(retailerService.updateRetailer(id, retailer));
    }

    // ✅ Delete Retailer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetailer(@PathVariable Long id) {
        retailerService.deleteRetailer(id);
        return ResponseEntity.noContent().build();
    }
}
