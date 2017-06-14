package com.quick.location.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dozer.Mapping;

/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@Table(name = "place")
@NamedQuery(name = "PlaceEntity.findAll", query = "SELECT p FROM PlaceEntity p")
public class PlaceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "place_id")
	private String placeId;

	@Mapping("geometry.location.lat")
	private float lat;
	@Mapping("geometry.location.lng")
	private float lng;

	private String name;

	private float rating;

	private String vicinty;

	// bi-directional many-to-one association to PhotoEntity
	@OneToMany(mappedBy = "place", cascade = { CascadeType.ALL })
	private List<PhotoEntity> photos;

	// uni-directional one-to-one association to PlacedetailEntity
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "place_id", referencedColumnName = "place_id")
	private PlacedetailEntity placedetail;

	// bi-directional many-to-one association to ReviewEntity
	@OneToMany(mappedBy = "place", cascade = { CascadeType.ALL })
	private List<ReviewEntity> reviews;

	// bi-directional many-to-one association to SugestDataEntity
	@OneToMany(mappedBy = "place")
	private List<SugestDataEntity> sugestData;

	public PlaceEntity() {
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

	public List<PhotoEntity> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<PhotoEntity> photos) {
		this.photos = photos;
	}

	public PhotoEntity addPhoto(PhotoEntity photo) {
		getPhotos().add(photo);
		photo.setPlace(this);

		return photo;
	}

	public void autoSetThis()
	{
		for (PhotoEntity photoentity : this.photos )
		{
			photoentity.setPlace(this);
		}
		
		for (ReviewEntity reviewEntity : this.reviews )
		{
			reviewEntity.setPlace(this);
		}
		
		placedetail.setPlaceId(this.placeId);

	}
	
	public PhotoEntity removePhoto(PhotoEntity photo) {
		getPhotos().remove(photo);
		photo.setPlace(null);

		return photo;
	}

	public PlacedetailEntity getPlacedetail() {
		return this.placedetail;
	}

	public void setPlacedetail(PlacedetailEntity placedetail) {
		this.placedetail = placedetail;
	}

	 public List<ReviewEntity> getReviews() {
	 return this.reviews;
	 }
	
	 public void setReviews(List<ReviewEntity> reviews) {
	 this.reviews = reviews;
	 }
	
	 public ReviewEntity addReview(ReviewEntity review) {
	 getReviews().add(review);
	 review.setPlace(this);
	
	 return review;
	 }
	
	 public ReviewEntity removeReview(ReviewEntity review) {
	 getReviews().remove(review);
	 review.setPlace(null);
	
	 return review;
	 }

	public List<SugestDataEntity> getSugestData() {
		return this.sugestData;
	}

	public void setSugestData(List<SugestDataEntity> sugestData) {
		this.sugestData = sugestData;
	}

	public SugestDataEntity addSugestData(SugestDataEntity sugestData) {
		getSugestData().add(sugestData);
		sugestData.setPlace(this);

		return sugestData;
	}

	public SugestDataEntity removeSugestData(SugestDataEntity sugestData) {
		getSugestData().remove(sugestData);
		sugestData.setPlace(null);

		return sugestData;
	}

}