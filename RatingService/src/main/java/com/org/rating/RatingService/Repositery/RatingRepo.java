package com.org.rating.RatingService.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.rating.RatingService.Entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, String> {

	//custom finding method;
	List<Rating>findByUserId(String userId);
	
	List<Rating>findByHotelId(String hotelId);
	
}
