package com.tomek.domek.controller;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomek.domek.model.GenericResponse;
import com.tomek.domek.model.PasswordDto;
import com.tomek.domek.model.User;
import com.tomek.domek.repository.CustomRepo;
import com.tomek.domek.repository.PasswordTokenRepository;
import com.tomek.domek.service.UserService;

@Controller
public class PasswordRecoveryController {

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private UserService userService;

	private JavaMailSender mailSender;
	@Autowired
	private PasswordTokenRepository  tokenRepo;
	@Autowired
	private CustomRepo customRepo;

	@Autowired
	private MessageSource messages;

	@Autowired
	private Environment env;
	private final Long a = (long) 11.0;
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private Logger logger = org.slf4j.LoggerFactory.getLogger(PasswordRecoveryController.class);

	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "forgotPassword";
	}

	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
	@ResponseBody()
	public GenericResponse resetPassword(HttpServletRequest request, @RequestParam("email") String email) {

		User user = userService.findUserByEmail(email);
		if (user == null) {
			logger.info("did not find user--------------------------------");
			throw new UsernameNotFoundException(email);
		}
		String token = UUID.randomUUID().toString();
		logger.info("im here-------------------------------------------");

		userService.createPasswordResetTokenForUser(user, token);

		try {
			mailSender.send(constructEmailWithToken(getAppUrl(request), request.getLocale(), token, user));
		} catch (MailException e) {
			logger.info("message-----------------------------------------------" + e.getMessage());
		}
		logger.info("zwracam genericmessagees-----------------------------------------------" );

		return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));

	}

	private SimpleMailMessage constructEmailWithToken(String contextPath, Locale locale, String token, User user) {
		String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
		String message = messages.getMessage("message.resetPassword", null, locale);
		return constructMail("Reset Pasword", message + " \r\n" + url, user);
	}

	private SimpleMailMessage constructMail(String subject, String body, User user) {

		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("support.email"));

		return email;
	}

	@GetMapping(value = "/user/changePassword")
	public String showResetPasswordPage(Locale locale, Model model, @RequestParam("id") long id,
			@RequestParam("token") String token) {

		String result = userService.validateUserToken(id, token);
		if (result != null) {
			model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
			return "redirect:/loginForm?lang=" + locale.getLanguage();
		}
		logger.info("lollll===============================");
		return "redirect:updatePassword";

	}

	@GetMapping("/user/updatePassword")
	public String forgotPasseword() {
		return "updatePassword";
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	@RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse savePassword(Locale locale, PasswordDto passwordDto) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("tu jestem======================================");
		
		userService.changeUserPassword(user, passwordDto.getNewPassword());
		
		
		
		try {
//			tokenRepo.deleteToken(user.getId());
			
		} catch (Exception e) {
		logger.info("message-------------"+e.getMessage());
		}
		
		
		
		
		try {
			int list = customRepo.deleteTokenByUserId(user.getId());
			logger.info("affected rows {}",list);
		} catch (Exception e) {
			logger.info("message1-------------"+e.getMessage());
		}
		
		
		logger.info("tu jestem2======================================");
		
		return new GenericResponse(messages.getMessage("message.resetPassword", null, locale));
	}

}
