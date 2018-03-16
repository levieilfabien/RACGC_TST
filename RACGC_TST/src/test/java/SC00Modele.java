package test.java;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import beans.CasEssaiBean;
import beans.ObjectifBean;
import constantes.Actions;
import exceptions.SeleniumException;
import extensions.SeleniumALMRESTWrapper;
import extensions.SeleniumConfluenceRESTWrapper;
import main.bean.CasEssaiRacgcBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.ALMOutils;
import outils.SeleniumOutils;
import outils.XLSOutils;
import java.sql.Date; 
public class SC00Modele extends CasEssaiRacgcBean{
	/**
	 * Id de serialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Créer le repertoire de téléchargement pour le cas d'essai.
	 * @param profile le profil firefox utilisé
	 * @param casEssai le cas d'essai
	 * @return le chemin du repertoire.
	 */
	public String creerRepertoireTelechargement(CasEssaiBean casEssai, FirefoxProfile profile) {
		File repertoireTelechargement = new File(".\\" + casEssai.getNomCasEssai());
		repertoireTelechargement.mkdir();
		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		return repertoireTelechargement.getAbsolutePath();
	}
	
	/**
	 * Configuration du profil pour test.
	 * @return Le profil utiliser sur le modèle du profil "Automate"
	 */
	public static FirefoxProfile configurerProfilNatixis() {
		
		// Initialisation du profil avec le profil automate requis
		ProfilesIni profileIni = new ProfilesIni();
		//FirefoxProfile profile = profileIni.getProfile("Automate");
		System.out.println(Constantes.EMPLACEMENT_PROFIL);
		FirefoxProfile profile = new FirefoxProfile(new File(Constantes.EMPLACEMENT_PROFIL));
		
		profile.setPreference("app.update.enabled", Boolean.FALSE);
		profile.setPreference("network.negotiate-auth.trusted-uris", "https://open-workplace.intranatixis.com/nfi/front-middle/wiki-izivente/Rfrentiel/Liens%20Izivente.aspx");
		profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "https://open-workplace.intranatixis.com/nfi/front-middle/wiki-izivente/Rfrentiel/Liens%20Izivente.aspx");
		
		profile.setPreference("browser.download.pluginOverrideTypes", "");
		profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf,application/vnd.fdf,application/vnd.adobe.xfdf,application/vnd.adobe.xdp+xml");
		
		profile.setPreference("pdfjs.disabled", Boolean.TRUE);
		profile.setPreference("pdfjs.firstRun", Boolean.FALSE);
		profile.setPreference("pdfjs.previousHandler.alwaysAskBeforeHandling", Boolean.FALSE);
		profile.setPreference("pdfjs.previousHandler.preferredAction", 4);
		profile.setPreference("pdfjs.disabled", Boolean.TRUE);
		
		profile.setPreference("browser.download.useDownloadDir", Boolean.TRUE);
		profile.setPreference("browser.download.manager.focusWhenStarting", Boolean.FALSE);
		profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", new File(".\\").getAbsolutePath());
        
        //browser.download.panel.shown
        profile.setPreference("browser.helperApps.alwaysAsk.force", Boolean.FALSE);
        profile.setPreference("browser.download.manager.showWhenStarting", Boolean.FALSE);
        profile.setPreference("browser.download.manager.useWindow", Boolean.FALSE);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,text/pdf,application/octet-stream,application/x-pdf,text/plain,text/xml");

		return profile;
	}
	
	/**
	 * Permet de finaliser le cas d'essai en erreur.
	 * @param outil la boite à outil
	 * @param casEssai le cas d'essai
	 * @param ex l'exception générant l'erreur
	 * @throws SeleniumException en cas d'erreur.
	 */
	public final void finaliserTestEnErreur(final SeleniumOutils outil, final CasEssaiBean casEssai, final SeleniumException ex, final String idObjectif) throws SeleniumException {
		ex.printStackTrace();
		casEssai.setCommentaire(ex.toString());
		logger(ex.toString());
		finaliserTest(outil, casEssai, idObjectif, false);
	}

	/**
	 * Finalise l'execution d'un test en renseignant les log du cas d'essai et du test à  partir des
	 * logs du driver.
	 * 
	 * @param outils le driver.
	 * @param casEssai le cas d'essai concerné par le test.
	 * @throws SeleniumException en cas d'erreur lors de la génération du fichier excel de rapport.
	 */
	private void finaliserTest(SeleniumOutils outils, CasEssaiBean casEssai, final String idObjectif, boolean succes) throws SeleniumException {
//		// On finalise aussi les sous cas.
//		for(CasEssaiBean sousCas : casEssai.getTests()) {
//			finaliserTest(outils, sousCas, casEssai.getNomCasEssai() + casEssai.getTime(), sousCas.getEtatFinal());
//		}
		// Si le driver n'est pas nul on effectue des capture d'écran et on récupère les logs.
		
		// On valide l'objectif en fonction du succès du cas de test.
		casEssai.validerObjectif(outils.getDriver(), idObjectif, succes);
		
		if(casEssai.getEtatFinal() == null) {
			//System.out.println("L'état final est : " + succes);
			casEssai.setEtatFinal(succes);
		}
		
		if (outils != null) {
			casEssai.setRegistreExecution(outils.getDriver());
			logger(casEssai.getRegistreExecution() + "\n" + casEssai.toString());
			if (casEssai.getRepertoireTelechargement() != null) {
				outils.captureEcran("captureFinale" + casEssai.getNomCasEssai(), casEssai.getRepertoireTelechargement());
			} else {
				outils.captureEcran("captureFinale" + casEssai.getNomCasEssai(), casEssai.getNomCasEssai());
			}
		}
		//setCasEssai(casEssai);

		logger(casEssai.toString());

		//TODO A remettre
//		if (outils != null) {
//			outils.getDriver().close();
//		    try {
//		    	outils.getDriver().quit();
//		    }catch(Exception e){
//		        System.out.println("Impossible de quitter le driver en raison d'une erreur.");
//		    }
//		}

		// On renseigne le rapport d'execution avec les données du cas de test.
		XLSOutils.renseignerExcel(casEssai);
		
		// On tente de mettre à jour ALM
		try {
			//ALMOutils.miseAJourTestSet(casEssai, succes);
			SeleniumALMRESTWrapper.miseAJourTestSet(casEssai, succes);
			SeleniumConfluenceRESTWrapper.miseAJourConfluence(casEssai);
			System.out.println("Mise à jour effectuée dans ALM");
		} catch (SeleniumException ex) {
			ex.printStackTrace();
			System.out.println("Mise à jour impossible à effectuée dans ALM : " + ex.getMessage());
		}	

	}
	
	/**
	 * Fonction de finalisation de test.
	 * @param outil la boite à outil.
	 * @param casEssai le cas d'essai.
	 * @param idObjectif l'id de l'objectif final.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public final void finaliserTest(SeleniumOutils outil, CasEssaiBean casEssai, final String idObjectif) throws SeleniumException {
		finaliserTest(outil, casEssai, idObjectif, true);
	}
	
	/**
	 * Permet d'ajouter une ligne dans le registre d'execution pour apporter plus d'informations.
	 * @param append la chaine de caractère à  ajouter dans le registre d'execution.
	 */
	public final void logger(final String append) {
		if (getLogs() != null) {
			setLogs(getLogs() + "\n" + append);
		} else {
			setLogs(append);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// FONCTION COMMUNES :
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String accesGoogle(SeleniumOutils outil) throws SeleniumException {
		
		String url = Constantes.URL_GOOGLE;
		String titre = Constantes.TITRE_PAGE;

		// Accès à google
		outil.chargerUrl(url);
		
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);

		return "OK";
	}
	
	/**
	 * Permet d'obtenir la boite a outil Selenium associe a un driver pour le scenario donne.
	 * @param scenario0 le scenario concerne.
	 * @return la boite a outil Selenium associee au scenario.
	 */
	public SeleniumOutils obtenirDriver(CasEssaiRacgcBean scenario0) {
		//Configuration du driver
		//FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();

		if (scenario0.getRepertoireTelechargement() == null) { 
			String repertoire = creerRepertoireTelechargement(scenario0, profile);
			scenario0.setRepertoireTelechargement(repertoire);
			this.setRepertoireTelechargement(repertoire);
		}
		// Initialisation du driver
		//FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		FirefoxImpl driver = new FirefoxImpl(profile);
		
		driver.get(Constantes.URL_GOOGLE);

		
	    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
		
		return outil;
	}
	
	/**
	 * Fonction de login à l'application AUG CMA.
	 * @param outil la boite à outil
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void identificationRacGc(SeleniumOutils outil) throws SeleniumException {
		// Remplir l'identifiant et validation
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_IDENTIFIANT, Constantes.ID_RACGC);
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_MOTDEPASSE, Constantes.PWD_RACGC);
		outil.action(Actions.CLIQUER, Cibles.VALIDER_LOGIN);
	}
	
	
	/**
	 * Fonction d'accès à l'IHM
	 * @param outil l'outil de manipulation selenium
	 * @throws SeleniumException en cas d'erreur
	 */
	public void accesRacGc(SeleniumOutils outil) throws SeleniumException {
		

		/////////////////////////////////////////////////// Configuration////////////////////////////////////////////////
		String url = Constantes.URL_APP_RACGC;
		String titre = Constantes.TITRE_PAGE_RACGC;
				
		System.out.println(url);

		// Accès à google
		outil.chargerUrl(url);
		
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);
	}	
	
	/**
	 * Cas de test decrivant l'affichage et la validation du formulaire de simulation OPENGO
	 * @param scenario0 le scénario auxquel appartient le cas de test
	 * @param outil la boite à outil
	 * @return le resultat du cas de test
	 * @throws SeleniumException en cas d'erreur
	 */
	public CasEssaiRacgcBean CT01AccesViaSimulationOPENGO(CasEssaiRacgcBean scenario0, SeleniumOutils outil) throws SeleniumException {

		// Déclaration du cas de test numéro 1
		CasEssaiRacgcBean CT01 = new CasEssaiRacgcBean();
		CT01.setAlm(scenario0.getAlm());
		CT01.setNomCasEssai("CT01 -" + getTime());
		CT01.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());	
		
		//Gestion des steps
		CT01.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Ouverture de l'url de la simu opengo", "ACCESSIMUOPENGO", "Le formulaire s'affiche");
		CT01.ajouterStep("Renseignement du formulaire et validation", "RENSEIGNEMENTFORMOPENGO", "L'accès à l'ihm se fait");
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		// Déroulement du cas de test
 		///////////////////////////////////////////////////////////////////////////////////////////////////////
		String url = Constantes.URL_APP_RACGC;
		String titre = Constantes.TITRE_PAGE_RACGC;
		// Accès à google
		outil.chargerUrl(url);
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);
		CT01.validerObjectif(outil.getDriver(), "ACCESSIMUOPENGO", true);
		
		// Remplir les données openGo
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.CD_DISTR, Constantes.CD_DISTR); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.CONTRAT_SICLID, Constantes.UNITED_CONTRAT); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.IUN, Constantes.IUN); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.ID_UNITED, Constantes.ID_UNITED); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.PWD_UNITED, Constantes.PWD_UNITED); 
        // Remplir le formulaire et le valider
        outil.action(Actions.CLIQUER, Cibles.ACCEDER_RACGC);
        //changer la fenetre pour afficher la fenetre réaménagement de créances
        outil.changerDeFenetre();
        
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);
		CT01.validerObjectif(outil.getDriver(), "RENSEIGNEMENTFORMOPENGO", true);
		
		return CT01;
	}

		
//		outil.action(Actions.CLIQUER, Cibles.VALIDER);
//		outil.action(Actions.VIDER_ET_SAISIR, Cibles.MENSUALITE, Constantes.MENSUALITE); 
//		outil.action(Actions.CLIQUER, Cibles.SIMULER);
//		outil.action(Actions.CLIQUER, Cibles.ENREGISTRER_SIMULER);
//		outil.action(Actions.CLIQUER, Cibles.ETAT_DU_DOSSIER);
//		outil.action(Actions.CLIQUER, Cibles.VALIDER_EN_CONTRAT_DE_CREDIT);
//		outil.action(Actions.CLIQUER, Cibles.VALIDER_FINALISATION_INSTRUCTION);
//		outil.action(Actions.CLIQUER, Cibles.VALIDER_DOSSIER);
//		
//		outil.action(Actions.CLIQUER, Cibles.EDITER);
//		outil.action(Actions.CLIQUER, Cibles.PASSER_ETUDE);
//		outil.action(Actions.CLIQUER, Cibles.FERMER_POPUP);
//		//System.out.println("coucou  SimpleDateFormat "+ );
//		outil.action(Actions.CLIQUER, Cibles.PROCEDER_A_ETUDE);
//
//		java.util.Date actuelle = new java.util.Date();
//		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		 String dat = dateFormat.format(actuelle);
//		 System.out.println("======================");
//		 System.out.println("La date affichée est: " + dat);
//		
//		outil.action(Actions.CLIQUER, Cibles.DECISION_REFUS);
//		outil.action(Actions.SELECTIONNER, Cibles.MOTOF_REFUS, "Contrat non valide"); 
//		outil.action(Actions.VIDER_ET_SAISIR, Cibles.DATESIGNATURE, dat);
//		
//		outil.action(Actions.CLIQUER, Cibles.FINALISER_OCTROI);
//		outil.action(Actions.CLIQUER, Cibles.VALIDER_FINALISATION_OCTROI);
//		outil.action(Actions.CLIQUER, Cibles.QUITTER);
//	}
	
	public void accederARacGc(SeleniumOutils outil) throws SeleniumException {

		String url = Constantes.URL_APP_RACGC;
		String titre = Constantes.TITRE_PAGE_RACGC;
		// Accès à google
		outil.chargerUrl(url);
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);
		
		// Remplir les données openGo
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.CD_DISTR, Constantes.CD_DISTR); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.CONTRAT_SICLID, Constantes.UNITED_CONTRAT); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.IUN, Constantes.IUN); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.ID_UNITED, Constantes.ID_UNITED); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.PWD_UNITED, Constantes.PWD_UNITED); 
        // Remplir le formulaire et le valider
        outil.action(Actions.CLIQUER, Cibles.ACCEDER_RACGC);
        //changer la fenetre pour afficher la fenetre réaménagement de créances
        outil.changerDeFenetre();
        
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);
		outil.action(Actions.CLIQUER, Cibles.VALIDER);
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.MENSUALITE, Constantes.MENSUALITE); 
		outil.action(Actions.CLIQUER, Cibles.SIMULER);
		outil.action(Actions.CLIQUER, Cibles.ENREGISTRER_SIMULER);
		outil.action(Actions.CLIQUER, Cibles.ETAT_DU_DOSSIER);
		outil.action(Actions.CLIQUER, Cibles.VALIDER_EN_CONTRAT_DE_CREDIT);
		outil.action(Actions.CLIQUER, Cibles.VALIDER_FINALISATION_INSTRUCTION);
		outil.action(Actions.CLIQUER, Cibles.VALIDER_DOSSIER);
		
		outil.action(Actions.CLIQUER, Cibles.EDITER);
		outil.action(Actions.CLIQUER, Cibles.PASSER_ETUDE);
		outil.action(Actions.CLIQUER, Cibles.FERMER_POPUP);
		//System.out.println("coucou  SimpleDateFormat "+ );
		outil.action(Actions.CLIQUER, Cibles.PROCEDER_A_ETUDE);

		java.util.Date actuelle = new java.util.Date();
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String dat = dateFormat.format(actuelle);
		 System.out.println("======================");
		 System.out.println("La date affichée est: " + dat);
		
		outil.action(Actions.CLIQUER, Cibles.DECISION_REFUS);
		outil.action(Actions.SELECTIONNER, Cibles.MOTOF_REFUS, "Contrat non valide"); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.DATESIGNATURE, dat);
		
		outil.action(Actions.CLIQUER, Cibles.FINALISER_OCTROI);
		outil.action(Actions.CLIQUER, Cibles.VALIDER_FINALISATION_OCTROI);
		outil.action(Actions.CLIQUER, Cibles.QUITTER);
	}
	
}
