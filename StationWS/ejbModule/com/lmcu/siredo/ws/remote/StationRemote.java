package com.lmcu.siredo.ws.remote;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import org.lmcu.siredo.jpa.Station;

import com.lmcu.siredo.ws.contenaires.StationObj;


@Remote
public interface StationRemote {

	/**
	 * 
	 * @return une liste de station siredo
	 */
	public Collection<StationObj> getStations();
	/**
	 * 
	 * @return une liste de station siredo par marque
	 */
	public Collection<StationObj> getStationsParMarque(String nom_Marque);
	
	
	/**
	 * @crée une station dans la bdd
	 * @param nomStation
	 * @param adrStation
	 * @param marqueStation
	 * @return si une station a été bien crée ou pas
	 */
	public String creerStation(String nomStation,String adrStation,String marqueStation);	
}
