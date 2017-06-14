package com.quick.location.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

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
public class Photo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4256934443470895294L;
	private String photoReference;
	private Long height;
	private Long width;

	@JsonSetter("photo_reference")
	public void setPhotoReference(String photoReference) {
		this.photoReference = photoReference;
	}
	
	@JsonGetter("photo_reference")
	public String getPhotoReference() {
		return this.photoReference;
	}
}
