package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apparquear.model.Token;

public interface TokenDAO extends JpaRepository<Token, Long> {
	public Token findByToken(String token);
}
