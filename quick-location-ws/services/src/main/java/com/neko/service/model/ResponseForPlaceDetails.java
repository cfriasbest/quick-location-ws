package com.neko.service.model;

import java.io.Serializable;

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
public class ResponseForPlaceDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6538505585882628252L;
	private PlaceDetail result;
	private String status;

}
