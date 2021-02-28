import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

public final class Lac {
    private final int energieSolaire;
    private List<Plante> plantes; //* j'ai retiré l'attribut final

    public Lac(int energieSolaire, List<Plante> plantes) {
        this.energieSolaire = energieSolaire;
        this.plantes = plantes;
    }

    /**
     * Avance la simulation d'un cycle.
     */
    public void tick() {

        System.out.println("____________________");   //! test
        System.out.println("cycle numéro : "+plantes.get(0).getAge()); //! test
        
 

        // calcul de l'energie totale : 

        double energieTotale = 0;

        for(Plante plante : plantes){
            energieTotale+=plante.getEnergie();
        }

        //________________________________

        List<Plante> newBorn = new ArrayList<>(); //contient les instance des plantes qui vont etre crée

        // deroulement d'un cycle : 


        for(Plante plante:plantes){

            double energieAbsorbe = energieSolaire*plante.getEnergie()/energieTotale;
            double difference = (plante.getBesoinEnergie()-energieAbsorbe);

            System.out.println("energie absorbé : "+energieAbsorbe); //! test

            if(energieAbsorbe<plante.getBesoinEnergie()) // la plante ne reçoit pas assez d'energie
            {
                double probaSurvie = Math.pow(plante.getResilience(),(int)difference); //calcul de la chance de survivre.

                if(Math.random()>probaSurvie){
                    plante.energie = 0;
                    //* la plante meurt | elle doit etre supprimé de plantes.
                }

                else {
                    plante.energie -= difference;
                    //* la plante survie on enleve l'energie manquante a son energie
                }

            }

            else //la plante reçoit assez d'energie
            {
                System.out.println("j'ai reçu assez d'energie"); //! test

                difference = Math.abs(difference);

                System.out.println("age de la plante : "+plante.getAge()); //! test

                if(plante.getAge()>=plante.getAgeFertilite()){

                    System.out.println("j'ai l'age pour avoir un enfant"); //! test
                    System.out.println("nombre d'energie sup : "+(int)difference); //! test

                    //si la plante est assez mature pour faire des bébé plantes

                    int nbRoulage = 0;
                    int nbUniteEnergie = (int)difference;

                    while ((nbRoulage<nbUniteEnergie) && (difference != 0)) {

                        System.out.println("nb de roulage  "+nbRoulage); //! test
                        System.out.println("unité d'energie restante "+(int)difference); //! test

                        // double probaEnfant = Math.random(); //! test
                        // System.out.println("nombre aleatoire generé "+probaEnfant); //! test
                        // System.out.println("probabilité d'avoir un enfant "+plante.getFertilite()); //! test

                        if(Math.random()<=plante.getFertilite()){ //la plante fais un enfant durant ce roulage

                            System.out.println("nouvel enfant cree"); //! test

                            newBorn.add(new Plante(plante.getNomEspece(),plante.getInfo())); 


                            if(plante.getEnergieEnfant()<=(int)difference-nbRoulage){
                                nbRoulage += plante.getEnergieEnfant();
                                difference -= plante.getEnergieEnfant();
                            }

                            else{
                                plante.energie += difference - plante.getEnergieEnfant();
                                nbRoulage = (int)difference;
                                difference -= (int)difference;
                            }

                        }

                        else{ //la plante ne fait pas d'enfant durant ce roulage

                            nbRoulage++;
                            difference--;

                        }

                    }
                    
                }

                else{// la plante n'est pas en age de faire des enfant mais a reçu assez d'energie

                    plante.energie+= Math.abs(difference)*plante.getEfficaciteEnergie();//on rajoute l'energie qui reste.
                }
            }

            plante.age+=1;
        }

    
        // mise niveau de plantes.


        List<Plante> clean = new ArrayList<>();

        for(Plante plante : plantes) // on supprime les plante mortes
        {
            if(!(plante.getEnergie() <= 0)){
                clean.add(plante); //on rajoute dans clean les plantes vivante i.e d'energie non null
            }

        }

        for(Plante plante: newBorn){ // on rajoute les plantes qui vienne de venir au monde
            clean.add(plante);
        }

        this.plantes = clean;

        System.out.println("nombre de nouveau née : "+newBorn.size()); //! test

        System.out.println("nombre de survivant : "+plantes.size()); //! test

        System.out.println("____________________"); //! test


        //____________________________________
    }



    public void imprimeRapport(PrintStream out) {
        var especes = this.plantes.stream().collect(groupingBy(
                Plante::getNomEspece,
                summarizingDouble(Plante::getEnergie)));
        out.println("Il reste " + especes.size() + " espèces de plantes.");
        for (var entry : especes.entrySet()) {
            var value = entry.getValue();
            out.printf(
                "%s: %d individus qui contiennent en tout %.2f unités d'énergie.",
                entry.getKey(),
                value.getCount(),
                value.getSum());
        }
    }
}
