package com.quick.location.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SugestData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idSugestData;

	private String direction;

	private String phone;

	private String placeId;

	private String schedule;

	private String state;

	private String user;

}