package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.AppConstants;
import com.example.demo.entity.UserAudit;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserAuditService;
import com.example.demo.service.UserManagementService;
import com.example.demoexception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Rajashekhar badad
 * @version 1.0
 * @since 16-06-2020
 */
@RestController
@RequestMapping("/users")
public class UserManagementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private UserAuditService userAuditService;


	/**
	 * 
	 * @param
	 * @return UserEntity List
	 * @throws
	 */
	@GetMapping
	@ApiOperation("GETTING ALL USERS LIST")
	public ResponseEntity<List<UserEntity>> getAllUserDetails(@RequestParam(defaultValue = "1", required = true) Integer loggedInUser ) throws UserNotFoundException{
		String ACTION = "GET";
		LOGGER.debug("***************Inside getAllUserDetails *******************"+ACTION);

		Optional<UserEntity> user = null;
		List<UserEntity> userList = null;
		if(null!=loggedInUser) {
			user = userManagementService.findById(loggedInUser);
			System.out.println("user"+user);
		}

		if(user.isPresent() && user.get().getUserRole().equalsIgnoreCase("ADMIN")) {
			userAuditService.saveUserAudit(userAuditActionMethod(ACTION, user.get().getId()));
			userList = userManagementService.getAllUserDetails();
			if(userList.size()>0) {
				return new ResponseEntity<>(userList, HttpStatus.OK);
			}else {
				LOGGER.error("NO DATA FOUND");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			userList =userManagementService.findByRoleName();
			return new ResponseEntity<>(userList, HttpStatus.OK);
		}

	}

	/**
	 * 
	 * @param
	 * @return UserEntity
	 * @throws
	 */
	@PostMapping
	@ApiOperation("CREATING NEW USER")
	public ResponseEntity<Object> createUser(@RequestParam(defaultValue = "1") Integer loggedInUser, @RequestBody UserEntity users) {
		String ACTION = "POST";
		LOGGER.debug("***************Inside createUser *******************"+ACTION);
		Optional<UserEntity> user = null;
		if(null!=loggedInUser) {
			user = userManagementService.findById(loggedInUser);
			System.out.println("user"+user);
		}
		if(user.get().getUserRole().equalsIgnoreCase(AppConstants.USER_ROLE)) {
			userAuditService.saveUserAudit(userAuditActionMethod(ACTION, user.get().getId()));
			userManagementService.createUser(users);
			LOGGER.info("users");
			return new ResponseEntity<Object>("USER CREATED SUCCESSFULLY", HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("ONLY ADMIN CAN CREATE USER", HttpStatus.OK);
		}
		
	}

	/**
	 * 
	 * @param
	 * @return UserEntity
	 * @throws
	 */	
	@PutMapping("/{id}")
	@ApiOperation("GET USER DETAILS BASED ON USER ID")
	public ResponseEntity<Object> updateUserEntity(@PathVariable("id") Integer id, @RequestBody UserEntity user) {
		String ACTION = "PUT";
		LOGGER.debug("***************Inside createUser *******************"+ACTION);
		userAuditService.saveUserAudit(userAuditActionMethod(ACTION, id));
		userManagementService.updateUserEntity(id, user);
		return new ResponseEntity<Object>("User updated successfully", HttpStatus.OK);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws
	 */
	@DeleteMapping("/{userId}")
	@ApiOperation("DELETE USER BASED ON USER ID")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer userId){
		String ACTION = "DELETE";
		LOGGER.debug("***************Inside deleteUser *******************"+ACTION);
		userAuditService.saveUserAudit(userAuditActionMethod(ACTION, userId));
		userManagementService.deleteUser(userId);
		return new ResponseEntity<Object>("USER DELETED SUCCESSFULLY", HttpStatus.OK);
	}

	/**
	 * 
	 * @param
	 * @return UserAudit List
	 * @throws
	 */
	@GetMapping("/auditSearch/{action}")
	@ApiOperation("GET ACTIONS PERFORMED BY ADMIN")
	public ResponseEntity<List<UserAudit>> userAuditAction(@PathVariable("action") String action) {
		LOGGER.debug("***************Inside createUser *******************"+action);
		@SuppressWarnings("unchecked")
		List<UserAudit> userAudit = (List<UserAudit>) userAuditService.findAuditDetails(action);
		return new ResponseEntity<List<UserAudit>>(userAudit, HttpStatus.OK);
	}

	/**
	 * 
	 * @param
	 * @return UserAUdit
	 * @throws
	 */
	private UserAudit userAuditActionMethod(String action, Integer userId) {
		LOGGER.debug("***************Inside userAuditActionMethod *******************"+action);
		UserAudit userAudit = new UserAudit();
		userAudit.setAction(action);
		userAudit.setActionPerfomedOn(new Date());
		userAudit.setUserId(userId.toString());
		return userAudit;
	}
}
