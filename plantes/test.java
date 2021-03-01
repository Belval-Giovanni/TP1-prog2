import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args){
        UsineHerbivore usine  = new UsineHerbivore();
        usine.setBesoinEnergie(12);
        usine.setNomEspece("kurapica");
        usine.setFertilite(0.1);
        usine.setAgeFertilite(12);
        usine.setDebrouillardise(15);
        usine.setEnergieEnfant(15);

        Herbivore herbivore = usine.creerHerbivore();
        System.out.println("nom de l'espece : "+herbivore.getNomEspece());
        System.out.println("besoinEnergie : "+herbivore.getBesoinEnergie());
        System.out.println("fertilite: "+herbivore.getFertilite());

        System.out.println("Agefertilité : "+herbivore.getAgeFertilite());
        System.out.println("Debrouillardise : "+herbivore.getDebrouillardise());
        // System.out.println("besoinEnergie : "+herbivore.getBesoinEnergie());
        // System.out.println("besoinEnergie : "+herbivore.getBesoinEnergie());
        // System.out.println("besoinEnergie : "+herbivore.getBesoinEnergie());
        // System.out.println("besoinEnergie : "+herbivore.getBesoinEnergie());
    }
    

}
