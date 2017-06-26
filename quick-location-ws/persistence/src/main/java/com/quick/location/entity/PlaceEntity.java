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

	private String formattedAddress;

	private String formattedPhoneNumber;
	@Mapping("geometry.location.lat")
	private float lat;
	@Mapping("geometry.location.lng")
	private float lng;

	private String name;

	private float rating;

	private String vicinity;
	
	private String url;

	private long reviewsCount;

	private long updatesCount;

	// bi-directional many-to-one association to ReviewEntity
	@OneToMany(mappedBy = "place")
    @Column(insertable=false, updatable=true)
	private List<ReviewEntity> reviews;

	// bi-directional many-to-one association to ReportEntity
	@OneToMany(mappedBy = "place", cascade = { CascadeType.ALL })
    @Column(insertable=false, updatable=true)
	private List<ReportEntity> reports;

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getFormattedAddress() {
		return this.formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedPhoneNumber() {
		return this.formattedPhoneNumber;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
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



	public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
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

	public List<ReportEntity> getReports() {
		return this.reports;
	}

	public void setReports(List<ReportEntity> reports) {
		this.reports = reports;
	}

	public ReportEntity addReport(ReportEntity report) {
		getReports().add(report);
		report.setPlace(this);

		return report;
	}

	public ReportEntity removeReport(ReportEntity report) {
		getReports().remove(report);
		report.setPlace(null);

		return report;
	}

	public long getReviewsCount() {
		return reviewsCount;
	}

	public void setReviewsCount(long reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

	public long getUpdatesCount() {
		return updatesCount;
	}

	public void setUpdatesCount(long updatesCount) {
		this.updatesCount = updatesCount;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}