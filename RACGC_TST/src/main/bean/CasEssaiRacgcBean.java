package main.bean;

import beans.CasEssaiBean;

public class CasEssaiRacgcBean extends CasEssaiBean {


	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Dossier RAC GC
	 */
	private String numeroDossier = "41004660822100";
	
	/**
	 * IUN du client
	 */
	private String numeroIUN = "791648";
	
	/**
	 * Distributeur concerné
	 */
	private String distributeur = "BP";


	/**
	 * Constructeur par défaut d'un scénario modèle.
	 */
	public CasEssaiRacgcBean() {
		super();
	}


	public String getNumeroDossier() {
		return numeroDossier;
	}


	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}


	public String getNumeroIUN() {
		return numeroIUN;
	}


	public void setNumeroIUN(String numeroIUN) {
		this.numeroIUN = numeroIUN;
	}


	public String getDistributeur() {
		return distributeur;
	}


	public void setDistributeur(String distributeur) {
		this.distributeur = distributeur;
	}
	
}
