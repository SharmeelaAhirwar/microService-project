package com.org.rating.RatingService.Services;

import java.util.List;

import com.org.rating.RatingService.Entity.Rating;



public interface RatingSer {
	
	
	Rating createUser(Rating rating);
	    
	    List<Rating>getAllRatings();
	    
	    Rating getRatingById(String hotelId);
	    
	    List<Rating>getRatingByHotelID(String hoteId);
	    
	    List<Rating>getRatingByUserlID(String userId);
	    
	    Rating updateUser(Rating rating,String userId);
	    
	    void deleteUser(String userId);

}
