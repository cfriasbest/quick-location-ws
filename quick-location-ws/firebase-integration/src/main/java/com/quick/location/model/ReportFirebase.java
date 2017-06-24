package com.quick.location.model;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@IgnoreExtraProperties
public class ReportFirebase {

	private String author;

	private String field;

	private String field_human;

	private String value;

	private boolean done;
}