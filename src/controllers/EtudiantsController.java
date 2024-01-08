    package controllers;
    import main.Connexion;

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
        int option = Connexion.getIntInput("Veuillez sélectionner une option : ");
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
                Connexion.showPrincipalMenu();
        }
    }
    public static void showEtudiants(){
        for (Etudiant etudiant : DB.etudiants) {
            System.out.print("Id : " + etudiant.getId());
            System.out.print(" | nom: " + etudiant.getNom());
            System.out.print("|prenom: " + etudiant.getPrenom());
            System.out.print("|email: " + etudiant.getEmail());
            System.out.print("|apogee : " + etudiant.getApogee());
            if (! Connexion.isNull(etudiant.getFiliere())) {
                System.out.print(" | filiere : " + etudiant.getFiliere().getIntitule() );
            }
            System.out.println("");
        }

    }
    public static void createEtudiant(){
        String nom = Connexion.getStringInput("Entrez le nom:");
        String prenom = Connexion.getStringInput("Entrez le prenom:");
        String email = Connexion.getStringInput("Entrez l'email:");
        int apogee = Connexion.getIntInput("Entrez code apogee:");
        FilieresController.showFilieres();
        int id = Connexion.getIntInput("Sélecionnez une filiere par id :");

        EtudiantServices.addEtd(nom,prenom,email,apogee, FiliereServices.getFiliereById(id));

        showEtudiants();
        showMenu();


    }
    public static void editEtudiant(){
        showEtudiants();
        int id = Connexion.getIntInput("Sélecionnez un etudiant par id :");
        String nom = Connexion.getStringInput("Entrez nom:");
        String prenom = Connexion.getStringInput("Entrez prenom:");
        String email = Connexion.getStringInput("Entrez email:");
        int apogee = Connexion.getIntInput("Entrez apogee:");
        FilieresController.showFilieres();
        int idFil = Connexion.getIntInput("Sélecionnez une filiere par id :");

        EtudiantServices.updateEtd(id, nom,prenom,email,apogee, FiliereServices.getFiliereById(idFil));

        showEtudiants();
        showMenu();}
    public static void destroyEtudiant(){
        showEtudiants();
        int id = Connexion.getIntInput("Sélecionnez un etudiant par id :");
        EtudiantServices.deleteEtdById(id);
        showEtudiants();

    }
}