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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.GameAccount;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.ImageService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private ImageService imageService;
	
	@Autowired
    private OrderService orderService;

	@GetMapping("/user/{id}")
	public String getUserDetails(@PathVariable int id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user",user);
		return "userDetails";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int userId, @RequestParam("publicKey") String publicKey, 
	                         @RequestParam("email") String email, @RequestParam("password") String password,
	                         @RequestParam(value = "twoFactorEnabled", required = false) String twoFactorEnabled, Model model) {
	    User user = userService.findUserById(userId);
	    if (user != null) {
	        user.setPublicKey(publicKey);
	        user.setEmail(email);
	        user.setPassword(password); // Cần xem xét mã hóa mật khẩu trước khi lưu
	        user.setTwoFactorEnabled(twoFactorEnabled != null);
	        userService.saveUser(user);
	        model.addAttribute("msg", "Cập nhật thành công!");
	    }
	    return "redirect:/user/" + userId;
	}

	@GetMapping("/user/{id}/myAccount")
	public String myAccount(Model model, @PathVariable int id,
	        @RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "9") int size) {

	    User user = userService.findUserById(id);
	    Pageable pageable = PageRequest.of(page, size);
	    Page<GameAccount> paidAccountsPage = orderService.getPaidAccountsByUser(user, pageable);

	    // Fetch images for each game account and set them
	    for (GameAccount account : paidAccountsPage.getContent()) {
	        List<Image> images = imageService.getImagesByGameAccountId(account.getAccountId());
	        account.setImages(images);
	    }

	    // Add attributes to the model
	    model.addAttribute("gameAccounts", paidAccountsPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", paidAccountsPage.getTotalPages());

	    return "myAccount";
	}

}
