public abstract class Organisme {
    
    String nomEspece;
    double energie;
    int age;
    double besoinEnergie;
    double efficaciteEnergie;
    double resilience;
    double fertilite;
    int ageFertilite;
    double energieEnfant;

    public Organisme(String nomEspece,double []args){

        this.nomEspece = nomEspece;
        this.energie = args[0];
        this.age =  15;   //TODO a remmetre a zero
        this.besoinEnergie = args[1];
        this.efficaciteEnergie = args[2];
        this.resilience = args[3];
        this.fertilite= args[4];
        this.ageFertilite= (int)args[5];
        this.energieEnfant = args[0];

    }

    // getters de la classe : 


    public String getNomEspece() {
        return nomEspece;
    }

    public double getEnergie() {
        return energie;
    }

    public int getAge() {
        return age;
    }

    public double getBesoinEnergie() {
        return besoinEnergie;
    }

    public double getEfficaciteEnergie() {
        return efficaciteEnergie;
    }

    public double getResilience() {
        return resilience;
    }

    public double getFertilite() {
        return this.fertilite;
    }

    public int getAgeFertilite() {
        return ageFertilite;
    }

    public double getEnergieEnfant() {
        return energieEnfant;
    }
}
