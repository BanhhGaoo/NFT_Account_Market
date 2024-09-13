package com.example.demo.service;

import com.example.demo.entity.GameAccount;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

import jakarta.transaction.Transactional;

import org.p2p.solanaj.core.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order createOrder(User user, double totalAmount, List<OrderDetail> orderDetails) {
        // Tạo hóa đơn mới
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setTotalAmount(totalAmount);
        
        // Lưu hóa đơn
        Order savedOrder = orderRepository.save(order);
        
        // Gán hóa đơn cho mỗi OrderDetail và lưu
        for (OrderDetail detail : orderDetails) {
            detail.setOrder(savedOrder);
        }
        orderDetailRepository.saveAll(orderDetails);

        return savedOrder;
    }
    
    public Page<GameAccount> getPaidAccountsByUser(User user, Pageable pageable) {
        List<Order> orders = orderRepository.findByUser(user);
        List<GameAccount> paidAccounts = new ArrayList<>();

        for (Order order : orders) {
            order.getOrderDetails().forEach(orderDetail -> {
                GameAccount account = orderDetail.getGameAccount();
                if (account.isSelled()) {
                    paidAccounts.add(account);
                }
            });
        }

        // Tạo Page từ danh sách tài khoản đã thanh toán
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), paidAccounts.size());
        List<GameAccount> pagedAccounts = paidAccounts.subList(start, end);

        return new PageImpl<>(pagedAccounts, pageable, paidAccounts.size());
    }

}
