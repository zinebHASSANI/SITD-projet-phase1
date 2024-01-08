package main;



import java.sql.*;
import java.util.Scanner;
import models.Departement;
import models.Enseignant;




import controllers.DepartementsController;
import controllers.EnseignantsController;
import controllers.FilieresController;
import controllers.EtudiantsController;
import controllers.ModulesController;


import services.DB;


    public class Connexion {
    public static boolean isNull(Object ob) {
        return ob == null;
    }

    public static int getIntInput(String... msg) {
        Scanner scan = new Scanner(System.in);
        String message = "Entrez un nombre entier : ";
        if (msg.length > 0)
            message = msg[0];
        System.out.print(message);


        // This method reads the number provided using keyboard
        int num = scan.nextInt();

        // Closing Scanner after the use
        //  scan.close();
        return num;
    }

    public static String getStringInput(String... msg) {
        Scanner scan = new Scanner(System.in);
        String message = "Entrez un texte : ";
        if (msg.length > 0)
            message = msg[0];
        System.out.print(message);

        // This method reads the number provided using keyboard
        String str = scan.nextLine();

        // Closing Scanner after the use
        //  scan.close();
        return str;
    }

    public static void showPrincipalMenu() {
        System.out.println("-------------------------[ Bienvenu ]---------------------------");


        System.out.println("1: Pour gérer les départements");
        System.out.println("2: Pour gérer les filières");
        System.out.println("3: Pour gérer les enseignants");
        System.out.println("4: Pour gérer les modules");
        System.out.println("5: Pour gérer les étudiants");
        System.out.println("0: Pour sortir");

        //"Veuillez sélectionner une option : ")
        int option = getIntInput("Veuillez sélectionner une option : ");
        switch (option) {
            case 1:
                DepartementsController.showMenu();
                break;
            case 2:
                FilieresController.showMenu();
                break;
            case 3:
                EnseignantsController.showMenu();
                break;
            case 4:
                ModulesController.showMenu();
                break;
            case 5:
                EtudiantsController.showMenu();
                break;
            default:
                System.out.println("Au revoir !");
                System.exit(0);
                // code block
        }
    }



    public static void main(String[] args) {





        Enseignant enseignant=new Enseignant(3,"mostapha","hafid","hassani@gmail.com","pers");
        Enseignant enseignant2=new Enseignant(4,"mostapha","hafid","hassani@gmail.com","pers");





            DB.enseignants.add(enseignant);
            DB.enseignants.add(enseignant2);

            showPrincipalMenu();




    }










    public static void insertDepartement(Departement departement , Connection cx) throws SQLException {
        String query = "INSERT INTO departement (id_departement, intitule,id_enseignant) values (?,?,?)";

        PreparedStatement ps = cx.prepareStatement(query);

        ps.setInt(1,departement.getId());
        ps.setString(2, departement.getIntitule());
        ps.setInt(3,departement.getChef().getId());


        ps.executeUpdate();



    }


    public static void deleteDept(int id, Connection cx) throws SQLException {
        String query = "DELETE  from parcoursdb.departement where id_departement = ?";
        PreparedStatement ps = cx.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();

    }



        public static void displayDepartements(Connection cx) throws SQLException {
            String query = "SELECT * FROM departement";

            try (PreparedStatement ps = cx.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int idDepartement = rs.getInt("id_departement");
                    String intitule = rs.getString("intitule");
                    int idEnseignant = rs.getInt("id_enseignant");


                    System.out.println("ID Département: " + idDepartement);
                    System.out.println("Intitulé: " + intitule);
                    System.out.println("ID Enseignant: " + idEnseignant);
                    System.out.println("------------------------");
                }
            }
        }



















        public static void updateDepartement(Departement departement, Connection cx) throws SQLException {
            String query = "UPDATE departement SET intitule = ?, id_enseignant = ? WHERE id_departement = ?";

            PreparedStatement ps = cx.prepareStatement(query);

            ps.setString(1, departement.getIntitule());
            ps.setInt(2, departement.getChef().getId());
            ps.setInt(3, departement.getId());

            ps.executeUpdate();
        }
}




