import javax.xml.stream.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Le point d'entré du programme. Il reconnait l'option --nombre-cycles qui spécifie la durée de la simulation et lit
 * les conditions initiales sur l'entrée standard du processus.
 */
public final class Simulation {
    public static void main(String[] args) {
        int nombreCycles = 50;

        Iterator<String> argsIterator = Arrays.stream(args).iterator();
        while (argsIterator.hasNext()) {
            var next = argsIterator.next();
            switch (next) {
                case "--nombre-cycles" -> nombreCycles = Integer.parseInt(argsIterator.next());
                default -> {
                    System.out.println("erreur: option inconnue \"" + next + "\"");
                    return;
                }
            }
        }

        var xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader;
        try {
            reader = xmlInputFactory.createXMLEventReader(System.in);
        } catch (XMLStreamException e) {
            System.out.println("erreur: je ne peux pas lire les conditions initiales");
            e.printStackTrace();
            return;
        }

        var conditionsInitiales = new ConditionsInitiales(reader);
        Lac lac;
        try {
            lac = conditionsInitiales.nextLac();
        } catch (ConditionsInitialesInvalides e) {
            System.out.println("erreur: les conditions initiales sont invalides");
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < nombreCycles; i += 1) {
            lac.tick();
        }

        System.out.println("Après " + nombreCycles + " cycles :");
        lac.imprimeRapport(System.out);
    }
}
