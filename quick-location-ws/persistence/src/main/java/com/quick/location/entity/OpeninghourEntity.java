package com.quick.location.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the openinghours database table.
 * 
 */
@Entity
@Table(name = "openinghours")
@NamedQuery(name = "OpeninghourEntity.findAll", query = "SELECT o FROM OpeninghourEntity o")
public class OpeninghourEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idopenHours;

	@Column(name = "place_id")
	private String placeId;

	@Column(name = "weekday_text")
	private String weekdayText;

	public int getIdopenHours() {
		return this.idopenHours;
	}

	public void setIdopenHours(int idopenHours) {
		this.idopenHours = idopenHours;
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getWeekdayText() {
		return this.weekdayText;
	}

	public void setWeekdayText(String weekdayText) {
		this.weekdayText = weekdayText;
	}

}