package com.neko.service.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Photo {
    private String photoReference;
    private Long height;
    private Long width;
   

    @JsonSetter("photo_reference")
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}
