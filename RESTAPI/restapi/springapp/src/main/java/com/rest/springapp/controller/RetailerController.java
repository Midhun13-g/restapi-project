package com.rest.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.rest.springapp.Service.RetailerService;
import com.rest.springapp.entities.Retailer;

@RestController
@RequestMapping("/api/retailers")
public class RetailerController {
    
    @Autowired
    private RetailerService retailerService;

    @PostMapping
    public ResponseEntity<Retailer> createRetailer(@RequestBody Retailer retailer) {
        return ResponseEntity.ok(retailerService.createRetailer(retailer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retailer> getRetailerById(@PathVariable Long id) {
        Retailer retailer = retailerService.getRetailerById(id);
        return retailer != null ? ResponseEntity.ok(retailer) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Retailer>> getAllRetailers() {
        return ResponseEntity.ok(retailerService.getAllRetailers());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Retailer>> getRetailersWithPaginationAndSorting(@RequestParam int page, @RequestParam int size,
                                                                               @RequestParam String sortBy, @RequestParam String order) {
        return ResponseEntity.ok(retailerService.getRetailersWithPaginationAndSorting(page, size, sortBy, order));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Retailer>> getRetailersByCategory(@PathVariable String category) {
        return ResponseEntity.ok(retailerService.getRetailersByCategory(category));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Retailer>> getRetailersByName(@PathVariable String name) {
        return ResponseEntity.ok(retailerService.getRetailersByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Retailer> updateRetailerById(@PathVariable Long id, @RequestBody Retailer retailer) {
        Retailer updatedRetailer = retailerService.updateRetailerById(id, retailer);
        return updatedRetailer != null ? ResponseEntity.ok(updatedRetailer) : ResponseEntity.notFound().build();
    }

    @PutMapping("/name/{name}")
    public ResponseEntity<Void> updateRetailerByName(@PathVariable String name, @RequestBody Retailer retailer) {
        retailerService.updateRetailerByName(name, retailer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/category/{category}")
    public ResponseEntity<Void> updateRetailerByCategory(@PathVariable String category, @RequestBody String newCategory) {
    retailerService.updateRetailerByCategory(category, newCategory);
    return ResponseEntity.ok().build();
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetailerById(@PathVariable Long id) {
        retailerService.deleteRetailer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteRetailerByName(@PathVariable String name) {
        retailerService.deleteRetailerByName(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/category/{category}")
    public ResponseEntity<Void> deleteRetailerByCategory(@PathVariable String category) {
        retailerService.deleteRetailerByCategory(category);
        return ResponseEntity.noContent().build();
    }
}