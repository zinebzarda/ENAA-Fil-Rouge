package com.filRouge.service;

import com.filRouge.model.Admin;
import com.filRouge.model.enums.Role;
import com.filRouge.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminUserIfNotExist() {
        String adminEmail = "admin@example.com";
        Optional<Admin> existingAdmin = adminRepository.findByEmail(adminEmail);

        if (existingAdmin.isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setEmail(adminEmail);
            admin.setRole(Role.ADMIN);
            admin.setPassword(passwordEncoder.encode("admin"));
            adminRepository.save(admin);
        }
    }
}
