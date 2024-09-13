package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByGameAccount_AccountId(Integer accountId);
}
