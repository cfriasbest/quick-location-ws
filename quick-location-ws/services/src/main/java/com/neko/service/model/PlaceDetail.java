package com.neko.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlaceDetail extends Place{

	private static final long serialVersionUID = 1L;
    private String formattedAddress;
    private String formattedPhoneNumber;
    private OpeningHours openingHours;
    private String url;
    private String website;
    private List<Photo> photos;
    private List<Review> reviews;
    
    @JsonSetter("formatted_address")
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
    
    @JsonSetter("formatted_phone_number")
	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}
    @JsonSetter("opening_hours")
	public void setOpeningHours(OpeningHours openingHours) {
		this.openingHours = openingHours;
	}
    
    

}