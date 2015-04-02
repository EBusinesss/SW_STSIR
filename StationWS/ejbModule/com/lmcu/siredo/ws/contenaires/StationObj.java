package com.lmcu.siredo.ws.contenaires;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="StationObj")
@XmlType(name="StationObj", propOrder = {"id_Station","nom_Station","adr_Station","marque"})
public class StationObj{
	
	private int id_Station;
	private String nom_Station;
	private String adr_Station;
	private String marque;
	
	public int getId_Station() {
		return id_Station;
	}
	public void setId_Station(int id_Station) {
		this.id_Station = id_Station;
	}
	public String getNom_Station() {
		return nom_Station;
	}
	public void setNom_Station(String nom_Station) {
		this.nom_Station = nom_Station;
	}
	
	public String getAdr_Station() {
		return adr_Station;
	}
	public void setAdr_Station(String adr_Station) {
		this.adr_Station = adr_Station;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	
	
	
}
