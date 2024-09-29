package myApplication.Main;

import java.util.Map;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import myApplication.Controller.Controller;
import myApplication.Model.Etudiant;

public class Main extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
		
        EcranSaisieMatricule();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	
	private void EcranSaisieMatricule() {
        // Éléments de l'écran de login
    	AnchorPane root= new AnchorPane();
    	
    	AnchorPane root2 = new AnchorPane();
		
        
        VBox verticalAlign = new VBox(5);
        
        //creation d'un label
        
        
        Label msgAcceuil = new Label();
        msgAcceuil.setText("Veuillez saisir votre matricule pour consulter votre resultat");
        msgAcceuil.setWrapText(true);
        msgAcceuil.setStyle("-fx-font-size: 24px; -fx-font-family: 'Times New Roman';");

        
        //creation d'un textfield pour la saisie du matricule
        
        TextField inputMatricule = new TextField("Saisir le matricule ici...");
        inputMatricule.setStyle("-fx-font-size: 24px; -fx-font-family: 'Times New Roman';");
        
        
        //creation d'un bouton pour recuperer le matricule et faire la recherche des resultats
		
        Button btnChercheRes = new Button("CHERCHER");
        root2.setStyle("-fx-background-color: AZURE;");
        
        btnChercheRes.setStyle("-fx-padding: 10px; -fx-background-color: lightblue;");
        btnChercheRes.setMinSize(300, 25); // Largeur minimale de 50px, hauteur minimale de 20px
        btnChercheRes.setMaxSize(200, 50);
        
        //
        verticalAlign.getChildren().addAll(msgAcceuil, inputMatricule, btnChercheRes);
        
        //pour ajouter une marge à mon vertical box
        verticalAlign.setPadding(new Insets(20));
        
        root2.getChildren().add(verticalAlign);
        
        root2.setTopAnchor(verticalAlign, 20.0);
        root2.setRightAnchor(verticalAlign, 20.0);
        root2.setBottomAnchor(verticalAlign, 20.0);
        root2.setLeftAnchor(verticalAlign, 20.0);
        
        root.setTopAnchor(root2, 50.0);
        root.setRightAnchor(root2, 50.0);
        root.setBottomAnchor(root2, 50.0);
        root.setLeftAnchor(root2, 50.0);
        
        root.getChildren().add(root2);
        
        
        verticalAlign.setAlignment(Pos.CENTER);
        
        //pour la recherche
        btnChercheRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Controller controller = new Controller();
            	if (inputMatricule.getText().isEmpty()) {
            		monAlert("MATRICULE NON SAISI", "Veuiller saisir un matricule!");
                } else {
                	Etudiant etudiantInfo = controller.getEtudiantInformation(inputMatricule.getText());
                	if(etudiantInfo==null) {
                		monAlert("ETUDIANT NON TROUVÉ", "Veuiller verifier le matricule!");
                	}else {
                		System.out.print(etudiantInfo);
                		EcranResultat(etudiantInfo);
                	}
                	
                	
                }
            }
        });
        
        Scene scene = new Scene(root, 900, 400);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		primaryStage.setTitle("Consultation resultats");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	
	private void EcranResultat(Etudiant etudiant) {
    	
        try {
        	
        	AnchorPane root = new AnchorPane();
        	
        	
        	AnchorPane anchorPane2 = new AnchorPane();
        	
        	//pour le scroll view
        	
        	ScrollPane  scrollView = new ScrollPane();
        	
        	//pour aligner les element de maniere verticale
        	VBox vertical = new VBox(8);
            
            String libelleResultat;
            Image iconResultat = null;
            ImageView iconResultatView = null;
            Label labelResultat= null;
            
            int note = etudiant.getNote();
            
            //pour le libellé du resultat
            if(note >= 10) {
            	libelleResultat = "SUCCÈS";
            	labelResultat = new Label(libelleResultat);
            	labelResultat.setStyle("-fx-text-fill: green; -fx-font-size: 40px; -fx-font-weight: bold");
            	iconResultat = new Image("data:image/jpeg;base64,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");
            }else {
            	libelleResultat = "ÉCHEC";
            	labelResultat = new Label(libelleResultat);
            	labelResultat.setStyle("-fx-text-fill: red; -fx-font-size: 40px; -fx-font-weight: bold");
            	iconResultat = new Image("data:image/png;base64,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");
            }
            
            iconResultatView = new ImageView(iconResultat);
            
            //pour le resultat
            //labelResultat.setFont(Font.font("verdana",FontWeight.BOLD, 45));
            //labelResultat.setStyle("-fx-text-fill: red; -fx-font-size: 40px; -fx-font-weight: bold");
            
            //pour le nom
            Label labelNote = new Label("NOTE");
            Label lNote = new Label(etudiant.getNote()+"/20");
            labelNote.setFont(Font.font("verdana",FontWeight.NORMAL, 15));
            lNote.setFont(Font.font("verdana",FontWeight.BOLD, 25));
            
          //pour la note
            Label libelleNom = new Label("NOM");
            Label nom = new Label(etudiant.getNom());
            libelleNom.setFont(Font.font("verdana",FontWeight.NORMAL, 15));
            nom.setFont(Font.font("verdana",FontWeight.BOLD, 25));
            
            //pour le prenom
            Label libellePrenom = new Label("PRENOM");
            Label prenom = new Label(etudiant.getPrenom());
            libellePrenom.setFont(Font.font("verdana",FontWeight.NORMAL, 15));
            prenom.setFont(Font.font("verdana",FontWeight.BOLD, 25));
            
            //pour la date de naissance
            Label libelleDateNais = new Label("DATE DE NAISSANCE");
            Label dateNais = new Label(etudiant.getDatenais());
            libelleDateNais.setFont(Font.font("verdana",FontWeight.NORMAL, 15));
            dateNais.setFont(Font.font("verdana",FontWeight.BOLD, 25));
            
            //Pour l'ecole
            Label libelleEcole = new Label("ÉCOLE");
            Label ecole = new Label(etudiant.getEcole());
            libelleEcole.setFont(Font.font("verdana",FontWeight.NORMAL, 15));
            ecole.setFont(Font.font("verdana",FontWeight.BOLD, 25));
            
            
            //bouton afficher les details
            Button btnAfficherDetail = new Button("AFFICHER LES DETAILS");
            btnAfficherDetail.setStyle("-fx-padding: 10px; -fx-background-color: lightgreen;");
            btnAfficherDetail.setMinSize(300, 25);
            btnAfficherDetail.setMaxSize(200, 50);
            
            
            //le bouton pour revenir à l'ecran de saisie d
            Button btnRevenirEcranSaisie = new Button("REPRENDRE LA RECHERCHE");
            btnRevenirEcranSaisie.setStyle("-fx-padding: 10px; -fx-background-color: lightblue;");
            btnRevenirEcranSaisie.setMinSize(300, 25);
            btnRevenirEcranSaisie.setMaxSize(200, 50);
            
            
            Pane inforPersoZonePane = new Pane();
            inforPersoZonePane.setStyle("-fx-background-color: lightblue;");
            
           //pour afficher la note
            VBox alignNoteHorizontal =  new VBox(0);
            alignNoteHorizontal.getChildren().addAll(labelNote, lNote);
            alignNoteHorizontal.setAlignment(Pos.CENTER);
            
          //pour afficher le nom
            VBox alignNomHorizontal =  new VBox(0);
            alignNomHorizontal.getChildren().addAll(libelleNom, nom);
            alignNomHorizontal.setAlignment(Pos.CENTER);
            
          //pour afficher le prenom
            VBox alignPrenomHorizontal =  new VBox();
            alignPrenomHorizontal.getChildren().addAll(libellePrenom, prenom);
            alignPrenomHorizontal.setAlignment(Pos.CENTER);
            
          //pour afficher le date de naissance
            VBox alignDateNaisHorizontal =  new VBox();
            alignDateNaisHorizontal.getChildren().addAll(libelleDateNais, dateNais);
            alignDateNaisHorizontal.setAlignment(Pos.CENTER);
            
          //pour afficher le ecole
            VBox alignEcoleHorizontal =  new VBox();
            alignEcoleHorizontal.getChildren().addAll(libelleEcole, ecole);
            alignEcoleHorizontal.setAlignment(Pos.CENTER);
            
            
            VBox inforPersoVertical = new VBox(5);
            
            AnchorPane anchorpaneInfoPerso =  new AnchorPane();
            
            
            
            vertical.getChildren().addAll(iconResultatView, labelResultat, btnAfficherDetail);
            
            
            //pour afficher les details
            btnAfficherDetail.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	vertical.getChildren().addAll(alignNoteHorizontal, alignNomHorizontal, alignPrenomHorizontal, alignDateNaisHorizontal, alignEcoleHorizontal, btnRevenirEcranSaisie);
                	vertical.getChildren().remove(btnAfficherDetail);
                }
            });
            
            //pour revenir à l'ecran de saisie du matricule
            btnRevenirEcranSaisie.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	EcranSaisieMatricule();
                }
            });
            
            
            //pour center le contenu de vertical
            vertical.setAlignment(Pos.CENTER);
            
            root.getChildren().add(scrollView);
            
            //pour center le contenur de root(le premier parent)
            AnchorPane.setTopAnchor(scrollView, 0.0);
            AnchorPane.setRightAnchor(scrollView, 0.0);
            AnchorPane.setBottomAnchor(scrollView, 0.0);
            AnchorPane.setLeftAnchor(scrollView, 0.0);
            
            //pour center le contenu de scroll view
            
            
            
            anchorPane2.getChildren().add(vertical);
            anchorPane2.setStyle("-fx-background-color: WHITE;");
            
            AnchorPane.setTopAnchor(vertical, 5.0);
            AnchorPane.setRightAnchor(vertical, 5.0);
            AnchorPane.setBottomAnchor(vertical, 5.0);
            AnchorPane.setLeftAnchor(vertical, 5.0);
            
            scrollView.setContent(anchorPane2);
            scrollView.setFitToWidth(true);
            
            Scene scene = new Scene(root, 900, 400);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	
    		primaryStage.setTitle("Consultation resultats");
    		primaryStage.setScene(scene);
    		primaryStage.show();
        }
        catch(Exception e){
        	e.printStackTrace();
        	
        }
    }
	
	//un alert d'information
	public void monAlert(String titreAlert, String msgAlert) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(titreAlert);
        alert.setContentText(msgAlert);
        alert.showAndWait();
    }

    
    

}
