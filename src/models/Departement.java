package models;
import services.DB;

import java.util.ArrayList;



    public class Departement {


        private int id;
        private String intitule;
        private Enseignant chef;


        ArrayList<Filiere> filieres = new ArrayList<Filiere>();


        public Departement() {
        }
        public Departement(int id,String intitule){
            this.intitule = intitule;
            this.id=id;
        }

        public Departement(int id, String intitule, Enseignant chef) {
            this.intitule = intitule;
            this.chef = chef;
            this.id=id;
        }





        public String getIntitule() {
            return intitule;
        }

        public void setIntitule(String intitule) {
            this.intitule = intitule;
        }

        public Enseignant getChef() {
            return chef;
        }

        public void setChef(Enseignant chef) {
            this.chef = chef;
        }

        public ArrayList<Filiere> getFilieres() {
            return filieres;
        }

        public void setFilieres(ArrayList<Filiere> filieres) {
            this.filieres = filieres;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Departement{" +
                    "id=" + id +
                    ", intitule='" + intitule + '\'' +
                    '}';
        }
    }
