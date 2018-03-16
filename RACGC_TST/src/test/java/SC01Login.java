package test.java;
import org.junit.Test;

import exceptions.SeleniumException;
import outils.SeleniumOutils;

public class SC01Login extends SC00Modele {
	
	/**
	 * Id de serialisation.
	 */
	private static final long serialVersionUID = 1L;
	@Test
	public void accesTest() throws SeleniumException {
		
		this.setNomCasEssai("TNRSC01-" + getTime());
		this.setDescriptif("TNRSC01 - RAC GC - BP - Octroi avec refus");
		this.setIdConfluence("");
		// Valorisation des données
		setNumeroDossier("41004660822100");
		setNumeroIUN("791648");
		setDistributeur("BP");

		/////////////////////////////////////////////////// Configuration////////////////////////////////////////////////
		SeleniumOutils outil = obtenirDriver(this);
		/////////////////////////////////////////////////// EXECUTION////////////////////////////////////////////////
		
		try {
		
		// Accès à RACGC
		accesRacGc(outil);
		// S'identifier sur l'appliation
		
		this.getTests().add(CT01AccesViaSimulationOPENGO(this, outil));
		
		
		identificationRacGc(outil);
		accederARacGc(outil);
		
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, this, ex, this.getNomCasEssai() + this.getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, this, this.getNomCasEssai() + this.getTime());
	}
}
