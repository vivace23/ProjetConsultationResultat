package myApplication.Junit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import myApplication.Model.Etudiant;
import myApplication.Controller.Controller;

public class TestJUnit {
	Controller controller = new Controller();
	
	//test pour tester la connexion à la base de donnée
	@Test
	public void connexionDataBase() {
        Boolean result = controller.connexionBD();
        assertTrue(result, "la connexion à la base de donner n'a pas reuissi");
	}
	
	//test pour avoir le resultat de l'etudiant, c'est à dire echec ou succes
	@Test
	public void checkMatriculeIsInstaceOfIntegerTest() {
		 Boolean matriculeInt = controller.checkMatriculeIsInstaceOfInteger("0072");
	     assertTrue(matriculeInt, "la connexion à la base de donner n'a pas reuissi");
	}
	
	//test pour verifier que le matricule n'est pas null c'est à dire vide
	@Test
	public void ckeckMatriculeIsNotEmptyTest() {
		Boolean matricule = controller.ckeckMatriculeIsNotEmpty("097");
		assertTrue(matricule,"matricule vide");
	}
	
	//test pour verifier que le matricule existe dans la base de donnée
	@Test
	public void checkMatriculeExistInDatabaseTest() {
		Boolean matriculeStatus = controller.checkMatriculeExistInDatabase("0071348");
		assertTrue(matriculeStatus, "le matricule n'existe pas la base de donnée");
	}
	
	//test pour recuperer les informations de l'etudiant de la base de donnée
	@Test
	public void getEtudiantInformationTest() {
		Etudiant etudiant = controller.getEtudiantInformation("0071348");
		assertNotNull(etudiant, "vide");
	}
}
