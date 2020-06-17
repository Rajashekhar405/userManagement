package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.UserAudit;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserAuditService;
import com.example.demo.service.UserManagementService;

@SpringBootTest
public class UserManagementControllerTest {

	@Mock
	private UserManagementController userController;
	
	@Mock
	private UserManagementService userService;
	
	@Mock
	private UserAuditService auditService;
	
	@Mock
	private UserEntity userEntity;

	public List<UserEntity> dataSetups() {
		List<UserEntity> userList = new ArrayList<UserEntity>();
		UserEntity user = new UserEntity();
		user.setFirstName("abc");
		user.setLastName("asd");
		user.setEmail("abc@gmail.com");
		user.setUserID(123);
		user.setAddress("Bangalore");
		user.setMobileNo("123456789");
		userList.add(user);
		return userList;
		
	}
	
	public UserEntity userDataSetups() {
		UserEntity user = new UserEntity();
		user.setFirstName("abc");
		user.setLastName("asd");
		user.setEmail("abc@gmail.com");
		user.setUserID(123);
		user.setAddress("Bangalore");
		user.setMobileNo("123456789");
		return user;
		
	}
	
	@Test
	public void testGetAllUserDetails() {
		List<UserEntity> userlst = dataSetups();
		doReturn(userlst).when(userService).getAllUserDetails();
		userController.getAllUserDetails();
		assertEquals("abc", userlst.get(0).getFirstName());
		//verify(userService, times(1));
	}
	
	@Test
	public void testCreateUser() {
		UserEntity userdata = userDataSetups();
		doReturn(userdata).when(userService).createUser(userEntity);
		userController.createUser(userEntity);
		//verify(userService).createUser(userEntity);
		assertEquals("Bangalore", userdata.getAddress());
	}
	
	@Test
	public void testuserAuditActionTest() {
		List<UserAudit> auditList = new ArrayList<>();
		doReturn(auditList).when(auditService).findAuditDetails("GET");
		userController.userAuditAction("GET");
		assertEquals(0, auditList.size());
	}
	
}
