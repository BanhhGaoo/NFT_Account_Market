package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GameAccount;

@Repository
public interface GameAccountRepository extends JpaRepository<GameAccount, Integer> {
	GameAccount findByAccountId(int accountId);
	Page<GameAccount> findByGameNameAndSelledFalse(String gameName, Pageable pageable);
	Page<GameAccount> findBySelledFalse(Pageable pageable);
}
