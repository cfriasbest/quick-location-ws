package com.quick.location.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the placedetails database table.
 * 
 */
@Entity
@Table(name="placedetails")
@NamedQuery(name="PlacedetailEntity.findAll", query="SELECT p FROM PlacedetailEntity p")
public class PlacedetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int place_Details_id;
	
	private String formattedAddress;

	private String formattedPhoneNumber;
	@Id
	@Column(name="place_id")
	private String placeId;

	private String url;

	private String website;

	public PlacedetailEntity() {
	}

	public int getPlace_Details_id() {
		return this.place_Details_id;
	}

	public void setPlace_Details_id(int place_Details_id) {
		this.place_Details_id = place_Details_id;
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

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}