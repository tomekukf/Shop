package com.tomek.domek.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tomek.domek.model.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

	void deleteByUserId(Long id);

//	@Modifying
//	@Transactional
//	@Query("DELETE FROM PasswordResetToken  WHERE PasswordResetToken.userid = 10")
//	void deleteToken(Long id);

}
