//package com.quick.location.model;
//
//import java.io.Serializable;
//
//import com.fasterxml.jackson.annotation.JsonSetter;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
///**
// * 
// * @author cfriasb
// *
// */
//@Setter
//@Getter
//@ToString
//public class Review implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -7768252548404151432L;
//	private Object[] aspects;
//	private String authorName;
//	private String authorUrl;
//	private String language;
//	private String profilePhotoUrl;
//	private Double rating;
//	private String text;
//	private boolean done;
//
//	@JsonSetter("author_name")
//	public void setAuthorName(String authorName) {
//		this.authorName = authorName;
//	}
//
//	@JsonSetter("author_url")
//	public void setAuthorUrl(String authorUrl) {
//		this.authorUrl = authorUrl;
//	}
//
//	@JsonSetter("profile_photo_url")
//	public void setProfilePhotoUrl(String profilePhotoUrl) {
//		this.profilePhotoUrl = profilePhotoUrl;
//	}
//
//}
