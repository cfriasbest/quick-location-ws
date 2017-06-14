package com.quick.location.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author cfriasb
 *
 */
@ToString
@Setter
@Getter
public class ResponseForPlaces implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093435704950589961L;
	private List<Place> results;
	private String status;

}
