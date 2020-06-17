package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.UserManagementController;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserManagementRepository;

@Service
public class UserManagementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementService.class);
	
	@Autowired
	private UserManagementRepository userManagementRepository;
	
	public List<UserEntity> getAllUserDetails() {
		
		return userManagementRepository.findAll();
	}
	
	public UserEntity createUser(UserEntity users) {
		return userManagementRepository.save(users);
		
	}
	
	public UserEntity updateUserEntity(Integer id, UserEntity user) {
		
		Optional<UserEntity> userEntity = userManagementRepository.findById(id);
			UserEntity userEnty = userEntity.get();
			userEnty.setFirstName(user.getFirstName());
			userEnty.setLastName(user.getLastName());
			userEnty.setEmail(user.getEmail());
			userEnty.setAddress(user.getAddress());
			userEnty.setMobileNo(user.getMobileNo());
			return userManagementRepository.save(userEnty);
	}
	
	public void deleteUser(Integer userId){
		userManagementRepository.deleteById(userId);
	}

}
