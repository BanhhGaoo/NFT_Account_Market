package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Blockchain;

public interface BlockchainRepository extends JpaRepository<Blockchain, Integer> {}
