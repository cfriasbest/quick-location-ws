package com.neko.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the placedetails database table.
 * 
 */
@Entity
@Table(name="placedetails")
public class Placedetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int place_Details_id;

	private String formattedAddress;

	private String formattedPhoneNumber;

	private String placedetailscol;

	private String url;

	private String website;

	//bi-directional one-to-one association to Place
	@OneToOne( fetch=FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name="place_id")
	private Place place;

	public Placedetail() {
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

	public String getPlacedetailscol() {
		return this.placedetailscol;
	}

	public void setPlacedetailscol(String placedetailscol) {
		this.placedetailscol = placedetailscol;
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

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}