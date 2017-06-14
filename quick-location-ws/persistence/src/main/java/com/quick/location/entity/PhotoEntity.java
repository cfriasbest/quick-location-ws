package com.neko.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@Table(name="photo")
@NamedQuery(name="PhotoEntity.findAll", query="SELECT p FROM PhotoEntity p")
public class PhotoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String photoReference;


	//bi-directional many-to-one association to PlaceEntity
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="place_id")
	private PlaceEntity place;

	public PhotoEntity() {
	}

	public String getPhotoReference() {
		return this.photoReference;
	}

	public void setPhotoReference(String photoReference) {
		this.photoReference = photoReference;
	}

	public PlaceEntity getPlace() {
		return this.place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

}