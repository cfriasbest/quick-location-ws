package com.quick.location.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.firebase.database.IgnoreExtraProperties;

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
@IgnoreExtraProperties
public class OpeningHours {

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