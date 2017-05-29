package com.neko.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "place_id")
	private String placeId;

	private float lat;

	private float lng;

	private String name;

	private float rating;

	private String vicinty;

	// bi-directional many-to-one association to Photo
	@Autowired
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "place")
	private List<Photo> photos = new ArrayList<>();

	// bi-directional one-to-one association to Placedetail
	@OneToOne(mappedBy = "place", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Placedetail placedetail;

	// bi-directional many-to-many association to Type
	@ManyToMany(mappedBy = "places", cascade = { CascadeType.ALL })
	private List<Type> types = new ArrayList<>();;

	// bi-directional many-to-one association to ReviewEntity
	@OneToMany(mappedBy = "place", cascade = { CascadeType.ALL })
	private List<ReviewEntity> reviews = new ArrayList<>();;

	public Place() {
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public float getLat() {
		return this.lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return this.lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getVicinty() {
		return this.vicinty;
	}

	public void setVicinty(String vicinty) {
		this.vicinty = vicinty;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setPlace(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setPlace(null);

		return photo;
	}

	public Placedetail getPlacedetail() {
		return this.placedetail;
	}

	public void setPlacedetail(Placedetail placedetail) {
		this.placedetail = placedetail;
	}

	public List<Type> getTypes() {
		return this.types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public List<ReviewEntity> getReviewEntitys() {
		return this.reviews;
	}

	public void setReviewEntitys(List<ReviewEntity> reviews) {
		this.reviews = reviews;
	}

	public ReviewEntity addReviewEntity(ReviewEntity review) {
		getReviewEntitys().add(review);
		review.setPlace(this);

		return review;
	}

	public ReviewEntity removeReviewEntity(ReviewEntity review) {
		getReviewEntitys().remove(review);
		review.setPlace(null);

		return review;
	}

}