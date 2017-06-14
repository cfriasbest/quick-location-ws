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
public class Geometry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1901521289425520826L;
	private Location location;

}
