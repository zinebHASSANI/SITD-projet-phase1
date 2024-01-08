package controllers;

import main.Connexion;
import models.Filiere;
import services.DB;
import services.FiliereServices;
import services.EnseignantServices;
import services.DepartementServices;

public class FilieresController {



    public static void showMenu(){
        System.out.println("-------------------------[ Filieres ]---------------------------");


        System.out.println("1: Pour ajouter une filiere");
        System.out.println("2: Pour afficher les filieres");
        System.out.println("3: Pour modifier une filiere");
        System.out.println("4: Pour supprimer une filiere");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = Connexion.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createFiliere();
                break;
            case 2:
                showFilieres();
                break;
            case 3:
                editFiliere();
                break;
            case 4:
                destroyfiliere();
                break;
            default:
                Connexion.showPrincipalMenu();
        }
    }
    public static void showFilieres(){
        for (Filiere filiere : DB.filieres) {
            System.out.print("Id : " + filiere.getId());
            System.out.print(" | Intitulé : " + filiere.getIntitule());
            if (! Connexion.isNull(filiere.getChef())) {
                System.out.print(" | Chef : " + filiere.getChef().getNom() + " " +filiere.getChef().getPrenom());
            }
            if (! Connexion.isNull(filiere.getDept())) {
                System.out.print(" | departement : " + filiere.getDept().getIntitule() );
            }

            System.out.println("");
        }

    }
    public static void createFiliere(){
        String intitule = Connexion.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        DepartementsController.showDepartements();
        int id = Connexion.getIntInput("Sélecionnez un enseignant par id :");
        int id1 = Connexion.getIntInput("Sélecionnez un departement par id :");
        FiliereServices.addFiliere(intitule,EnseignantServices.getEnsById(id),DepartementServices.getDeptById(id1));

        showFilieres();
        showMenu();


    }
    public static void editFiliere(){
        showFilieres();
        int id = Connexion.getIntInput("Sélecionnez une filiere par id :");
        String intitule = Connexion.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        DepartementsController.showDepartements();
        int idEns = Connexion.getIntInput("Sélecionnez un enseignant par id :");
        int iddept = Connexion.getIntInput("Sélecionnez un departement par id :");


        FiliereServices.updateFiliere(id, intitule, EnseignantServices.getEnsById(idEns),DepartementServices.getDeptById(iddept));

        showFilieres();
        showMenu();
    }
    public static void destroyfiliere(){
        showFilieres();
        int id = Connexion.getIntInput("Sélecionnez une filiere par id :");
        FiliereServices.deleteFiliereById(id);
        showFilieres();}
}




