package com.neko.service.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Place implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Geometry geometry;
    private String name;
    private String placeId;
    private double rating;
    private List<String> types;
    private List<Object> addressComponents;
    private String icon;
    private String id;
    private String reference;
    private String scope;
    private String vicinity;
    
    
    @JsonSetter("place_id")
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
    @JsonSetter("address_components")
    public void setAddressComponents(List<Object> addressComponents){
    	this.addressComponents=addressComponents;
    }
    
}
