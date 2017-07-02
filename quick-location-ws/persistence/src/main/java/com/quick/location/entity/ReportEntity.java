package com.quick.location.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.dozer.Mapping;

/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@Table(name = "report")
@NamedQuery(name = "ReportEntity.findAll", query = "SELECT r FROM ReportEntity r")
public class ReportEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_report")
    private int idReport;

    private String author;

    private String field;

    @Mapping("field_human")
    @Column(name = "field_human")
    private String fieldHuman;
    @Column(insertable = false, updatable = false)
    private Timestamp date;

    @Lob
    private String value;
    @Column(name = "place_id")
    private String placeId;

    private boolean done;

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldHuman() {
        return this.fieldHuman;
    }

    public void setFieldHuman(String fieldHuman) {
        this.fieldHuman = fieldHuman;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getIdReport() {
        return idReport;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}