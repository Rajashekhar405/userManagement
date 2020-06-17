package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserManagementRepository extends JpaRepository<UserEntity, Integer>{

	public UserEntity findById(String roleId);

	@Query(value = "select * from USER_MANAGEMENT um where um.USER_ROLE='USER'", nativeQuery=true)
	public List<UserEntity> findByUserRole();

}
