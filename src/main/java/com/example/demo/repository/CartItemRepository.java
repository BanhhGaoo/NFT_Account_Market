package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser(User user);
    void deleteByUserAndGameAccount(User user, GameAccount gameAccount);
}