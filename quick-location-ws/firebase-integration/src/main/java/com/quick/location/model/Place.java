package com.quick.location.model;

import java.io.Serializable;

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

    @JsonSetter("place_id")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @JsonGetter("place_id")
    public String getPlaceId() {
        return placeId;
    }

}
