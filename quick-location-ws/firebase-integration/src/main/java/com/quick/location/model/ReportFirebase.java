package com.quick.location.model;



import java.util.Date;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@IgnoreExtraProperties
public class ReportFirebase {

    private int idReport;
    
	private String author;

	private String field;

	private String field_human;

	private Object value;

	private Date date;
	
	private boolean done;
	
	private boolean remove;
}