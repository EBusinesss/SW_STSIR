/**
 * 
 */
package org.lmcu.siredo.jpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 * @author LAMGHARI Mohammed
 *
 */
@Entity
public class Station {
	
	
	  @Id
	  @SequenceGenerator( name = "id_StationSeq", sequenceName = "id_Station_seq", allocationSize = 1, initialValue = 1 )
	  @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_StationSeq" )
	  @Column( name = "id_Station" )
	  private int id_Station;
/*	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_Station;*/
	private String nom_Station;
	private String adr_Station;
	
	@ManyToOne
	@JoinColumn(name="id_Marque")
	private Marque marque;
	public String getNom_Station() {
		return nom_Station;
	}
	public void setNom_Station(String nom_Station) {
		this.nom_Station = nom_Station;
	}
	public Marque getMarque() {
		return marque;
	}
	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	public int getId_Station() {
		return id_Station;
	}
	public String getAdr_Station() {
		return adr_Station;
	}
	public void setAdr_Station(String adr_Station) {
		this.adr_Station = adr_Station;
	}
	
}
