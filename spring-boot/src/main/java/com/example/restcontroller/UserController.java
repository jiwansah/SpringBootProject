package com.example.restcontroller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entitybean.User;
import com.example.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	
	@Autowired
    private UserService userService;
	
	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/api/services/controller/user")
	public List<User> user() {
		List<User> userList = userService.getAll();
		return userList;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/api/services/controller/user")
	public User saveUser(@RequestBody User newUser) {
		return userService.save(newUser);
	}
	
//	@DeleteMapping("/api/services/controller/user/{id}")
//	public void deleteUser(@PathVariable("id") long id) {
//		userService.deleteByID(id);
//	}

	@DeleteMapping("/api/services/controller/user/{userName}")
	public void deleteUser(@PathVariable("userName") String userName) {
		userService.deleteByUserName(userName);
	}

	@PostMapping("/api/services/controller/user/login")
	public ResponseEntity<String> login(@RequestBody User loginData) {
		User user = userService.getByUserName(loginData);
		if(user==null) {
			return new ResponseEntity<String>("UserName or Password is not correct ", HttpStatus.FORBIDDEN);
		}
		String token = getJWTToken(loginData.getUserName());
		return new ResponseEntity<String>(token, HttpStatus.ACCEPTED);
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_VIEWER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
}
