package test.java;
import org.junit.Test;

import exceptions.SeleniumException;
import outils.SeleniumOutils;
import constantes.Actions;
import main.constantes.Cibles;
import main.constantes.Constantes;

public class SC01Login extends SC00Modele {
	
	/**
	 * Id de serialisation.
	 */
	private static final long serialVersionUID = 1L;
	@Test
	public void accesTest() throws SeleniumException {
		
		// Valorisation des données
				setNumeroDossier("41004660822100");
				setNumeroIUN("791648");
				setDistributeur("BP");

				/////////////////////////////////////////////////// Configuration////////////////////////////////////////////////
				SeleniumOutils outil = obtenirDriver(this);
				/////////////////////////////////////////////////// EXECUTION////////////////////////////////////////////////
				// Accès à RACGC
				accesRacGc(outil);
				// S'identifier sur l'appliation
				identificationRacGc(outil);
				accederARacGc(outil);
	}
}
