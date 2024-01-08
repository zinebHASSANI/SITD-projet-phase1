package controllers;

import main.Connexion;
import models.Module;
import services.DB;
import services.ModuleServices;
import services.EnseignantServices;
import services.FiliereServices;


public class ModulesController {
    public static void showMenu(){
        System.out.println("-------------------------[ Modules ]---------------------------");


        System.out.println("1: Pour ajouter un module");
        System.out.println("2: Pour afficher les modules");
        System.out.println("3: Pour modifier un module");
        System.out.println("4: Pour supprimer un module");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = Connexion.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createModule();
                break;
            case 2:
                showModules();
                break;
            case 3:
                editModule();
                break;
            case 4:
                destroyModule();
                break;
            default:
                Connexion.showPrincipalMenu();
        }
    }
    public static void showModules(){
        for (Module module : DB.modules) {
            System.out.print("Id : " + module.getId());
            System.out.print(" | Intitulé : " + module.getIntitule());
            if (! Connexion.isNull(module.getChef())) {
                System.out.print(" | Chef : " + module.getChef().getNom() + " " + module.getChef().getPrenom());
            }
            if (! Connexion.isNull(module.getFiliere())) {
                System.out.print(" | filiere : " + module.getFiliere().getIntitule() );
            }
            System.out.println("");
        }

    }
    public static void  createModule(){
        String intitule = Connexion.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        int id = Connexion.getIntInput("Sélecionnez un enseignant par id :");
        FilieresController.showFilieres();
        int idfil = Connexion.getIntInput("Sélecionnez une filiere par id :");
        ModuleServices.addModule(intitule,FiliereServices.getFiliereById(idfil), EnseignantServices.getEnsById(id));

        showModules();
        showMenu();


    }
    public static void editModule(){
        showModules();
        int id = Connexion.getIntInput("Sélecionnez un departement par id :");
        String intitule = Connexion.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        FilieresController.showFilieres();
        int idEns = Connexion.getIntInput("Sélecionnez un enseignant par id :");
        int idfil = Connexion.getIntInput("Sélecionnez une filiere par id :");

        ModuleServices.updateModule(id, intitule,FiliereServices.getFiliereById(idfil) ,EnseignantServices.getEnsById(idEns));

        showModules();
        showMenu();
    }
    public static void destroyModule(){

        showModules();
        int id = Connexion.getIntInput("Sélecionnez un departement par id :");
        ModuleServices.deleteModuleById(id);
        showModules();}
}



