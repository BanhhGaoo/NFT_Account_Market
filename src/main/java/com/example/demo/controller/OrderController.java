package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.GameAccount;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.service.CartService;
import com.example.demo.service.GameAccountService;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
	private GameAccountService gameAccountService;

	@Autowired
	private CartService cartService;

	@PostMapping("/createOrder")
	@ResponseBody
	public String confirmPurchase(
	        @RequestParam double totalAmount,
	        @RequestParam String accountIds, // Lấy danh sách accountId từ client
	        HttpSession session) {
		
		List<Integer> accountIdList = Arrays.stream(accountIds.split(","))
                .map(String::trim)   // Xóa khoảng trắng thừa
                .map(Integer::parseInt) // Chuyển đổi chuỗi thành số nguyên
                .collect(Collectors.toList());
	    
	    User user = (User) session.getAttribute("loggedInUser"); // Lấy thông tin người dùng từ session
	    
	    // Tạo danh sách OrderDetail từ các thông tin nhận được
	    List<OrderDetail> orderDetails = new ArrayList<>();
	    for (Integer accountId : accountIdList) {
	        OrderDetail detail = new OrderDetail();
	        
	        GameAccount gameAccount = gameAccountService.getAccountById(accountId);
	        
	        if (gameAccount != null) {
	            detail.setGameAccount(gameAccount);
	            detail.setPrice(gameAccount.getPrice());
	            gameAccountService.updateSelledStatus(accountId);
	            orderDetails.add(detail);

	            cartService.removeCartItem(user, gameAccount);
	            
	        } else {
	            // Xử lý trường hợp không tìm thấy GameAccount với accountId
	            System.out.println("Không tìm thấy GameAccount với accountId: " + accountId);
	        }
	    }

	    // Tạo và lưu hóa đơn cùng các chi tiết hóa đơn
	    orderService.createOrder(user, totalAmount, orderDetails);
	    
	    return "cart"; // Trả về trang giỏ hàng sau khi mua
	}
}