import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Herbivore extends Organisme {
    
    double debrouillardise;
    private double voraciteMin;
    private double voraciteMax;
    double tailleMaximum;
    Set<String> aliments;

    public Herbivore(String nomEspece,double[] args , Set<String> aliments){
        

        super(nomEspece,args);

        this.debrouillardise = args[6];
        this.voraciteMin = args[7];
        this.voraciteMax = args[8];
        this.aliments = aliments;
        this.tailleMaximum = args[9];

        if(aliments.size() == 0){
            this.aliments = new LinkedHashSet<String>();
            aliments.add("Bident de Beck");
        }
    }

    public double getDebrouillardise(){
        return this.debrouillardise;
    }

    public double getVoraciteMin(){
        return this.voraciteMin;
    }

    public double getVoraciteMax(){
        return this.voraciteMax;
    }

    public Set<String> getAliments(){
        return this.aliments;
    }

    public double getTailleMaximum(){
        return tailleMaximum;
    }

    double[] getInfo() //renvoi un tableau contenant toute les information numerique d'un carnivore.
    {
        double[] info = {energieEnfant,besoinEnergie,efficaciteEnergie,resilience,fertilite,ageFertilite,debrouillardise,voraciteMin,voraciteMax,tailleMaximum};
        return info;
    }


}
