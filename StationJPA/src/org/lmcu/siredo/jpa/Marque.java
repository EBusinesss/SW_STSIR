package org.lmcu.siredo.jpa;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Marque {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_Marque; 
	private String nom_Marque;
	
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="marque")
	Collection<Station> stations;

	public String getNom_Marque() {
		return nom_Marque;
	}

	public void setNom_Marque(String nom_Marque) {
		this.nom_Marque = nom_Marque;
	}

	public Collection<Station> getStations() {
		return stations;
	}

	public void setStations(Collection<Station> stations) {
		this.stations = stations;
	}

	public int getId_Marque() {
		return id_Marque;
	}
	
	
	
}
