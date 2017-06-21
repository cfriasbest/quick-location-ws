package com.quick.location.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sugest_data database table.
 * 
 */
@Entity
@Table(name="sugest_data")
@NamedQuery(name="SugestDataEntity.findAll", query="SELECT s FROM SugestDataEntity s")
public class SugestDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_Sugest_Data")
	private String idSugestData;

	private String direction;

	private String phone;

	private String schedule;

	private String state;

	private String user;

	//bi-directional many-to-one association to PlaceEntity
//	@ManyToOne
//	@JoinColumn(name="place_id")
//	private PlaceEntity place;

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

//	public PlaceEntity getPlace() {
//		return this.place;
//	}
//
//	public void setPlace(PlaceEntity place) {
//		this.place = place;
//	}

}