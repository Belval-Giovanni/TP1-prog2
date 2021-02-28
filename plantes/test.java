import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Plante> plantes = new ArrayList<>();

        UsinePlante usine = new UsinePlante();

        usine.setNomEspece("rose");
        usine.setEnergieEnfant(150);
        Plante plante1 = usine.creerPlante();
        plantes.add(plante1);

        usine.setNomEspece("Tim");
        usine.setEnergieEnfant(200);
        Plante plante2 = usine.creerPlante();
        plantes.add(plante2);

        usine.setNomEspece("Violet");
        usine.setEnergieEnfant(400);
        Plante plante3 = usine.creerPlante();
        plantes.add(plante3);

        for(Plante plante: plantes){
            plante.info();
        }

        plantes.remove(plante1);

        System.out.println("********************");
        System.out.println("apres suppression : ");
        System.out.println("*********************");

        for(Plante plante: plantes){
            plante.info();
        }

    }

}
