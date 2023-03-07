package com.org.rating.RatingService.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.rating.RatingService.Entity.Rating;
import com.org.rating.RatingService.Exception.ResourceNotFoundException;
import com.org.rating.RatingService.Repositery.RatingRepo;

@Service
public class RatingSerImpl implements RatingSer {

	@Autowired
	private RatingRepo ratingRepo;
	
	@Override
	public Rating createUser(Rating rating) {
		String ranId=UUID.randomUUID().toString();
		rating.setRatingId(ranId);
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		
		return ratingRepo.findAll();
	}

	@Override
	public Rating getRatingById(String ratinglId) {
		
		return ratingRepo.findById(ratinglId).orElseThrow(
				()->new ResourceNotFoundException("rating not found with given id"));
	}

	@Override
	public List<Rating> getRatingByHotelID(String hoteId) {
		
		return ratingRepo.findByHotelId(hoteId);
	}

	@Override
	public List<Rating> getRatingByUserlID(String userId) {
		
		return ratingRepo.findByUserId(userId);
	}

	@Override
	public Rating updateUser(Rating rating, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

}
