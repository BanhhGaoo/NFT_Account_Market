package com.example.demo.controller;

import com.example.demo.entity.GameAccount;
import com.example.demo.entity.Image;
import com.example.demo.service.GameAccountService;
import com.example.demo.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameAccountController {

	@Autowired
	private GameAccountService gameAccountService;
	
	@Autowired
    private ImageService imageService;

	@GetMapping("/products")
    public String getGameAccountsByGameName(@RequestParam(value = "gameName", required = false) String gameName, Model model,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size) {

		Pageable pageable = PageRequest.of(page, size);
	    Page<GameAccount> gameAccountsPage = gameAccountService.getFilteredAccounts(gameName, pageable);

	    List<GameAccount> gameAccounts = gameAccountsPage.getContent();

	    // Fetch images for each game account and set them
	    for (GameAccount account : gameAccounts) {
	        List<Image> images = imageService.getImagesByGameAccountId(account.getAccountId());
	        account.setImages(images); // Set images to the account
	    }

	    model.addAttribute("gameAccounts", gameAccounts);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", gameAccountsPage.getTotalPages());

	    return "products";
    }
	
	@GetMapping("/productDetail")
    public String productDetail(@RequestParam(value = "accountId") int accountId, Model model) {
        GameAccount gameAccount = gameAccountService.getAccountById(accountId);
        List<Image> images = imageService.getImagesByGameAccountId(accountId);
        gameAccount.setImages(images); // Set images to the account

        model.addAttribute("gameAccount", gameAccount);
        return "productDetails";
    }
	
	@GetMapping("/myAccount")
    public String getMyAccountPage(Model model) {
		return "myAccount";
    }

}
