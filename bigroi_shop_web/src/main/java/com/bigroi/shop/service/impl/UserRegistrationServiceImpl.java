package com.bigroi.shop.service.impl;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.UserCredentialsDao;
import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.dao.VerificationTokenDao;
import com.bigroi.shop.exception.EmailVerificationException;
import com.bigroi.shop.model.User;
import com.bigroi.shop.model.UserCredentials;
import com.bigroi.shop.model.UserRegistrationData;
import com.bigroi.shop.model.VerificationToken;
import com.bigroi.shop.service.PasswordEncryptionService;
import com.bigroi.shop.service.UserRegistrationService;

public class UserRegistrationServiceImpl implements UserRegistrationService {
	
	@Autowired
	private PasswordEncryptionService passwordEncryptionService;
	
	@Autowired
	private UserCredentialsDao userCredentialsDao;
	
	@Autowired
	private VerificationTokenDao verificationTokenDao;
	
	@Autowired
	private UserDao userDao;
		
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public void register(final UserRegistrationData registration) throws Exception {
		final String password = registration.getPassword();
		final byte[] salt = passwordEncryptionService.generateSalt();
		final byte[] encryptedPassword = passwordEncryptionService.getEncryptedPassword(password, salt);
		
		VerificationToken verificationToken = register(registration, encryptedPassword, salt);
		sendMail(registration, verificationToken);
	}

	protected void sendMail(final UserRegistrationData registration, VerificationToken verificationToken) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(registration.getUser().getEmail());
		String userName = registration.getUser().getFullName();
		String link = InetAddress.getLocalHost().getHostAddress() + "/registration/verify?token=";
		String messageTextTemplate = messageSource.getMessage("mail.confirmation.template", new Object[0], LocaleContextHolder.getLocale() );
		message.setText( String.format( messageTextTemplate , userName, link) );
		message.setSubject("Bigroi Shop registration confirmation");
		mailSender.send(message);
	}
	
	@Transactional
	protected VerificationToken register(UserRegistrationData registration, byte[] encryptedPassword, byte[] salt) throws Exception {
		String password = registration.getPassword();
		User user = registration.getUser();
		userDao.save(user);
		Integer userId = user.getId();
		UserCredentials userPassword = new UserCredentials(userId, password, encryptedPassword, salt);
		userCredentialsDao.save(userPassword);
		VerificationToken verificationToken = new VerificationToken(userId);
		verificationTokenDao.save(verificationToken);
		return verificationToken;
	}

	@Override
	@Transactional
	public void confirm(String verificationToken) throws Exception {
		Integer userId = verificationTokenDao.findUserIdByVerificationToken(verificationToken);
		if ( userId == null ) {
			throw new EmailVerificationException();
		}
		verificationTokenDao.deleteVerificationToken(verificationToken);				
	}

}
