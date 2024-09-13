package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.User;
import com.example.demo.service.CartService;
import com.example.demo.service.GameAccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@Autowired
    private GameAccountService gameAccountService;

	@Autowired
	private CartService cartService;

	@GetMapping("/cart")
	public String viewCart(Model model, HttpSession session) {
	    User user = (User) session.getAttribute("loggedInUser");
	    
	    if (user == null) {
	        // Xử lý trường hợp người dùng chưa đăng nhập, ví dụ, chuyển hướng đến trang đăng nhập
	        return "redirect:/login";
	    }
	    
	    List<CartItem> cartItems = cartService.getCartItemsForUser(user);
	    model.addAttribute("cartItems", cartItems);
	    return "cart";
	}

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("gameAccountId") int gameAccountId, HttpSession session) {
	    // Lấy thông tin người dùng từ session
	    User user = (User) session.getAttribute("loggedInUser");
	    
	    // Nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
	    if (user == null) {
	        session.setAttribute("msg", "Vui lòng đăng nhập !!");
	        return "redirect:/login";
	    }
	    
	    // Lấy thông tin gameAccount từ database dựa trên gameAccountId
	    GameAccount gameAccount = gameAccountService.getAccountById(gameAccountId);
	    
	    // Thêm sản phẩm vào giỏ hàng của người dùng
	    cartService.addProductToCart(user, gameAccount);
	    session.setAttribute("msg", "Thêm vào giỏ hàng thành công !!");
	    
	    return "redirect:/products?gameName=" + gameAccount.getGameName();
	}
	
	@GetMapping("/removeFromCart")
	public String removeFromCart(@RequestParam("cartItemId") int cartItemId, HttpSession session, Model model) {
	    // Kiểm tra người dùng đã đăng nhập
	    User user = (User) session.getAttribute("loggedInUser");
	    
	    // Xóa sản phẩm khỏi giỏ hàng
	    cartService.removeProductFromCart(user, cartItemId);
	    model.addAttribute("msg", "Xóa khỏi giỏ hàng thành công !!");
	    
	    // Chuyển hướng về trang giỏ hàng
	    return "redirect:/cart";
	}


}
