    package controllers;
    import main.Main;

    import models.Etudiant;
    import services.DB;

    import services.EtudiantServices;
    import services.FiliereServices;

public class EtudiantsController {
    public static void showMenu(){
        System.out.println("-------------------------[ Etudiants ]---------------------------");


        System.out.println("1: Pour ajouter un étudiant");
        System.out.println("2: Pour afficher les étudiants");
        System.out.println("3: Pour modifier un étudiant");
        System.out.println("4: Pour supprimer un étudiant");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = Main.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createEtudiant();
                break;
            case 2:
                showEtudiants();
                break;
            case 3:
                editEtudiant();
                break;
            case 4:
                destroyEtudiant();
                break;
            default:
                Main.showPrincipalMenu();
        }
    }
    public static void showEtudiants(){
        for (Etudiant etudiant : DB.etudiants) {
            System.out.print("Id : " + etudiant.getId());
            System.out.print(" | nom: " + etudiant.getNom());
            System.out.print("|prenom: " + etudiant.getPrenom());
            System.out.print("|email: " + etudiant.getEmail());
            System.out.print("|apogee : " + etudiant.getApogee());
            if (! Main.isNull(etudiant.getFiliere())) {
                System.out.print(" | filiere : " + etudiant.getFiliere().getIntitule() );
            }
            System.out.println("");
        }

    }
    public static void createEtudiant(){
        String nom = Main.getStringInput("Entrez le nom:");
        String prenom = Main.getStringInput("Entrez le prenom:");
        String email = Main.getStringInput("Entrez l'email:");
        int apogee = Main.getIntInput("Entrez code apogee:");
        FilieresController.showFilieres();
        int id = Main.getIntInput("Sélecionnez une filiere par id :");

        EtudiantServices.addEtd(nom,prenom,email,apogee, FiliereServices.getFiliereById(id));

        showEtudiants();
        showMenu();


    }
    public static void editEtudiant(){
        showEtudiants();
        int id = Main.getIntInput("Sélecionnez un etudiant par id :");
        String nom = Main.getStringInput("Entrez nom:");
        String prenom = Main.getStringInput("Entrez prenom:");
        String email = Main.getStringInput("Entrez email:");
        int apogee = Main.getIntInput("Entrez apogee:");
        FilieresController.showFilieres();
        int idFil = Main.getIntInput("Sélecionnez une filiere par id :");

        EtudiantServices.updateEtd(id, nom,prenom,email,apogee, FiliereServices.getFiliereById(idFil));

        showEtudiants();
        showMenu();
    }
    public static void destroyEtudiant(){
        showEtudiants();
        int id = Main.getIntInput("Sélecionnez un etudiant par id :");
        EtudiantServices.deleteEtdById(id);
        showEtudiants();

    }
}