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
 

        // calcul de l'energie totale : 

        double energieTotale = 0;

        for(Plante plante : plantes){
            energieTotale+=plante.getEnergie();
        }

        //________________________________

        // deroulement d'un cycle : 

        for(Plante plante:plantes){

            double energieAbsorbe = energieSolaire*plante.getEnergie()/energieTotale;

            if(energieAbsorbe<plante.getBesoinEnergie()){
                int difference = (int)(plante.getBesoinEnergie()-energieAbsorbe);
                double probaSurvie = Math.pow(plante.getResilience(),difference); //calcul de la chance de survivre.

                if(Math.random()>probaSurvie){
                    plante.energie = 0;
                    //TODO la plante meurt | elle doit etre supprimé de plantes.
                }

            }

        }

    
        // mise niveau de plantes.


        List<Plante> clean = new ArrayList<>();

        for(Plante plante : plantes) //TODO mise a niveau de plantes (suppression et ajout de plante).
        {
            if(!(plante.getEnergie() == 0)){
                clean.add(plante); //on rajoute dans clean les plantes vivante i.e d'energie non null
            }

        }

        this.plantes = clean;



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
