package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserAudit;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserAuditService;
import com.example.demo.service.UserManagementService;

@RestController
@RequestMapping("/users")
public class UserManagementController {
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private UserAuditService userAuditService;
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllUserDetails() {
		String ACTION = "GET";
		userAuditService.saveUserAudit(userAuditActionMethod(ACTION));
		List<UserEntity> userList = userManagementService.getAllUserDetails();
		if(userList.size()>0) {
			return new ResponseEntity<>(userList, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<UserEntity> createUser( @RequestBody UserEntity users) {
		String ACTION = "POST";
		userAuditService.saveUserAudit(userAuditActionMethod(ACTION));
		UserEntity userList = userManagementService.createUser(users);
		return new ResponseEntity<UserEntity>(userList, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUserEntity(@PathVariable("id") Integer id, @RequestBody UserEntity user) {
		String ACTION = "UPDATE";
		userAuditService.saveUserAudit(userAuditActionMethod(ACTION));
		return userManagementService.updateUserEntity(id, user);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Integer userId){
		String ACTION = "DELETE";
		userAuditService.saveUserAudit(userAuditActionMethod(ACTION));
		userManagementService.deleteUser(userId);
	}
	
	
	@GetMapping("/auditSearch/{action}")
	public ResponseEntity<List<UserAudit>> userAuditAction(@PathVariable("action") String action) {
		@SuppressWarnings("unchecked")
		List<UserAudit> userAudit = (List<UserAudit>) userAuditService.findAuditDetails(action);
		return new ResponseEntity<List<UserAudit>>(userAudit, HttpStatus.OK);
	}
	
	private UserAudit userAuditActionMethod(String action) {
		UserAudit userAudit = new UserAudit();
		userAudit.setAction(action);
		userAudit.setCreatedDate(new Date());
		userAudit.setUpdatedDate(new Date());
		return userAudit;
	}
}
