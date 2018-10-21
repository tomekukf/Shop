package com.tomek.domek.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.PasswordResetToken;
import com.tomek.domek.model.User;
import com.tomek.domek.repository.PasswordTokenRepository;
import com.tomek.domek.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordTokenRepository passwordTokenRepo;

	public User getUser(int number) {
		User user = userRepo.findById(number);
		return user;
	}

	public User getUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}

	public boolean isUserPresent(String email) {
		User user = userRepo.findByEmail(email);
		if (user != null)
			return true;

		return false;
	}

	public List<User> findThem(String username) {
		List<User> userList = userRepo.findAllByUsername(username);

		return userList;

	}

	public List<User> findByUsername(String name) {
		return userRepo.findByUsernameLike("%" + name + "%");
	}

	public User findUserByEmail(String email) {

		User user = userRepo.findByEmail(email);
		return user;
	}

	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepo.save(myToken);
	}

	public String validateUserToken(long id, String token) {
		PasswordResetToken result = passwordTokenRepo.findByToken(token);
		if ((result == null) || (result.getUser().getId() != id)) {
			return "invalidToken";
		}

		Calendar cal = Calendar.getInstance();
		if ((result.getExpirationDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "expired";
		}

		User user = result.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

	public void changeUserPassword(User user, String newPassword) {
		BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(newPassword));
		userRepo.save(user);
	}

}
