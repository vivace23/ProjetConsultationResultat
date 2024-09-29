package myApplication.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import myApplication.Model.Etudiant;

public class Controller {
	static String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0071348";
	static String user = "user0071348";
	static String password = "Yf3IgyBsOPa34WR";
	
	Connection conDB =  null;
	Statement stmt = null;
    ResultSet resultatS = null;

	
	//methode pour tester la connexion à la base de donnée
	public Boolean connexionBD() {
		
		Boolean status = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conDB = DriverManager.getConnection(url, user, password);
			if(conDB!=null) {
				status = true;
			}
		}catch(Exception e) {
			System.out.println(e);
			status = false;
		}
		
		return status;
	}
	
	//methode recuperer les informations de l'etudi
	public Etudiant getEtudiantInformation(String matricule) {
		
		Etudiant etudiant = null;
		
		Boolean DBSatus = connexionBD();
		
		if(DBSatus) {
			try {
				String query = "select * from note LEFT JOIN Etudiant on note.etudiant = Etudiant.matricule WHERE Etudiant.matricule =\""+matricule+"\";";
				
				//se connecter à la base de donnée
				conDB = DriverManager.getConnection(url, user, password);

		        // Créer un statement
		        stmt = conDB.createStatement();

		        // Exécuter la requête SQL
		        resultatS = stmt.executeQuery(query);
		        

		        if(resultatS.next()) {
		        	etudiant = new Etudiant(resultatS.getString("matricule"), resultatS.getString("nom"), resultatS.getString("prenom"), resultatS.getString("DateNais"), resultatS.getString("ecole"), resultatS.getInt("note"));
		        }else {
		        	etudiant = null;
		        }

		        
			}catch (SQLException e) {
	            System.out.println("Erreur de connexion ou d'exécution de la requête.");
	            e.printStackTrace();
	        } finally {
	            // Fermer les ressources
	            try {
	                if (resultatS != null) resultatS.close();
	                if (stmt != null) stmt.close();
	                if (conDB != null) conDB.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}
		
		return etudiant;
	}
	
	//methode pour verifier que la zone de matricule n'est pas vide
	public Boolean ckeckMatriculeIsNotEmpty(String matricule) {
		
		String _matricule = matricule;
		
		if(_matricule.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	//methode pour verifier que le format du matricule est correct
	public Boolean checkMatriculeIsInstaceOfInteger(String matricule) {
		Integer _matricule = Integer.parseInt(matricule);
		
		if (_matricule instanceof Integer) {
			return true;
		}else {
			return false;
		}
		
	}
	//methode pour verifier que le matricule saisi existe dans la base de donnée
	public Boolean checkMatriculeExistInDatabase(String matricule) {
		
		Boolean matriculeSatuts = null;
		Boolean DBSatus = connexionBD();
		if(DBSatus) {
			try {
				String query = "select * from Etudiant where matricule="+matricule;
				
				//se connecter à la base de donnée
				conDB = DriverManager.getConnection(url, user, password);

		        // Créer un statement
		        stmt = conDB.createStatement();

		        // Exécuter la requête SQL
		        resultatS = stmt.executeQuery(query);
		        

		        if(resultatS.next()) {
		        	matriculeSatuts = true;
		        }else {
		        	matriculeSatuts = false;
		        }

		        
			}catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Fermer les ressources
	            try {
	                if (resultatS != null) resultatS.close();
	                if (stmt != null) stmt.close();
	                if (conDB != null) conDB.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}
		
		return matriculeSatuts;
	}
}
