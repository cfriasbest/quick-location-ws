package com.quick.location.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author cfriasb
 *
 */
@Getter
@Setter
@ToString
@IgnoreExtraProperties
public class PlaceDetailFirebase extends Place {

	private static final long serialVersionUID = 1L;
	private String formattedAddress;
	private String formattedPhoneNumber;
	private OpeningHours openingHours;
	private String url;
	private String website;


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
	@JsonGetter("formatted_address")
	public String getFormattedAddress() {
		return formattedAddress;
	}
	@JsonGetter("formatted_phone_number")
	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}
	@JsonGetter("opening_hours")
	public OpeningHours getOpeningHours() {
		return openingHours;
	}
	
	

}