import java.util.LinkedHashSet;
import java.util.Set;

public class Carnivore extends Organisme {

    double debrouillardise;
    double tailleMaximum;
    Set<String> aliments;

    public Carnivore(String nomEspece,double[] args , Set<String> aliments){
        

        super(nomEspece,args);

        this.debrouillardise = args[6];
        this.aliments = aliments;
        this.tailleMaximum = args[7];

        if(aliments.size() == 0){
            this.aliments = new LinkedHashSet<String>();
            aliments.add("Mulet perlé");
        }
    }

    public double getDebrouillardise(){
        return this.debrouillardise;
    }

    public Set<String> getAliments(){
        return this.aliments;
    }

    public double getTailleMaximum(){
        return tailleMaximum;
    }

    double[] getInfo() //renvoi un tableau contenant toute les information numerique d'un carnivore.
    {
        double[] info = {energieEnfant,besoinEnergie,efficaciteEnergie,resilience,fertilite,ageFertilite,debrouillardise,tailleMaximum};
        return info;
    }
}
