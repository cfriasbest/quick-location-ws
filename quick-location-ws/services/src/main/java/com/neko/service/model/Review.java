package com.neko.service.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Review {

	private Object [] aspects;
	private String authorName;
	private String authorUrl;
	private String language;
	private String profilePhotoUrl;
	private Double rating;
	private String text;
	
	@JsonSetter("author_name")
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@JsonSetter("author_url")
	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
	@JsonSetter("profile_photo_url")
	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	
}
