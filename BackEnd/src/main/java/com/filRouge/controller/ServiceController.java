package com.filRouge.controller;

import com.filRouge.model.Prestataire;
import com.filRouge.model.Services;
import com.filRouge.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;


    @PostMapping("/add")
    public Services createService(@RequestBody Services service) {
        return serviceService.createService(service);
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
