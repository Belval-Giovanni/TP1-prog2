public abstract class Usine {
    
    String nomEspece;
    double besoinEnergie;
    double efficaciteEnergie;
    double resilience;
    double fertilite;
    int ageFertilite;
    double energieEnfant;

    double[] tableau = {0,0,0,0,0,0,0,0,0,0,0};

    // setters :


    public void setNomEspece(String nomEspece) {
        this.nomEspece = nomEspece;
    }

    public void setBesoinEnergie(double besoinEnergie) {
        this.besoinEnergie = besoinEnergie;
        this.tableau[1] = besoinEnergie;
    }

    public void setEfficaciteEnergie(double efficaciteEnergie) {

        //* nous avons que efficacité energie est bornée entre 0 et 1.

        if(efficaciteEnergie<0){
            this.efficaciteEnergie = 0;
            this.tableau[2] = 0;
            return;
        } 

        if(efficaciteEnergie>1){
            this.efficaciteEnergie = 1;
            this.tableau[2] = 1;
            return;
        }

        this.efficaciteEnergie = efficaciteEnergie;
        this.tableau[2] = efficaciteEnergie;
    }

    public void setResilience(double resilience) {

        //* nous avons que resilience est bornée entre 0 et 1.

        if(resilience<0){
            this.resilience = 0;
            this.tableau[3] = 0;
            return;
        } 

        if(resilience>1){
            this.resilience = 1;
            this.tableau[3] = 1;
            return;
        }

        this.resilience = resilience;
        this.tableau[3] = resilience;
    }

    public void setFertilite(double fertilite) {

        //* nous avons que fertilite est bornée entre 0 et 1.

        if(fertilite<0){
            this.fertilite = 0;
            this.tableau[4] = 0;
            return;
        } 

        if(fertilite>1){
            this.fertilite = 1;
            this.tableau[4] = 1;
            return;
        }

        this.fertilite = fertilite;
        this.tableau[4] = fertilite;
    }

    public void setAgeFertilite(int ageFertilite) {
        this.tableau[5] = ageFertilite;
        this.ageFertilite = ageFertilite;
    }

    public void setEnergieEnfant(double energieEnfant) {
        this.tableau[0] = energieEnfant;
        this.energieEnfant = energieEnfant;
    }

    //___________________________________________
    
}
