package myApplication.AssertJ;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import myApplication.Model.Etudiant;
import myApplication.Controller.Controller;

public class TestAssertJ {
	Controller controller = new Controller();
	
	//test pour tester la connexion à la base de donnée
	@Test
	public void connexionDataBase() {
        Boolean result = controller.connexionBD();
        assertThat(result).describedAs("la connexion à la base de donner n'a pas reuissi").isTrue();
	}
	
	//test pour avoir le resultat de l'etudiant, c'est à dire echec ou succes
	@Test
	public void getEtudiantResultatTest() {
		
	}
	
	//test pour verifier que le matricule n'est pas null c'est à dire vide
	@Test
	public void ckeckMatriculeIsNotEmptyTest() {
		Boolean matricule = controller.ckeckMatriculeIsNotEmpty("00768");
		assertThat(matricule).describedAs("le matricule est vide").isTrue();
	}
	
	//test pour verifier que le matricule existe dans la base de donnée
	@Test
	public void checkMatriculeExistInDatabaseTest() {
		Boolean matriculeStatus = controller.checkMatriculeExistInDatabase("0071348");
		assertThat(matriculeStatus).describedAs("le matricule n'existe pas dans la abse de donnée").isTrue();
	}
	
	//test pour recuperer les informations de l'etudiant de la base de donnée
	@Test
	public void getEtudiantInformationTest() {
		Etudiant etudiant = controller.getEtudiantInformation("0071348");
		assertThat(etudiant).isNotNull();
	}
}
