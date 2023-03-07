package com.org.rating.RatingService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.rating.RatingService.Entity.Rating;
import com.org.rating.RatingService.Services.RatingSer;



@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingSer ratingS;
	
	  @PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/create")
	public ResponseEntity<Rating> createUsers(@RequestBody Rating rating){
		Rating savedRating=this.ratingS.createUser(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
		
	}
	
	@GetMapping("/getRatingByID/{ratingId}")
	public ResponseEntity<Rating>getRatingById(@PathVariable String ratingId){
		Rating rating=this.ratingS.getRatingById(ratingId);
		return new ResponseEntity<Rating>(rating,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/getRatingByUserID/{userId}")
	public ResponseEntity<List<Rating>>getRatingByUsersId(@PathVariable String userId){
		List<Rating>rating=this.ratingS.getRatingByUserlID(userId);
		return new ResponseEntity<List<Rating>>(rating,HttpStatus.OK);
	}
	
	@GetMapping("/getRatingByHotelID/{hotelId}")
	public ResponseEntity<List<Rating>>getRatingByHotelId(@PathVariable String hotelId){
		List<Rating>rating=this.ratingS.getRatingByHotelID(hotelId);
		return new ResponseEntity<List<Rating>>(rating,HttpStatus.OK);
	}

}
