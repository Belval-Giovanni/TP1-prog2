import java.util.Set;

public class Carnivore extends Organisme {

    double debrouillardise;
    Set<String> aliments;

    public Carnivore(String nomEspece,double[] args , Set<String> aliments){
        

        super(nomEspece,args);

        this.debrouillardise = args[6];
        this.aliments = aliments;

        //TODO aliments par defauts
    }

    public double getDebrouillardise(){
        return this.debrouillardise;
    }

    public Set<String> getAliments(){
        return this.aliments;
    }

    double[] getInfo() //renvoi un tableau contenant toute les information numerique d'un carnivore.
    {
        double[] info = {energieEnfant,besoinEnergie,efficaciteEnergie,resilience,fertilite,ageFertilite,debrouillardise};
        return info;
    }
}
