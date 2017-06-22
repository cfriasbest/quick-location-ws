package com.quick.location.model;

import org.dozer.Mapping;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

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
@IgnoreExtraProperties
public class ReviewFirebase {

	private String authorName;
	private Double rating;
	private String comment;
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
