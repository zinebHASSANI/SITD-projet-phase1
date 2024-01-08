    package services;
    import models.Departement;
    import models.Enseignant;
    import models.Filiere;
    import models.Module;

    import java.util.ArrayList;


    public class ModuleServices {

        public static Module addModule(String intitule,Filiere filiere,Enseignant... chef) {
            Module module = new Module();
            module.setIntitule(intitule);
            module.setId(DB.getModId());
            module.setFiliere(filiere);
            if (chef.length > 0){
                module.setChef(chef[0]);
            }
            DB.modules.add(module);

            return module;
        }



        public static Module updateModule(int id , String intitule, Filiere filiere,Enseignant... chef ){
            for (Module module : DB.modules) {
                if (module.getId() == id) {
                    module.setIntitule(intitule);
                    module.setFiliere(filiere);

                    if (chef.length > 0){
                        module.setChef(chef[0]);
                    }
                    return module;
                }
            }

            return  new Module();
        }
        public static ArrayList<Module> deleteModuleById(int id){
            DB.modules.remove(getModuleById(id));
            return  DB.modules;
        }

        public static Module getModuleById(int id){
            for (Module module: DB.modules) {
                if (module.getId() == id);
                return  module;
            }
            return  new Module();
        }

        public static ArrayList<Module> getAllModule(){
            return  DB.modules;
        }
    }


