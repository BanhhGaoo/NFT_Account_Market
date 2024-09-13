package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AdminPayment;
import com.example.demo.entity.AdminPaymentKey;

public interface AdminPaymentRepository extends JpaRepository<AdminPayment, AdminPaymentKey> {}