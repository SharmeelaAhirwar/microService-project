package com.org.hotelService.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.org.hotelService.Entity.Hotel;
import com.org.hotelService.Exception.ResourceNotFoundException;
import com.org.hotelService.Repositery.HotelRepo;

@Service
public class HotelSerImpl implements HotelSer {
	
	
	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public Hotel create(Hotel hotel) {
		String ranId=UUID.randomUUID().toString();
		hotel.setHotelId(ranId);
		
		
		return hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		
		return hotelRepo.findAll();
	}

	@Override
	public Hotel getById(String id) {
		
		return hotelRepo.findById(id).orElseThrow(()->new
				ResourceNotFoundException("hotel with given id is not found"));
	}

}
