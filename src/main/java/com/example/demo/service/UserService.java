package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean validateUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		return user != null && user.getPassword().equals(password);
	}
	
	public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
    
    public User findUserById(int id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    
}
