package com.quick.location.model.firebase;

import java.util.Date;

import org.dozer.Mapping;

import com.google.firebase.database.Exclude;
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
public class ReviewFirebase {

    private int idreviews;

    private String authorName;
    private Double rating;
    @Mapping("text")
    private String comment;
    private String placeId;
    private boolean done;
    private boolean remove;

    private Date date;

    @Exclude
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

}
