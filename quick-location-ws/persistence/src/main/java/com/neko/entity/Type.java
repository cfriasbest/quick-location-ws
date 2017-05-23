package com.neko.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idType;

	private String typecol;

	//bi-directional many-to-many association to Place
	@ManyToMany
	@JoinTable(
		name="place_has_type"
		, joinColumns={
			@JoinColumn(name="idType")
			}
		, inverseJoinColumns={
			@JoinColumn(name="place_id")
			}
		)
	private List<Place> places;

	public Type() {
	}

	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getTypecol() {
		return this.typecol;
	}

	public void setTypecol(String typecol) {
		this.typecol = typecol;
	}

	public List<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

}