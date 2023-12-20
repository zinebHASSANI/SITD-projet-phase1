package main;



import controllers.DepartementsController;
import controllers.EnseignantsController;
import controllers.FilieresController;
import controllers.EtudiantsController;
import controllers.ModulesController;

import models.Enseignant;
import services.DB;

import java.util.Scanner;


    public class Main {


            public static boolean isNull (Object ob){
                return ob == null;
            }
            public static int getIntInput (String...msg){
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

            public static String getStringInput (String...msg){
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

            public static void showPrincipalMenu () {
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
            public static void main (String[]args){

                Enseignant enseignant = new Enseignant();
                enseignant.setNom("AHMED");
                enseignant.setPrenom("Hafid");
                enseignant.setEmail("hafidi180@gmail.com");
                enseignant.setGrade("RES");
                enseignant.setId(DB.getEnsId());
                DB.enseignants.add(enseignant);
                showPrincipalMenu();
            }
        }



