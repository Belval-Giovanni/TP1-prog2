public class Plante extends Organisme{
    
    // constructeurs : 

    public Plante(String nomEspece,double []args){

        super(nomEspece,args);

    }


    // methode utile : 

    double[] getInfo() //renvoi un tableau contenant toute les information numerique d'une plante.
    {
        double[] info = {energieEnfant,besoinEnergie,efficaciteEnergie,resilience,fertilite,ageFertilite};
        return info;
    }


}


