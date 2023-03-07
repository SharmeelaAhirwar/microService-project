package com.org.hotelService.Controller;

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

import com.org.hotelService.Entity.Hotel;
import com.org.hotelService.Services.HotelSer;


@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelSer hotelSer;
	
	 @PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotels(@RequestBody Hotel hotel){
		Hotel savedHotel=this.hotelSer.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
		
	}
	
   @PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/getHotelByID/{hotelId}")
	public ResponseEntity<Hotel>getUsersById(@PathVariable String hotelId){
		Hotel hotel=this.hotelSer.getById(hotelId);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	
//	@PreAuthorize("hasAuthority('SCOPE_internal')" || hasAuthority('Admin'))
//	getalllHotel
	

}
