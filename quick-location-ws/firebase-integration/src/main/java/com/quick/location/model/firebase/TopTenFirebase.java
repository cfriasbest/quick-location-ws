package com.quick.location.model.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@IgnoreExtraProperties
public class TopTenFirebase {
    
    String name;
    long count;
    long porcent;

}
