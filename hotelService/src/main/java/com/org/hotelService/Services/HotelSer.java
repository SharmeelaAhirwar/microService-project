package com.org.hotelService.Services;

import java.util.List;

import com.org.hotelService.Entity.Hotel;

public interface HotelSer {
	
	Hotel create(Hotel hotel);
	
	
	List<Hotel>getAll();
	
	
	Hotel getById(String id);

}
