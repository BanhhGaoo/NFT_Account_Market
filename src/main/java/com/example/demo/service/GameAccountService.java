package com.example.demo.service;

import com.example.demo.entity.GameAccount;
import com.example.demo.repository.GameAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameAccountService {

    @Autowired
    private GameAccountRepository gameAccountRepository;

    public List<GameAccount> getAllGameAccounts() {
        return gameAccountRepository.findAll();
    }
    
    public GameAccount getAccountById(int accountId) {
    	return gameAccountRepository.findByAccountId(accountId);
    }

    public Page<GameAccount> getFilteredAccounts(String gameName, Pageable pageable) {
        if (gameName != null && !gameName.isEmpty()) {
            return gameAccountRepository.findByGameNameAndSelledFalse(gameName, pageable);
        } else {
            return gameAccountRepository.findBySelledFalse(pageable);
        }
    }

    
    public Page<GameAccount> getAllGameAccounts(Pageable pageable) {
        return gameAccountRepository.findAll(pageable);
    }
    
    public Page<GameAccount> getUnsoldAccounts(Pageable pageable) {
        return gameAccountRepository.findBySelledFalse(pageable);
    }
    
    public void updateSelledStatus(int accountId) {
        GameAccount gameAccount = gameAccountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("GameAccount not found"));
        gameAccount.setSelled(true);
        gameAccountRepository.save(gameAccount);
    }
}
