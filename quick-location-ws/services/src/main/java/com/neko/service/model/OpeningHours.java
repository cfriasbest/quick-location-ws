package com.neko.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OpeningHours {
    private List<String> weekdayText;
    private boolean openNow;

    @JsonSetter("weekday_text")
    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

}