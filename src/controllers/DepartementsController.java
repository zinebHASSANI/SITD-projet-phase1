
package controllers;


import main.Connexion;
import models.Departement;
import services.DB;
import services.DepartementServices;
import services.EnseignantServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static main.Connexion.*;


public class DepartementsController {
    public static void showMenu(){
        System.out.println("-------------------------[ Départements ]---------------------------");


        System.out.println("1: Pour ajouter un département");
        System.out.println("2: Pour afficher les départements");
        System.out.println("3: Pour modifier un département");
        System.out.println("4: Pour supprimer un département");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = Connexion.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createDepartement();
                break;
            case 2:
                showDepartements();
                break;
            case 3:
                editDepartement();
                break;
            case 4:
                destroyDepartement();
                break;
            default:
                Connexion.showPrincipalMenu();
        }
    }
    public static void showDepartements(){
        for (Departement departement : DB.departements) {
            System.out.print("Id : " + departement.getId());
            System.out.print(" | Intitulé : " + departement.getIntitule());
            if (! Connexion.isNull(departement.getChef())) {
                System.out.print(" | Chef : " + departement.getChef().getNom() + " " + departement.getChef().getPrenom());
            }
            System.out.println("");
        }
        String url = "jdbc:mysql://localhost:3306/parcoursdb";
        String user = "root";
        String pwd = "";
        try {
            Connection cx = DriverManager.getConnection(url, user, pwd);
            displayDepartements(cx);

            }
            catch (SQLException e) {
                System.out.println("Bad Connection");
                e.printStackTrace();
            }

    }


    public static void createDepartement(){
        String url = "jdbc:mysql://localhost:3306/parcoursdb";
        String user = "root";
        String pwd = "";
        try {
            Connection cx = DriverManager.getConnection(url, user, pwd);

            String intitule = Connexion.getStringInput("Entrez l'intitulé :");
            EnseignantsController.showEnseignants();
            int id  = Connexion.getIntInput("Sélecionnez un enseignant par id :");


            DepartementServices.addDept(intitule, EnseignantServices.getEnsById(id));
            insertDepartement(DB.departements.get(0), cx);
            System.out.println("vous avez ajouté un element avec succès");
            showDepartements();


        }catch (SQLException e) {
            System.out.println("Bad Connection");
            e.printStackTrace();
        }
    }



    public static void editDepartement() {

        try{
            int id = Connexion.getIntInput("Sélecionnez un departement par id :");
        String intitule = Connexion.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        int idEns = Connexion.getIntInput("Sélecionnez un enseignant par id :");
        DepartementServices.updateDept(id, intitule, EnseignantServices.getEnsById(idEns));
        String url = "jdbc:mysql://localhost:3306/parcoursdb";
        String user = "root";
        String pwd = "";
            Connection cx = DriverManager.getConnection(url, user, pwd);
            updateDepartement(DB.departements.get(0), cx);


            showDepartements();

            showMenu();
        } catch (SQLException e) {
            System.out.println("Bad Connection");
            e.printStackTrace();
        }}


        public static void  destroyDepartement(){
            showDepartements();
            int id = Connexion.getIntInput("Sélectionnez un departement par id :");
            DepartementServices.deleteDeptById(id);
            showDepartements();
            String url = "jdbc:mysql://localhost:3306/parcoursdb";
            String user = "root";
            String pwd = "";
            try {
                Connection cx = DriverManager.getConnection(url, user, pwd);

                deleteDept(id, cx);
                System.out.println("vous avez supprimé l'element avec succès");
                showMenu();
            } catch (SQLException e) {
                System.out.println("Bad Connection");
                e.printStackTrace();
            }
        }


    }
