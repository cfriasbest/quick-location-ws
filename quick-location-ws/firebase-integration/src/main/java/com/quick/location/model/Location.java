package com.quick.location.model;

import java.io.Serializable;

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
public class Location implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1210299918826349135L;
	private float lat;
	private float lng;

}
