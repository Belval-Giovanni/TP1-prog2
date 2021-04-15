import java.util.LinkedHashSet;
import java.util.Set;

public class UsineCarnivore extends Usine{
    double debrouillardise;
    double tailleMaximum;
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

   

    public void addAliment(String aliment){
        aliments.add(aliment);
    }

    public void setTailleMaximum(double tailleMaximum){
        if(tailleMaximum>0){
            
            this.tailleMaximum = tailleMaximum;
            this.tableau[9] = tailleMaximum;
        }
        else{
            this.tailleMaximum = 10*this.energieEnfant;
            this.tableau[9] = 10*this.energieEnfant;
        }
    }

    // methodes utiles : 

    public Carnivore creerCarnivore(){
        return new Carnivore(this.nomEspece,this.tableau,this.aliments);
    }

    //_______________________________

}
