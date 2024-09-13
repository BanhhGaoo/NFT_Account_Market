package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
    private ImageRepository imageRepository;

    public List<Image> getImagesByGameAccountId(Integer accountId) {
        return imageRepository.findByGameAccount_AccountId(accountId);
    }
}
