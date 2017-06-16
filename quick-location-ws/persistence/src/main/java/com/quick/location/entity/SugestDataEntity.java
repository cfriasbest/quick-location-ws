package com.quick.location.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.ToString;


/**
 * The persistent class for the sugest_data database table.
 * 
 */
@Entity
@Table(name="sugest_data")
@NamedQuery(name="SugestDataEntity.findAll", query="SELECT s FROM SugestDataEntity s")
@ToString
public class SugestDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_Sugest_Data")
	private String idSugestData;

	private String direction;

	private String phone;

	@Column(name="place_id")
	private String placeId;

	private String schedule;

	private String state;

	private String user;

	public SugestDataEntity() {
	}

	public String getIdSugestData() {
		return this.idSugestData;
	}

	public void setIdSugestData(String idSugestData) {
		this.idSugestData = idSugestData;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}