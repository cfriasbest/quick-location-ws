package com.quick.location.model;

import org.dozer.Mapping;

import com.google.firebase.database.Exclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author cfriasb
 *
 */
@Setter
@Getter
@ToString
public class ReviewFirebase {

	private String authorName;
	private Double rating;
	private String text;
	@Mapping("place.placeId")
	private String placeId;

	@Exclude
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}
