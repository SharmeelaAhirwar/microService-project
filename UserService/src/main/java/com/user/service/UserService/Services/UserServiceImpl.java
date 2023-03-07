package com.user.service.UserService.Services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.sql.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.UserService.Entity.Hotel;
import com.user.service.UserService.Entity.Rating;
import com.user.service.UserService.Entity.User;
import com.user.service.UserService.Exception.ResourceNotFoundException;
import com.user.service.UserService.Repositery.UserRepo;
import com.user.service.UserService.external.services.HotelService;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelsService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) {
		
		//genrate user id
		String  randomID=UUID.randomUUID().toString();
		user.setUserId(randomID);
		
		return this.userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
	
		return this.userRepo.findAll();
	}

	@Override
	public User getUserById(String userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("user not found with given id :!!" +userId));
		
		//fetch rating of above user from rating service;
		
//		http://localhost:8082/rating/getRatingByUserID/4c237adb-0594-4ad9-95ac-a98caade9df4
		
//		 ArrayList forObject= restTemplate.getForObject("http://localhost:8082/rating/getRatingByUserID/"+user.getUserId(),
//				ArrayList.class);

		
//		 user.setRating(forObject);
//		
//		return user;
		 logger.info("user :{} ",user.getEmail());
		Rating[] ratingOfUser= restTemplate.getForObject("http://RATING-SERVICE/rating/getRatingByUserID/"+user.getUserId(),
				Rating[].class);
		
		logger.info("Rating  :{} ",user.getEmail());
		
		 List<Rating>ratings=Arrays.stream(ratingOfUser).toList();
		 
		 List<Rating>ratingList=ratings.stream().map(rating->{
			 
			 System.out.println(rating.getHotelId());
			 
//			 ResponseEntity<Hotel>forEnt=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/getHotelByID/"+rating.getHotelId(),
//					Hotel.class );
			 
//			 Hotel hotel=forEnt.getBody();
			 
			 Hotel hotel=hotelsService.getHotel(rating.getHotelId());
			 rating.setHotel(hotel);
			 return rating;
		 }).collect(Collectors.toList());
		 
		 user.setRating(ratingList);
		 return user;
		
	
	}

	@Override
	public User updateUser(User user, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

}
