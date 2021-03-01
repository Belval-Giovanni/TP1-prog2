import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UsineHerbivore extends Usine{
    
    double debrouillardise;
    double voraciteMin;
    double voraciteMax;
    Set<String> aliments = new LinkedHashSet<String>();
    
    // double[] tableau2 = {tableau[0],tableau[1],tableau[2],tableau[3],tableau[4],tableau[5],0,0,0}; //sert à stocker les information numerique pertinante.

    public void setDebrouillardise(double debrouillardise){

        //* nous avons que debrouillardise est bornée entre 0 et 1.

        if(debrouillardise<0){
            this.debrouillardise = 0;
            this.tableau[6] = 0;
            return;
        } 

        if(debrouillardise>1){
            this.debrouillardise = 1;
            this.tableau[6] = 1;
            return;
        }

        this.debrouillardise = debrouillardise;
        this.tableau[6] = debrouillardise;
    }

    public void setVoraciteMin(double voraciteMin){

        //* nous avons que voraciteMin est bornée entre 0 et 1.

        if(voraciteMin<0){
            this.voraciteMin = 0;
            this.tableau[7] = 0;
            return;
        } 

        if(voraciteMin>1){
            this.voraciteMin = 1;
            this.tableau[7] = 1;
            return;
        }

        if(voraciteMin>this.voraciteMax){
            this.voraciteMin = this.voraciteMax;
            this.tableau[7] = this.voraciteMax;
        }

        this.voraciteMin = voraciteMin;
        this.tableau[7] = voraciteMin;
    }

    public void setVoraciteMax(double voraciteMax){

        //* nous avons que voraciteMax est bornée entre 0 et 1.

        if(voraciteMax<0){
            this.voraciteMax = 0;
            this.tableau[8] = 0;
            return;
        } 

        if(voraciteMax>1){
            this.voraciteMax = 1;
            this.tableau[8] = 1;
            return;
        }

        this.voraciteMax = voraciteMax;
        this.tableau[8] = voraciteMax;
    }

    public void addAliment(String aliment){
        aliments.add(aliment);
    }

    // methodes utiles : 

    public Herbivore creerHerbivore(){
        return new Herbivore(this.nomEspece,this.tableau,this.aliments);
    }

    //_______________________________

    

    


}
