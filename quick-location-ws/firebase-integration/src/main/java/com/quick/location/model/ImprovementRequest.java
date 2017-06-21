package com.quick.location.model;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImprovementRequest {
    private String placeId;
    private List<ImprovementInformation> informations;
    private String author;

  
}