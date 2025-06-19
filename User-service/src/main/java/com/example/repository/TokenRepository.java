package com.example.repository;


	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import com.example.model.Token;

import java.util.Date;
	import java.util.Optional;

	@Repository
	public interface TokenRepository extends JpaRepository<Token,Long> {
	    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String tokenValue, boolean deleted, Date currentTime);
	    Optional<Token> findByValueAndDeleted(String value,boolean deleted);
	}