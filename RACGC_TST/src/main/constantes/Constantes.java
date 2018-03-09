package main.constantes;

import outils.PropertiesOutil;

public class Constantes {
	
	//////////////////////////////////////////////////// INFORMATIONS TECHNIQUES ////////////////////////////////////////////////////////////////
	public static final String EMPLACEMENT_FIREFOX = PropertiesOutil.getInfoConstante("EMPLACEMENT_FIREFOX");
	public static final String EMPLACEMENT_PROFIL = PropertiesOutil.getInfoConstante("EMPLACEMENT_PROFILE");
	public static final String EMPLACEMENT_GECKO =  System.setProperty("webdriver.gecko.driver", PropertiesOutil.getInfoConstante("EMPLACEMENT_GECKO"));
	public static final String EMPLACEMENT_LIASSE = PropertiesOutil.getInfoConstante("EMPLACEMENT_LIASSE");
	////////////////////////////////////////////////////INFORMATIONS POUR LES PREFERENCES ////////////////////////////////////////////////////////////
	public static final String PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT = "browser.download.dir";
	//////////////////////////////////////////////////// INFORMATIONS POUR LES TESTS ////////////////////////////////////////////////////////////
	public static final String URL_GOOGLE = PropertiesOutil.getInfoConstante("URL_TEST");
	public static final String TITRE_PAGE = "Google";
	
	//////////////////////////////////////////////////// INFORMATIONS POUR LES TESTS ////////////////////////////////////////////////////////////
	
	public static final String ID_RACGC = PropertiesOutil.getInfoConstante("RACGC.login");
	public static final String PWD_RACGC = PropertiesOutil.getInfoConstante("RACGC.password");
	public static final String URL_APP_RACGC = PropertiesOutil.getInfoConstante("URL_RACGC");
	public static final String TITRE_PAGE_RACGC = "nxs";
	
	
// Données opengo de RAC GC

	public static final String CD_DISTR = PropertiesOutil.getInfoConstante("CD_DISTR");
	public static final String UNITED_CONTRAT = PropertiesOutil.getInfoConstante("UNITED.CONTRAT");
	public static final String IUN = PropertiesOutil.getInfoConstante("IUN");
	public static final String ID_UNITED = PropertiesOutil.getInfoConstante("UNITED.login");
	public static final String PWD_UNITED = PropertiesOutil.getInfoConstante("UNITED.password");
	public static final String MENSUALITE = PropertiesOutil.getInfoConstante("MENSUALITE");
}
