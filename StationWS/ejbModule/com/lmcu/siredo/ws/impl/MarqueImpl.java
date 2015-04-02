package com.lmcu.siredo.ws.impl;

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

import com.lmcu.siredo.ws.remote.MarqueRemote;

@Stateless
@Path("Marque")
@Consumes("application/json")
@Produces("application/json")
public class MarqueImpl implements MarqueRemote {

	private EntityManagerFactory emf;
	private EntityManager em;

	/**
	 * Web service qui ajoute une marque de station
	 */
	@Override
	@GET
	@Path("ajoutMarque/{nomMarque}")
	@Produces({ "application/json" })
	public String ajoutMarque(@PathParam("nomMarque") String nomMarque) {

		String resultat;
		this.emf = Persistence.createEntityManagerFactory("Persist");
		this.em = emf.createEntityManager();

		String param = nomMarque;
		try {
			Query req = em
					.createQuery("SELECT Count(m) FROM Marque m WHERE m.nom_Marque like :x");
			req.setParameter("x", param);
			long nb = (long) req.getSingleResult();

			if (nb == 0) {
				Marque m = new Marque();
				m.setNom_Marque(nomMarque);

				em.getTransaction().begin();
				this.em.persist(m);
				em.getTransaction().commit();

				resultat = "Marque " + nomMarque + " cree avec l'Id :"
						+ String.valueOf(m.getId_Marque());
			} else {
				resultat = "La marque " + nomMarque + " existe déja !!!";
			}

		} catch (NoResultException e) {
			resultat = e.toString();
		}

		return resultat;

	}

}
