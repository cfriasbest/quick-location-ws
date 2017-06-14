package com.quick.location.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author cfriasb
 *
 */
@Setter
@Getter
@ToString
public class OpeningHours implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 193143646932349549L;
	private List<String> weekdayText;
	private boolean openNow;

	@JsonSetter("weekday_text")
	public void setWeekdayText(List<String> weekdayText) {
		this.weekdayText = weekdayText;
	}
	
	@JsonGetter("weekday_text")
	public List<String> getWeekdayText() {
		return this.weekdayText;
	}

}