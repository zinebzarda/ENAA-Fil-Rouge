package com.filRouge.controller;

import com.filRouge.dto.ServiceDTO;
import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Prestataire;
import com.filRouge.model.Services;
import com.filRouge.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("Services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/createService")
    public ResponseEntity<Services> createServiceWithImages(
            @RequestPart("service") Services service,
            @RequestPart("attachments") List<MultipartFile> attachments,
            @AuthenticationPrincipal Prestataire prestataire) {

        try {
            Services createdService = serviceService.createServiceWithImages(service, attachments, prestataire);
            return new ResponseEntity<>(createdService, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<Services> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable Long id) {
        return serviceService.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public Services updateService(@PathVariable Long id, @RequestBody Services serviceDetails) {
        return serviceService.updateService(id, serviceDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }

    @GetMapping("/search")
    public List<Services> searchServices(@RequestParam String titre) {
        return serviceService.searchServices(titre);
    }
}
