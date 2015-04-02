package com.lmcu.siredo.ws.remote;

import javax.ejb.Remote;

@Remote
public interface MarqueRemote {

	/**
	 * @param nomMarque
	 * @return une confirmation d'ajout d'une marque dans la base si elle n'existe pas.
	 */
	public String ajoutMarque(String nomMarque);
	
}
