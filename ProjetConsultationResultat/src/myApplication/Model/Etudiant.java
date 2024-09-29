package myApplication.Model;

import java.util.Date;

public class Etudiant {
    private String matricule;
    private String nom;
    private String prenom;
    private String DateNais;
    private String ecole;
    private int note;

    // Constructeurs
    public Etudiant() {}

    public Etudiant(String matricule, String nom, String prenom, String DateNais, String ecole, int note) {
    	
    	this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.DateNais = DateNais;
        this.ecole = nom;
        this.note = note;
    }
    
    
    //matricule
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
    //nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    //prenom 
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    //date de naissance
    public String getDatenais() {
        return DateNais;
    }

    public void setDatenais(String DateNais) {
        this.DateNais = DateNais;
    }
    
    //ecole
    
    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }
    
    //ecole
    
    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    
    

}