import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.*;

public final class Lac {
    private final int energieSolaire;
    private List<Plante> plantes; //* j'ai retiré l'attribut final
    private List<Herbivore> herbivores;
    private List<Carnivore> carnivores;

    public Lac(int energieSolaire, List<Plante> plantes, List<Herbivore> herbivores , List<Carnivore> carnivores) {
        this.energieSolaire = energieSolaire;
        this.plantes = plantes;
        this.herbivores = herbivores;
        this.carnivores = carnivores;
    }

    /**
     * Avance la simulation d'un cycle.
     */
    public void tick() {

        System.out.println("*****************"); //! test
        System.out.println("debut d'un cycle"); //! test


        // calcul de l'energie totale : 

        double energieTotale = 0;

        for(Plante plante : plantes){
            energieTotale+=plante.getEnergie();
        }

        System.out.println("______________________________________________________"); //! test
        System.out.println("energieTotale contenu par les plante : "+energieTotale); //! test

        //________________________________

        List<Plante> newBorn = new ArrayList<>(); //contient les instance des plantes qui vont etre crée
        List<Herbivore> newHerbivores =  new ArrayList<>(); //contient les instance des herbivores qui vont etre crée
        List<Carnivore> newCarnivores = new ArrayList<>(); //contient toute les instance de carnivore qui vont etre crée

        // deroulement d'un cycle : 


        for(Plante plante:plantes){

            System.out.println("______________________________________________________"); //! test

            System.out.println("traitement d'une plante "); //! test

            double energieAbsorbe;

            if (energieTotale == 0){
                energieAbsorbe = 0.0; //pour ne pas avoir une division par zero.
            }

            else{
                energieAbsorbe = energieSolaire*plante.getEnergie()/energieTotale; //sinon on fait le calcul normal.
            }

            double difference = (plante.getBesoinEnergie()-energieAbsorbe);

            if(energieAbsorbe<plante.getBesoinEnergie()) // la plante ne reçoit pas assez d'energie
            {

                System.out.println("je n'ai pas ressu asez d'energie"); //! test

                double probaSurvie = Math.pow(plante.getResilience(),(int)difference); //calcul de la chance de survivre.

                if(Math.random()>probaSurvie){
                    plante.energie = 0;
                    //* la plante meurt | elle doit etre supprimé de plantes.
                    System.out.println("je suis une plante qui vient de mourrir par manque d'energie "); //! test
                }

                else {
                    plante.energie -= difference;
                    //* la plante survie on enleve l'energie manquante a son energie
                    System.out.println("j'ai survécu au manque d'energie"); //! test
                }

            }

            else //la plante reçoit assez d'energie
            {

                System.out.println("j'ai ressus asez d'energie"); //! test

                difference = Math.abs(difference);

                System.out.println("differance : "+difference); //! test

                if(plante.getAge()>=plante.getAgeFertilite()){

                    //si la plante est assez mature pour faire des bébé plantes

                    int nbRoulage = 0;
                    int nbUniteEnergie = (int)difference;

                    while ((nbRoulage<nbUniteEnergie) && ((int)difference != 0)) {

                        if(Math.random()<=plante.getFertilite()){ //la plante fais un enfant durant ce roulage

                            newBorn.add(new Plante(plante.getNomEspece(),plante.getInfo())); 

                            System.out.println("j'ai fais un bébé plante"); //! test


                            if(plante.getEnergieEnfant()<=(int)difference-nbRoulage){
                                nbRoulage += plante.getEnergieEnfant();
                                difference -= plante.getEnergieEnfant();
                            }

                            else{
                                plante.energie += difference - plante.getEnergieEnfant();
                                nbRoulage = (int)difference;
                                difference -= (int)difference;
                            }

                        }

                        else{ //la plante ne fait pas d'enfant durant ce roulage

                            //j'administre a la plante cette unité d'energie pour laquelle elle n'a pas fait d'enfant.

                            plante.energie += plante.getEfficaciteEnergie();

                            // je met a jour le nombre de roulage encore possible 
                            nbRoulage++;
                            difference--;

                        }

                    }
                    
                }

                else{// la plante n'est pas en age de faire des enfant mais a reçu assez d'energie

                    plante.energie+= Math.abs(difference)*plante.getEfficaciteEnergie();//on rajoute l'energie qui reste.

                    System.out.println("energie rajouté : "+Math.abs(difference)*plante.getEfficaciteEnergie()); //! test
                }
            }

            plante.age+=1;
        }

    
        // mise niveau de plantes.


        List<Plante> clean = new ArrayList<>();

        for(Plante plante : plantes) // on supprime les plante mortes
        {
            if(!(plante.getEnergie() <= 0)){
                clean.add(plante); //on rajoute dans clean les plantes vivante i.e d'energie non null
            }

        }

        for(Plante plante: newBorn){ // on rajoute les plantes qui vienne de venir au monde
            clean.add(plante);
        }

        this.plantes = clean;
        //____________________________________

        //on met a niveau les plantes (dnt les herbivores vont se nourrir )

        List<Plante> clean2 = new ArrayList<>();

        for(Plante plante : plantes) // on supprime les plante mortes
        {
            if(!(plante.getEnergie() <= 0)){
                clean2.add(plante); //on rajoute dans clean les plantes vivante i.e d'energie non null
            }

        }

        for(Plante plante: newBorn){ // on rajoute les plantes qui vienne de venir au monde
            clean2.add(plante);
        }

        this.plantes = clean2;


        //! tick pour les herbivores :

        for(Herbivore herbivore : herbivores){

            System.out.println("______________________________________________________"); //! test


            System.out.println("je traite un herbivore ");  //! test

            double energieLatente = 0; //energie en attente de l'herbivore 

            Iterator iterator= herbivore.getAliments().iterator(); 

            List<String> aliments = new ArrayList<>(); // list contenant le nom des aliments

            while(iterator.hasNext()){ // on met les element dans cette liste
                aliments.add((String) iterator.next());
            } 

            System.out.println("liste des aliments : "+aliments);  //! test

            
            double aleatoire = Math.random(); //!test

            System.out.println("nombre aleatoire : "+aleatoire);  //! test
            System.out.println("debrouillarise : "+herbivore.getDebrouillardise());  //! test

            while (aleatoire<=herbivore.getDebrouillardise()) {

                aleatoire = Math.random(); //!test

                System.out.println("j'arrive au calcul de l'energie");  //! test
                
                int indexAliment = (int)(Math.random()*aliments.size()); //on choisis au hazard l'index de l'aliment a mangé

                String aliment = aliments.get(indexAliment); //on selectionne l'aiment d'index indexAliment

                
                System.out.println("nom de l'aliment selectionné : "+aliment);  //! test

                //on selectionne une plante au hasard qui a aliment comme nom de l'espece:

                List<Plante> nourritures = new ArrayList<>();

                for (Plante plante : plantes) {
                    if(plante.getNomEspece().equals(aliment)){
                        nourritures.add(plante);
                    }
                }

                if (nourritures.size() == 0){
                    break;
                }

                int indexNourriture = (int)(Math.random()*nourritures.size());

                Plante nourriture = nourritures.get(indexNourriture);

                // on ajoute l'energie de la plante a lenergie latente

                double intervalle = herbivore.getVoraciteMax() - herbivore.getVoraciteMin();

                // on ajoute a l'energie latente de la plante une fration de la plante 
                // entre voracitéMin fois l'energie et voracitéMax fois l'energie

                System.out.println("energie minimale a absorbé  : "+herbivore.getVoraciteMin()*nourriture.getEnergie()); // ! test
                System.out.println("energie absorbé max : "+herbivore.getVoraciteMax()*nourriture.getEnergie()); // ! test

                double energieAjouter = nourriture.getEnergie()*(herbivore.getVoraciteMin() + Math.random()*(intervalle));;

                energieLatente += energieAjouter;

                System.out.println("voici l'energie calculé partielle: "+energieLatente);  //! test

                nourriture.energie -= energieAjouter; //on retire a la plante l'energie absorber par l'herbivore

            }

            System.out.println("voici l'energie calculé Totale: "+energieLatente);  //! test
            System.out.println("voici l'energie dont j'ai besoin : "+herbivore.getBesoinEnergie());  //! test

            double difference = (herbivore.getBesoinEnergie()-energieLatente);

            if(energieLatente<herbivore.getBesoinEnergie()) // la herbivore ne reçoit pas assez d'energie
            {
                System.out.println("je n'ai pas assez d'energie"); //! test
                

                double probaSurvie = Math.pow(herbivore.getResilience(),(int)difference); //calcul de la chance de survivre.

                System.out.println("ma probabilité de survivre est : "+probaSurvie);

                if(Math.random()>probaSurvie){
                    herbivore.energie = 0;
                    //* la herbivore meurt | elle doit etre supprimé de herbivores.

                    System.out.println("je suis un animal mort par manque d'energie"); //! test
                }


                else {
                    System.out.println("j'ai survécu au manque d'energie | animal"); //! test
                    System.out.println("je detient une energie actuelle de :"+herbivore.getEnergie()); //! test

                    herbivore.energie -= difference;
                    //* la herbivore survie on enleve l'energie manquante a son energie
                }

            }

            else //la herbivore reçoit assez d'energie
            {

                difference = Math.abs(difference);

                if(herbivore.getAge()>=herbivore.getAgeFertilite()){

                    //si la herbivore est assez mature pour faire des bébé herbivores

                    int nbRoulage = 0;
                    int nbUniteEnergie = (int)difference;

                    while ((nbRoulage<nbUniteEnergie) && ((int)difference != 0)) {

                        if(Math.random()<=herbivore.getFertilite()){ //la herbivore fais un enfant durant ce roulage

                            newHerbivores.add(new Herbivore(herbivore.getNomEspece(),herbivore.getInfo(),herbivore.aliments)); 

                            System.out.println("je suis un animal qui a fait un enfant "); //! test

                            System.out.println("nombre de roulage effectué : "+nbRoulage); //! test

                            System.out.println("unité d'energie restante :  "+(int)difference); //! test

                            if(herbivore.getEnergieEnfant()<=(int)difference-nbRoulage){
                                nbRoulage += herbivore.getEnergieEnfant();
                                difference -= herbivore.getEnergieEnfant();
                            }

                            else{
                                herbivore.energie += difference - herbivore.getEnergieEnfant();
                                nbRoulage = (int)difference;
                                difference -= (int)difference;
                            }

                        }

                        else{ //la herbivore ne fait pas d'enfant durant ce roulage

                            //j'administre a l'herbivore cette unité d'energie qui n'a pas pu etre utilisé pour faire un enfant

                            herbivore.energie += herbivore.getEfficaciteEnergie();

                            // je met a niveau le nombre de roulage

                            nbRoulage++;
                            difference--;

                        }

                    }
                    
                }

                else{// la herbivore n'est pas en age de faire des enfant mais a reçu assez d'energie

                    herbivore.energie+= Math.abs(difference)*herbivore.getEfficaciteEnergie();//on rajoute l'energie qui reste.
                    System.out.println("j'ai reçu "+Math.abs(difference)*herbivore.getEfficaciteEnergie()+"energie "); //! test
                }
            }

            herbivore.age+=1;

        }

        // mise niveau de plantes et de herbivores.


        clean2 = new ArrayList<>();

        for(Plante plante : plantes) // on supprime les plante mortes
        {
            if(!(plante.getEnergie() <= 0)){
                clean2.add(plante); //on rajoute dans clean les plantes vivante i.e d'energie non null
            }

        }

        //cette fois on ne rajoute pas les plantes qui sont née car cela a déja été fais avant la partie
        //sur les herbivores , et des plantes ne sont pas née depuis ce moment.

        this.plantes = clean2;

        List<Herbivore> clean3 = new ArrayList<>();

        for(Herbivore herbivore_ : herbivores) // on supprime les herbivore mortes
        {
            if(!(herbivore_.getEnergie() <= 0)){
                clean3.add(herbivore_); //on rajoute dans clean les herbivores vivante i.e d'energie non null
            }

        }

        for(Herbivore herbivore_ : newHerbivores){
            clean3.add(herbivore_);
        }

        this.herbivores = clean3;

        //! tick pour les carnivore ----------------------------------------------------------------

        for(Carnivore carnivore : carnivores){

            System.out.println("______________________________________________________"); //! test


            System.out.println("je traite un carnivore ");  //! test

            double energieLatente = 0; //energie en attente de l'carnivore 

            Iterator iterator= carnivore.getAliments().iterator(); 

            List<String> aliments = new ArrayList<>(); // list contenant le nom des aliments

            while(iterator.hasNext()){ // on met les element dans cette liste
                aliments.add((String) iterator.next());
            } 

            System.out.println("liste des aliments : "+aliments);  //! test

            
            double aleatoire = Math.random(); //!test

            System.out.println("nombre aleatoire : "+aleatoire);  //! test
            System.out.println("debrouillarise : "+carnivore.getDebrouillardise());  //! test

            while (aleatoire<=carnivore.getDebrouillardise()) {

                aleatoire = Math.random(); //!test

                System.out.println("j'arrive au calcul de l'energie");  //! test
                
                int indexAliment = (int)(Math.random()*aliments.size()); //on choisis au hazard l'index de l'aliment a mangé

                String aliment = aliments.get(indexAliment); //on selectionne l'aiment d'index indexAliment

                
                System.out.println("nom de l'aliment selectionné : "+aliment);  //! test

                //on selectionne une plante au hasard qui a aliment comme nom de l'espece:

                List<Herbivore> nourritures = new ArrayList<>();

                for (Herbivore herbivore : herbivores) {
                    if(herbivore.getNomEspece().equals(aliment)){
                        nourritures.add(herbivore);
                    }
                }

                if (nourritures.size() == 0){
                    break;
                }

                int indexNourriture = (int)(Math.random()*nourritures.size());

                Herbivore nourriture = nourritures.get(indexNourriture);

                // on ajoute l'energie de l'herbibore  a lenergie latente


                double energieAjouter = nourriture.getEnergie();

                energieLatente += energieAjouter;

                System.out.println("voici l'energie calculé partielle: "+energieLatente);  //! test

                nourriture.energie -= energieAjouter; //on retire a l'herbivore  l'energie absorber par l'carnivore

            }

            System.out.println("voici l'energie calculé Totale: "+energieLatente);  //! test
            System.out.println("voici l'energie dont j'ai besoin : "+carnivore.getBesoinEnergie());  //! test

            double difference = (carnivore.getBesoinEnergie()-energieLatente);

            if(energieLatente<carnivore.getBesoinEnergie()) // la carnivore ne reçoit pas assez d'energie
            {
                System.out.println("je n'ai pas assez d'energie"); //! test
                

                double probaSurvie = Math.pow(carnivore.getResilience(),(int)difference); //calcul de la chance de survivre.

                System.out.println("ma probabilité de survivre est : "+probaSurvie);

                if(Math.random()>probaSurvie){
                    carnivore.energie = 0;
                    //* la carnivore meurt | elle doit etre supprimé de carnivores.

                    System.out.println("je suis un animal mort par manque d'energie"); //! test
                }


                else {
                    System.out.println("j'ai survécu au manque d'energie | animal"); //! test
                    System.out.println("je detient une energie actuelle de :"+carnivore.getEnergie()); //! test

                    carnivore.energie -= difference;
                    //* la carnivore survie on enleve l'energie manquante a son energie
                }

            }

            else //la carnivore reçoit assez d'energie
            {

                difference = Math.abs(difference);

                if(carnivore.getAge()>=carnivore.getAgeFertilite()){

                    //si la carnivore est assez mature pour faire des bébé carnivores

                    int nbRoulage = 0;
                    int nbUniteEnergie = (int)difference;

                    while ((nbRoulage<nbUniteEnergie) && ((int)difference != 0)) {

                        if(Math.random()<=carnivore.getFertilite()){ //la carnivore fais un enfant durant ce roulage

                            newCarnivores.add(new Carnivore(carnivore.getNomEspece(),carnivore.getInfo(),carnivore.aliments)); 

                            System.out.println("je suis un animal qui a fait un enfant "); //! test

                            System.out.println("nombre de roulage effectué : "+nbRoulage); //! test

                            System.out.println("unité d'energie restante :  "+(int)difference); //! test

                            if(carnivore.getEnergieEnfant()<=(int)difference-nbRoulage){
                                nbRoulage += carnivore.getEnergieEnfant();
                                difference -= carnivore.getEnergieEnfant();
                            }

                            else{
                                carnivore.energie += difference - carnivore.getEnergieEnfant();
                                nbRoulage = (int)difference;
                                difference -= (int)difference;
                            }

                        }

                        else{ //la carnivore ne fait pas d'enfant durant ce roulage

                            //j'administre a l'carnivore cette unité d'energie qui n'a pas pu etre utilisé pour faire un enfant

                            carnivore.energie += carnivore.getEfficaciteEnergie();

                            // je met a niveau le nombre de roulage

                            nbRoulage++;
                            difference--;

                        }

                    }
                    
                }

                else{// la carnivore n'est pas en age de faire des enfant mais a reçu assez d'energie

                    carnivore.energie+= Math.abs(difference)*carnivore.getEfficaciteEnergie();//on rajoute l'energie qui reste.
                    System.out.println("j'ai reçu "+Math.abs(difference)*carnivore.getEfficaciteEnergie()+"energie "); //! test
                }
            }

            carnivore.age+=1;

        }

        List<Carnivore> cleanC = new ArrayList<>();

        for(Carnivore carnivore_ : carnivores) // on supprime les carnivore mortes
        {
            if(!(carnivore_.getEnergie() <= 0)){
                cleanC.add(carnivore_); //on rajoute dans clean les carnivores vivante i.e d'energie non null
            }

        }

        for(Carnivore carnivore_ : newCarnivores){
            cleanC.add(carnivore_);
        }

        this.carnivores = cleanC;

        //! _________________________________________________________________________________________

        System.out.println("nombre d'animaux restant : "+herbivores.size()); // ! test
        System.out.println("nombre de plantes restante : "+plantes.size()); //! test
        System.out.println("nombre de plantes restante : "+carnivores.size()); //! test

        
        //____________________________________

    }



    public void imprimeRapport(PrintStream out) {
        var especes = this.plantes.stream().collect(groupingBy(
                Plante::getNomEspece,
                summarizingDouble(Plante::getEnergie)));
        out.println("Il reste " + especes.size() + " espèces de plantes.");
        for (var entry : especes.entrySet()) {
            var value = entry.getValue();
            out.printf(
                "%s: %d individus qui contiennent en tout %.2f unités d'énergie.",
                entry.getKey(),
                value.getCount(),
                value.getSum());
        }
    }

    public void imprimeRapportH(PrintStream out) { //! test
        var especes = this.herbivores.stream().collect(groupingBy(
                Herbivore::getNomEspece,
                summarizingDouble(Herbivore::getEnergie)));
        out.println("Il reste " + especes.size() + " espèces de herbivores.");
        for (var entry : especes.entrySet()) {
            var value = entry.getValue();
            out.printf(
                "%s: %d individus qui contiennent en tout %.2f unités d'énergie.",
                entry.getKey(),
                value.getCount(),
                value.getSum());
        }
    }

    public void imprimeRapportC(PrintStream out) { //! test
        var especes = this.carnivores.stream().collect(groupingBy(
                Carnivore::getNomEspece,
                summarizingDouble(Carnivore::getEnergie)));
        out.println("Il reste " + especes.size() + " espèces de carnivores.");
        for (var entry : especes.entrySet()) {
            var value = entry.getValue();
            out.printf(
                "%s: %d individus qui contiennent en tout %.2f unités d'énergie.",
                entry.getKey(),
                value.getCount(),
                value.getSum());
        }
    }
}
