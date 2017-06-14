package com.quick.location.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the reviews database table.
 * 
 */
@Entity
@Table(name="reviews")
@NamedQuery(name="ReviewEntity.findAll", query="SELECT r FROM ReviewEntity r")
public class ReviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idreviews;

	@Column(name="author_name")
	private String authorName;

	private String rating;

	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to PlaceEntity
	@ManyToOne
	@JoinColumn(name="place_id")
	private PlaceEntity place;

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

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public PlaceEntity getPlace() {
		return this.place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

}