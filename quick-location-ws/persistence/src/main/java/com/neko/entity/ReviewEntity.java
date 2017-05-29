package com.neko.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the reviews database table.
 * 
 */
@Entity
@Table(name = "reviews")
@NamedQuery(name = "ReviewEntity.findAll", query = "SELECT r FROM ReviewEntity r")
public class ReviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idreviews;

	@Column(name = "author_name")
	private String authorName;

	// @Column(name="author_url")
	// private String authorUrl;

	// private String language;

	@Column(name = "profile_photo_url")
	private String profilePhotoUrl;

	// private String rating;

	private String text;

	private String time;

	// bi-directional many-to-one association to Place
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id")
	private Place place;

	public ReviewEntity() {
	}

	public int getIdreviews() {
		return this.idreviews;
	}

	public void setIdreviews(int idreviews) {
		this.idreviews = idreviews;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	// public String getAuthorUrl() {
	// return this.authorUrl;
	// }
	//
	// public void setAuthorUrl(String authorUrl) {
	// this.authorUrl = authorUrl;
	// }
	//
	// public String getLanguage() {
	// return this.language;
	// }
	//
	// public void setLanguage(String language) {
	// this.language = language;
	// }

	public String getProfilePhotoUrl() {
		return this.profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	// public String getRating() {
	// return this.rating;
	// }
	//
	// public void setRating(String rating) {
	// this.rating = rating;
	// }

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}