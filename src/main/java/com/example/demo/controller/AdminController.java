package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.Image;
import com.example.demo.service.AdminService;
import com.example.demo.service.GameAccountService;
import com.example.demo.service.ImageService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	private GameAccountService gameAccountService;
	
	@Autowired
    private ImageService imageService;
	
	@GetMapping("/admin/{id}")
	public String getAdminDetails(@PathVariable int id, Model model) {
		Admin admin = adminService.findAdminById(id);
		model.addAttribute("admin", admin);
		return "adminDetails";
	}
	
	@GetMapping("/admin/{id}/accountManagement")
    public String accountManagement(Model model, 
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "9") int size) {
			Pageable pageable = PageRequest.of(page, size);
			Page<GameAccount> gameAccountsPage = gameAccountService.getFilteredAccounts(null, pageable);
			
			List<GameAccount> gameAccounts = gameAccountsPage.getContent();
			
			// Fetch images for each game account and set them
			for (GameAccount account : gameAccounts) {
			 List<Image> images = imageService.getImagesByGameAccountId(account.getAccountId());
			 account.setImages(images); // Set images to the account
			}
			
			model.addAttribute("gameAccounts", gameAccounts);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", gameAccountsPage.getTotalPages());
			
			return "accountManagement";
    }
}
