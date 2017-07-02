//package com.quick.location.model;
//
//import com.fasterxml.jackson.annotation.JsonSetter;
//import com.google.firebase.database.IgnoreExtraProperties;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
///**
// * 
// * @author cfriasb
// *
// */
//@Getter
//@Setter
//@ToString
//@IgnoreExtraProperties
//public class PlaceDetail extends Place {
//
//    private static final long serialVersionUID = 1L;
//    private String formattedAddress;
//    private String formattedPhoneNumber;
//    private OpeningHours openingHours;
//    private String url;
//    private String website;
//    // private List<Review> reviews;
//
//    @JsonSetter("formatted_address")
//    public void setFormattedAddress(String formattedAddress) {
//        this.formattedAddress = formattedAddress;
//    }
//
//    @JsonSetter("formatted_phone_number")
//    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
//        this.formattedPhoneNumber = formattedPhoneNumber;
//    }
//
//    @JsonSetter("opening_hours")
//    public void setOpeningHours(OpeningHours openingHours) {
//        this.openingHours = openingHours;
//    }
//
//}