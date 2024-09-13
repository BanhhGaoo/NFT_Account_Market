package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.User;
import com.example.demo.repository.CartItemRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {
    
	@Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartItemsForUser(User user) {
        return cartItemRepository.findByUser(user);
    }
    
    public void addProductToCart(User user, GameAccount gameAccount) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setGameAccount(gameAccount);
        cartItemRepository.save(cartItem);
    }
    
    public void removeProductFromCart(User user, int cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        // Log thông tin chi tiết để kiểm tra
        if (cartItem == null) {
            System.out.println("CartItem with ID " + cartItemId + " not found.");
        } else {
            System.out.println("CartItem found: " + cartItem);
            System.out.println("User in CartItem: " + cartItem.getUser());
            System.out.println("User passed: " + user);
            
            if (cartItem.getUser().equals(user)) {
                cartItemRepository.delete(cartItem);
                System.out.println("CartItem deleted successfully.");
            } else {
                System.out.println("User mismatch: CartItem user does not match the passed user.");
            }
        }
    }
    
    @Transactional
    public void removeCartItem(User user, GameAccount gameAccount) {
        cartItemRepository.deleteByUserAndGameAccount(user, gameAccount);
    }
}

