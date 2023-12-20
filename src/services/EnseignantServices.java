package services;

import models.Departement;
 import models.Enseignant;
import java.util.ArrayList;


public class EnseignantServices {
    public static Enseignant addEns(String nom, String prenom, String email, String grade, Departement... dept){
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        enseignant.setGrade(grade);

        enseignant.setId(DB.getEnsId());
        if (dept.length > 0){
            enseignant.setDept(dept[0]);
        }
        DB.enseignants.add(enseignant);

        return  enseignant;
    }


    public static Enseignant updateEns(int id, String nom, String prenom, String email, String grade, Departement...dept){
        for (Enseignant enseignant : DB.enseignants) {
            if (enseignant.getId() == id) {
                enseignant.setNom(nom);
                enseignant.setPrenom(prenom);
                enseignant.setEmail(email);
                enseignant.setGrade(grade);

                if (dept.length > 0){
                        enseignant.setDept(dept[0]);
                    }
                    return enseignant;


        }
        }

        return  new Enseignant();
    }

    public static ArrayList<Enseignant> deleteEnsById(int id){
        DB.enseignants.remove(getEnsById(id));
        return DB.enseignants ;
    }


    public static Enseignant getEnsById(int id){
        for (Enseignant enseignant : DB.enseignants) {
            if (enseignant.getId() == id) return  enseignant;
        }
        return  new Enseignant();
    }

    public static ArrayList<Enseignant> getAllEns(){
        return  DB.enseignants;
    }
   }
