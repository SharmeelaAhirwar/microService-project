package com.org.hotelService.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="hotels")
@Getter
@Setter
@NoArgsConstructor

public class Hotel {
	
	@Id
	private String hotelId;
	private String name;
	private String location;
	private String about;

}
