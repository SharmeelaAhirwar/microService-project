package com.org.hotelService.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.hotelService.Entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
