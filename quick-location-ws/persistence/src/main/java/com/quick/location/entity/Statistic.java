package com.quick.location.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.dozer.Mapping;

import java.sql.Timestamp;


/**
 * The persistent class for the statistics database table.
 * 
 */
@Entity
@Table(name="statistics")
@NamedQuery(name="Statistic.findAll", query="SELECT s FROM Statistic s")
public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idStatistics;
    @Mapping("loginDate")
	private Timestamp fecha;

	private String nickname;

	private String placeId;

	public Statistic() {
	}

	public int getIdStatistics() {
		return this.idStatistics;
	}

	public void setIdStatistics(int idStatistics) {
		this.idStatistics = idStatistics;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}