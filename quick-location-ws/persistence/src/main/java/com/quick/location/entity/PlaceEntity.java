package com.quick.location.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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