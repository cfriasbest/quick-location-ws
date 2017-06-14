package com.quick.location.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author cfriasb
 *
 */
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_EMPTY)
public class Place implements Serializable {

	private static final long serialVersionUID = 1L;

	private Geometry geometry;
	private String name;
	private String placeId;
	private float rating;
	private List<String> types;
	private List<Object> addressComponents;
//	private String icon;
//	private String id;
//	private String reference;
//	private String scope;
//	private String vicinity;

	@JsonSetter("place_id")
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@JsonSetter("address_components")
	public void setAddressComponents(List<Object> addressComponents) {
		this.addressComponents = addressComponents;
	}
	@JsonGetter("place_id")
	public String getPlaceId() {
		return placeId;
	}
	
	@JsonGetter("address_components")
	public List<Object> getAddressComponents() {
		return addressComponents;
	}

	
}
