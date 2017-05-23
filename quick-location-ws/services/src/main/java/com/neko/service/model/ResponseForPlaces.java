package com.neko.service.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ResponseForPlaces {
    private List<Place> results;
    private String status;

}
