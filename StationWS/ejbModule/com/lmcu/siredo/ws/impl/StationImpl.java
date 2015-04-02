package com.lmcu.siredo.ws.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.lmcu.siredo.jpa.Marque;
import org.lmcu.siredo.jpa.Station;

import com.lmcu.siredo.ws.contenaires.StationObj;
import com.lmcu.siredo.ws.remote.StationRemote;

@Stateless
@Path("Siredo")
@Consumes("application/json")
@Produces("application/json")
public class StationImpl implements StationRemote {

	private EntityManagerFactory emf;
	private EntityManager em;

	/**
	 * Web service qui retourne la liste des stations siredo
	 */
	@GET
	@Path("getStations")
	@Produces({ "application/json" })
	public Collection<StationObj> getStations() {
		
		this.emf = Persistence.createEntityManagerFactory("Persist");
		this.em = emf.createEntityManager();
		
		Collection<StationObj> ls = new ArrayList<StationObj>();
		Collection<Station> lista = new ArrayList<Station>();
		try {
			Query req = em.createQuery("SELECT s FROM Station s");
			lista = req.getResultList();
		} catch (Exception e) {
			return null;
		}
		
		
		for (Station station : lista) {
			StationObj stObj = new StationObj();
			
			stObj.setId_Station(station.getId_Station());
			stObj.setNom_Station(station.getNom_Station());
			stObj.setAdr_Station(station.getAdr_Station());
			stObj.setMarque(station.getMarque().getNom_Marque());
			ls.add(stObj);
		}
		

		
		return ls;
	}

	/**
	 * Web service qui retourne la liste des stations siredo
	 */
	@GET
	@Path("getStationsParMarque/{nom_Marque}")
	@Produces({ "application/json" })
	public Collection<StationObj> getStationsParMarque(@PathParam("nom_Marque") String nom_Marque) {
		
		this.emf = Persistence.createEntityManagerFactory("Persist");
		this.em = emf.createEntityManager();
		
		Collection<StationObj> ls = new ArrayList<StationObj>();
		Collection<Station> lista = new ArrayList<Station>();
		try {
			Query req = em.createQuery("SELECT s FROM Station s");
			lista = req.getResultList();
		} catch (Exception e) {
			return null;
		}
		
		
		for (Station station : lista) {

			if (nom_Marque.equals(station.getMarque().getNom_Marque())) {
				StationObj stObj = new StationObj();
				
				stObj.setId_Station(station.getId_Station());
				stObj.setNom_Station(station.getNom_Station());
				stObj.setAdr_Station(station.getAdr_Station());
				stObj.setMarque(station.getMarque().getNom_Marque());
				ls.add(stObj);
			}
			
		}
		

		
		return ls;
	}

	
	/**
	 * Web service qui crée une station siredo
	 */
	@Override
	@GET
	@Path("creerStation/{nomStation}/{adrStation}/{marqueStation}")
	@Produces({ "application/json" })
	public String creerStation(@PathParam("nomStation") String nomStation,
			@PathParam("adrStation") String adrStation,
			@PathParam("marqueStation") String marqueStation) {
		String resultat;
		this.emf = Persistence.createEntityManagerFactory("Persist");
		this.em = emf.createEntityManager();

		Marque m = new Marque();
		String p_marq = marqueStation;
		try {
			Query req = em
					.createQuery("SELECT m FROM Marque m WHERE m.nom_Marque like :x");
			req.setParameter("x", p_marq);
			m = (Marque) req.getSingleResult();
		} catch (Exception e) {
			resultat = e.toString();
		}

		String name_param = nomStation;
		String adr_param = adrStation;
		
		try {
			Query req = em
					.createQuery("SELECT Count(s) FROM Station s WHERE s.nom_Station like :x OR s.adr_Station like :y");
			req.setParameter("x", name_param);
			req.setParameter("y", adr_param);
			long nb = (long) req.getSingleResult();

			if (p_marq.equals(m.getNom_Marque())) {
				if (nb == 0) {
					Station s = new Station();
					s.setNom_Station(nomStation);
					s.setAdr_Station(adrStation);
					s.setMarque(m);

					em.getTransaction().begin();
					this.em.persist(s);
					em.getTransaction().commit();

					resultat = "Station "
							+ nomStation
							+ " cree avec l'Id :"
							+ String.valueOf(s.getId_Station()
									+ " Nom Marque :" + m.getNom_Marque());
				} else {
					resultat = "La station " + nomStation + " ou le numero de station "+adrStation+" existe deja !!!";
				}
			} else {
				resultat = "La station " + nomStation + " existe déja !!!";
			}

		} catch (NoResultException e) {
			resultat = e.toString();
		}

		return resultat;

	}

}
