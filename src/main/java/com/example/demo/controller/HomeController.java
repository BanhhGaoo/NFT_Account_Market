package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.GameAccount;
import com.example.demo.entity.Image;
import com.example.demo.service.GameAccountService;
import com.example.demo.service.ImageService;

import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private GameAccountService gameAccountService;
	
	@Autowired
    private ImageService imageService;
    
    // localhost:9010/
    @GetMapping("/")
    public String home(Model model, 
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
			
			return "index";
    }
}