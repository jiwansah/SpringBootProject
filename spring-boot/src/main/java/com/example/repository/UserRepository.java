package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entitybean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 
    
	@Query(value = "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
	User findByUserName(@Param("userName") String userName, @Param("password") String password);

	@Transactional
	@Modifying
	//@Query(value = "DELETE User u WHERE u.userName = :userName")
    @Query(name = "Delete User by UserName", value = "DELETE FROM User u WHERE u.id IN (SELECT"
    		+ " ud.id FROM User ud WHERE ud.userName = :userName)")
	void deleteByUserName(@Param("userName") String userName);
	
	
}
