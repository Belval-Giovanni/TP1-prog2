public class Plante {
    String nomEspece;
    double energie;
    int age;
    double besoinEnergie;
    double efficaciteEnergie;
    double resilience;
    double fertilite;
    int ageFertilite;
    double energieEnfant;


    // constructeurs : 

    public Plante(String nomEspece,double []args){

        this.nomEspece = nomEspece;
        this.energie = args[0];
        this.age =  0;
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
        return fertilite;
    }

    public int getAgeFertilite() {
        return ageFertilite;
    }

    public double getEnergieEnfant() {
        return energieEnfant;
    }

    // methode test : //! a ssuprimmer avant l'envoi du fichier 


    public void info(){
        System.out.println("nomEspece : "+this.nomEspece);
        System.out.println("energieEnfant : "+this.energieEnfant);
        System.out.println("_________________________");
    }
}


