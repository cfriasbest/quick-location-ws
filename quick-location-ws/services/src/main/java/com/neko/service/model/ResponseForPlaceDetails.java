package com.neko.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ResponseForPlaceDetails {
    private PlaceDetail result;
    private String status;

}
