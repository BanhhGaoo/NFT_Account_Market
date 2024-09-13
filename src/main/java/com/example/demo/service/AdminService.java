package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	public boolean validateAdmin(String adminName, String password) {
	    Admin admin = adminRepository.findByAdminName(adminName);
	    return admin != null && admin.getPassword().equals(password);
	}
	
	public boolean usernameExists(String username) {
        return adminRepository.findByAdminName(username) != null;
    }

    public void saveUser(Admin admin) {
    	adminRepository.save(admin);
    }
    
    public Admin findAdminById(int id) {
    	return adminRepository.findById(id).orElse(null);
    }
    
    public Admin findByAdminName(String adminName) {
        return adminRepository.findByAdminName(adminName);
    }
}
