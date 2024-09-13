package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    
    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/loginForm")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        // Kiểm tra đăng nhập với Admin trước
        boolean isValidAdmin = adminService.validateAdmin(username, password);
        
        if (isValidAdmin) {
            Admin admin = adminService.findByAdminName(username);
            session.setAttribute("loggedInAdmin", admin);
            return "redirect:/"; // Điều hướng đến trang admin
        }

        // Nếu không phải Admin, kiểm tra User
        boolean isValidUser = userService.validateUser(username, password);
        
        if (!isValidUser) {
            model.addAttribute("msg", "Tài khoản hoặc mật khẩu không đúng. Vui lòng nhập lại!");
            return "login";
        }

        User user = userService.findByUsername(username);
        session.setAttribute("loggedInUser", user);

        return "redirect:/"; // Điều hướng đến trang user
    }

    @PostMapping("/register")
    public String register(@RequestParam("regUsername") String username, @RequestParam("email") String email,
                           @RequestParam("regPassword") String password, @RequestParam("regConfirmPassword") String confirmPassword,
                           Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("msg", "Mật khẩu không khớp");
            model.addAttribute("showRegisterForm", true);
            return "login";
        }

        if (userService.usernameExists(username)) {
            model.addAttribute("msg", "Tên đăng nhập đã tồn tại");
            model.addAttribute("showRegisterForm", true);
            return "login";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password); // Lưu ý: Nên mã hóa mật khẩu trước khi lưu vào 
        newUser.setPublicKey("Chưa kết nối");
        newUser.setTwoFactorEnabled(false); // Giả sử mặc định không kích hoạt 2FA

        userService.saveUser(newUser);

        model.addAttribute("msg", "Đăng ký thành công. Bạn có thể đăng nhập.");
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("loggedInUser");
    	return "redirect:/login";
    }
}
