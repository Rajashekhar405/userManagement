package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserAudit;
import com.example.demoexception.UserNotFoundException;


public interface UserAuditRepository extends JpaRepository<UserAudit, Integer>{

	@Query(value="select * from USER_AUDIT u where u.action like %:keyword% ", nativeQuery=true)
	List<UserAudit> findUsersByKeyword(@Param("keyword") String keyword) throws UserNotFoundException;
}
