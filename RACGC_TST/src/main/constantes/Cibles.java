package main.constantes;

import beans.CibleBean;
import constantes.Clefs;

public class Cibles {

	//////EXEMPLE SUR RAC GC //////
	/**
	 * Champ de saisie pour l'identifiant RACGC.
	 */
	public static final CibleBean SAISIE_IDENTIFIANT = new CibleBean(Clefs.NAME, "j_username");
	/**
	 * Champ de saisie pour le mot de passe RACGC.
	 */
	public static final CibleBean SAISIE_MOTDEPASSE = new CibleBean(Clefs.NAME, "j_password");
	/**
	 * Bouton de recherche google.
	 */
	public static final CibleBean VALIDER_LOGIN = new CibleBean(Clefs.VALEUR, "Valider");
	
	// Nouvelle instruction avec simulation deja en cours
	
	/**
	 * Bouton d'annulation de simulation.
	 */
	public static final CibleBean METTRE_SANS_SUITE = new CibleBean(Clefs.NAME, "majClientForm:mettreSsSuite");
	
	/**
	 * Bouton radio motif annulation
	 */
	public static final CibleBean MOTIF_ANNULATION = new CibleBean(Clefs.VALEUR, "Agence");

	/**
	 * Bouton de soumission des données clients
	 */
	public static final CibleBean BOUTON_SOUMETTRE_DONNEES_CLIENTS = new CibleBean(Clefs.NAME, "majClientForm:soumettreDonneeSubmit");
	
	/**
	 * Remplir les données openGo : distributeur
	 */
	public static final CibleBean CD_DISTR = new CibleBean(Clefs.ID, "distributeur");
	/**
	 * Remplir les données openGo : Contrat SICLID
	 */
	public static final CibleBean CONTRAT_SICLID = new CibleBean(Clefs.ID, "numContrat");
	/**
	 * Remplir les données openGo:IUN 
	 */
	public static final CibleBean IUN = new CibleBean(Clefs.ID, "iun");
	/**
	 * Remplir les données openGo: ID UNITED
	 */
	public static final CibleBean ID_UNITED = new CibleBean(Clefs.ID, "idCommercial");
	/**
	 * Remplir les données openGo: PWD UNITED
	 */
	public static final CibleBean PWD_UNITED = new CibleBean(Clefs.ID, "mdpCommercial");
	/**
	 * Le bouton accéder à RAC GC
	 */
	public static final CibleBean ACCEDER_RACGC = new CibleBean(Clefs.VALEUR, "Accéder à RAC GC");
	/**
	 * Le bouton valider de la fenetre reamenangement de créances.
	 */
	public static final CibleBean VALIDER = new CibleBean(Clefs.NAME, "racGCForm:doValidate");
	/**
	 * SAisir la valeur du champ mensualité
	 */
	public static final CibleBean MENSUALITE = new CibleBean(Clefs.ID, "racGCForm:mensualiteProp:mensualiteInputText");
	
	public static final CibleBean SIMULER = new CibleBean(Clefs.ID, "racGCForm:doSimulate");
	/**
	 * Le bouton enregistrer la simulation
	 */
	public static final CibleBean ENREGISTRER_SIMULER = new CibleBean(Clefs.ID, "racGCForm:doSave");
	/**
	 * Cocher la case à cocher etat du dossier
	 */
	public static final CibleBean ETAT_DU_DOSSIER = new CibleBean(Clefs.ID, "racGCForm:j_idt27:0:refDossierRadio:0");
	/**
	 * Cliquer sur le bouton valider en contrat de crédit
	 */
	public static final CibleBean VALIDER_EN_CONTRAT_DE_CREDIT = new CibleBean(Clefs.ID, "racGCForm:doValidateContrat");
	/**
	 * Valider la pop up finalisation de l'instruction
	 */
	public static final CibleBean VALIDER_FINALISATION_INSTRUCTION = new CibleBean(Clefs.ID, "racGCForm:confirmerValidation");
	/**
	 * Valider l'instruction du dossier et la liste des participants 
	 */
	public static final CibleBean VALIDER_DOSSIER = new CibleBean(Clefs.ID, "racGCForm:doValiderInstruction");
	/**
	 * Editer le contrat   
	 */
	public static final CibleBean EDITER = new CibleBean(Clefs.ID, "racGCForm:doEditer");
	/**
	 * Cliquer sur le bouton passer à l'étude
	 */
	public static final CibleBean PASSER_ETUDE = new CibleBean(Clefs.ID, "racGCForm:doPasserALEtude");
	/**
	 * Fermer le pop up reception des éléments du dossier
	 */
	public static final CibleBean FERMER_POPUP = new CibleBean(Clefs.ID, "racGCForm:fermerPopupReceptionElementsDossier");
	/**
	 * Cliquer sur le dossier procéder à l'étude
	 */
	public static final CibleBean PROCEDER_A_ETUDE = new CibleBean(Clefs.ID, "racGCForm:doProcederEtude");
	/**
	 * Cliquer sur le calendrier
	 */
	public static final CibleBean CALENDRIER = new CibleBean(Clefs.ID, "racGCForm:dateSignature:dateCalPopupButton");
	
	/**
	 * Le bouton finaliser l'octroi
	 */
	public static final CibleBean FINALISER_OCTROI = new CibleBean(Clefs.ID, "racGCForm:doFinaliserOctroi");
	/**
	 * La date du signature
	 */
	public static final CibleBean DATESIGNATURE = new CibleBean(Clefs.ID, "racGCForm:dateSignature:dateCalInputDate");
	/**
	 * Saisir une décision refus 
	 */
	public static final CibleBean DECISION_REFUS = new CibleBean(Clefs.ID, "racGCForm:decisionRadio:1");
	/**
	 * Motif de refus
	 */
	public static final CibleBean MOTOF_REFUS = new CibleBean(Clefs.ID, "racGCForm:listMotifsRefus:motifDecision");
	/**
	 * Valider le pop up: finalisation de l'octroi
	 */
	public static final CibleBean VALIDER_FINALISATION_OCTROI = new CibleBean(Clefs.ID, "racGCForm:doValiderRefus");
	/**
	 * Le bouton quitter l'application
	 */
	public static final CibleBean QUITTER = new CibleBean(Clefs.ID, "racGCForm:doExitOctroi");
	
}
