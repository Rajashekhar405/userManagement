package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAudit;
import com.example.demo.repository.UserAuditRepository;

@Service
public class UserAuditService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuditService.class);
	
	@Autowired
	private UserAuditRepository  userAuditRepository;
	
	public void saveUserAudit(UserAudit userAudit) {
		userAuditRepository.save(userAudit);
	}
	
	public List<?> findAuditDetails(String keyword) {
		LOGGER.info("Entered search keyword = "+keyword);
		return userAuditRepository.findUsersByKeyword(keyword);
	}
}
