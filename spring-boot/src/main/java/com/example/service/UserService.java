package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entitybean.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAll() {
		
		Iterable<User> iterable = userRepository.findAll();
	    List<User> users = new ArrayList<User>();
	    iterable.forEach(users::add);
	    return users;
	}
	
	public User getByUserName(User user) {
		return userRepository.findByUserName(user.getUserName(), user.getPassword());
	}
	
	public void deleteByID(long userID) {
		userRepository.deleteById(userID);
	}
	public void deleteByUserName(String userName) {
		userRepository.deleteByUserName(userName);
	}
	
	//@Transactional(rollbackFor = Exception.class) 
	public User save(User newUser) {
		//newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
	    return userRepository.save(newUser);
	}
}
