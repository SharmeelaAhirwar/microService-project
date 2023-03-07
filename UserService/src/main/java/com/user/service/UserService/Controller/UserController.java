package com.user.service.UserService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.UserService.Entity.User;
import com.user.service.UserService.Services.UserService;
import com.user.service.UserService.Services.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userS;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@PostMapping("/create")
	public ResponseEntity<User> createUsers(@RequestBody User user){
		User savedUser=this.userS.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		
	}
//	int retryCount=1;
	
	@GetMapping("/getUserByID/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelBreak")
//	@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelBreak")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelBreak")
	public ResponseEntity<User>getUsersById(@PathVariable String userId){
		
		
//		logger.info("retry count : {}",retryCount);
	
//		  retryCount++;
		User user=this.userS.getUserById(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
//	creted fallbackmethod
	
	public ResponseEntity<User>ratingHotelBreak(String userId,Exception ex){
		
//		ex.printStackTrace();
		User user=User.builder().email("dummy@gmail.com").name("dummy").about("service is down:").userId("down").build();
	
	return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
