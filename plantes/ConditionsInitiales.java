import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Les conditions initiales de la simulation sont stockées dans des fichier XML. Le rôle de cette classe est de lire de
 * tels fichier.
 *
 * On utilise une classe nomée XMLEventReader. Ce n'est pas la façon la plus facile de lire du XML, mais elle a
 * l'avantage de faire partie du JDK, donc il n'y a pas de dépendances à ajouter à votre projet. Elle fonctionne comme
 * un curseur dans le document: on lui demande le prochain évènement, qui peut être une ouverture de tag, des
 * caractères, etc.
 */
public final class ConditionsInitiales {
    private final XMLEventReader reader;

    public ConditionsInitiales(XMLEventReader reader) {
        this.reader = reader;
    }

    public Lac nextLac() throws ConditionsInitialesInvalides {
        // On cherche le début du document.
        while (true) {
            var event = this.nextEvent();
            if (event.isStartElement()) {
                var startElement = event.asStartElement();
                var name = startElement.getName().getLocalPart();
                if (!name.equals("lac")) {
                    throw new ConditionsInitialesInvalides(
                        "je m'attendais au tag \"lac\", mais j'ai eu \"" + name + "\"");
                }
                break;
            } else if (!event.isProcessingInstruction() && !event.isStartDocument()) {
                throw new ConditionsInitialesInvalides("élément invalide: " + event);
            }
        }

        Integer energieSolaire = null;
        List<Plante> plantes = new ArrayList<>();
        List<Herbivore> herbivores = new ArrayList<>();

        while (this.reader.hasNext()) {
            var event = this.nextEvent();
            if (event.isStartElement()) {
                var startElement = event.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "energieSolaire" -> {
                        energieSolaire = this.nextInt("energieSolaire");
                        this.skipEndTag();
                    }
                    case "plante" -> {
                        var quantityAttribute = startElement.getAttributeByName(new QName("quantite"));
                        var quantity = Integer.parseInt(quantityAttribute.getValue());
                        var usine = new UsinePlante();
                        this.nextPlante(usine);
                        for (int i = 0; i < quantity; i += 1) {
                            plantes.add(usine.creerPlante());
                        }
                    }
                    case "herbivore" -> {
                        var quantityAttribute = startElement.getAttributeByName(new QName("quantite"));
                        var quantity = Integer.parseInt(quantityAttribute.getValue());
                        var usine = new UsineHerbivore();
                        this.nextHerbivore(usine);
                        for (int i = 0; i < quantity; i += 1) {
                            herbivores.add(usine.creerHerbivore());
                        }
                    }
                }
            } else if (event.isEndElement()) {
                // C'est nécessairement le tag fermant </lac>, si le document est bien formé.
                break;
            }
        }

        if (energieSolaire == null) {
            throw new ConditionsInitialesInvalides("energieSolaire non spécifiée");
        }

        return new Lac(energieSolaire, plantes, herbivores);
    }

    private void nextPlante(UsinePlante usine) throws ConditionsInitialesInvalides {
        while (true) {
            var event = this.nextEventIgnoringWhitespace();
            if (event.isStartElement()) {
                var startElement = event.asStartElement();
                var name = startElement.getName().getLocalPart();
                switch (name) {
                    case "nomEspece" -> usine.setNomEspece(this.nextString("nomEspece"));
                    case "besoinEnergie" -> usine.setBesoinEnergie(this.nextDouble("besoinEnergie"));
                    case "efficaciteEnergie" -> usine.setEfficaciteEnergie(this.nextDouble("efficaciteEnergie"));
                    case "resilience" -> usine.setResilience(this.nextDouble("resilience"));
                    case "fertilite" -> usine.setFertilite(this.nextDouble("fertilite"));
                    case "ageFertilite" -> usine.setAgeFertilite(this.nextInt("ageFertilite"));
                    case "energieEnfant" -> usine.setEnergieEnfant(this.nextDouble("energieEnfant"));
                    default -> throw new ConditionsInitialesInvalides(
                        "attribut \"" + name + "\" invalide pour une plante");
                }
                this.skipEndTag();
            } else if (event.isEndElement()) {
                return;
            }
        }
    }

    private void nextHerbivore(UsineHerbivore usine) throws ConditionsInitialesInvalides {
        while (true) {
            var event = this.nextEventIgnoringWhitespace();
            if (event.isStartElement()) {
                var startElement = event.asStartElement();
                var name = startElement.getName().getLocalPart();
                switch (name) {
                    case "nomEspece" -> usine.setNomEspece(this.nextString("nomEspece"));
                    case "besoinEnergie" -> usine.setBesoinEnergie(this.nextDouble("besoinEnergie"));
                    case "efficaciteEnergie" -> usine.setEfficaciteEnergie(this.nextDouble("efficaciteEnergie"));
                    case "resilience" -> usine.setResilience(this.nextDouble("resilience"));
                    case "fertilite" -> usine.setFertilite(this.nextDouble("fertilite"));
                    case "ageFertilite" -> usine.setAgeFertilite(this.nextInt("ageFertilite"));
                    case "energieEnfant" -> usine.setEnergieEnfant(this.nextDouble("energieEnfant"));
                    case "debrouillardise" -> usine.setDebrouillardise(this.nextDouble("debrouillardise"));
                    case "voraciteMin" -> usine.setVoraciteMin(this.nextDouble("voraciteMin"));
                    case "voraciteMax" -> usine.setVoraciteMax(this.nextDouble("voraciteMax"));
                    case "aliments" -> usine.addAliment(this.nextString("aliments"));
                    default -> throw new ConditionsInitialesInvalides(
                        "attribut \"" + name + "\" invalide pour un herbivore");
                }
                this.skipEndTag();
            } else if (event.isEndElement()) {
                return;
            }
        }
    }

    private String nextString(String context) throws ConditionsInitialesInvalides {
        var characters = this.nextCharacters(context);
        return characters.getData().trim();
    }

    private int nextInt(String context) throws ConditionsInitialesInvalides {
        return Integer.parseInt(this.nextString(context));
    }

    private double nextDouble(String context) throws ConditionsInitialesInvalides {
        return Double.parseDouble(this.nextString(context));
    }

    private Characters nextCharacters(String context) throws ConditionsInitialesInvalides {
        var event = this.nextEvent();
        if (event.isCharacters()) {
            return event.asCharacters();
        } else {
            throw new ConditionsInitialesInvalides("je m'attendais à avoir des caractères dans " + context);
        }
    }

    private void skipEndTag() throws ConditionsInitialesInvalides {
        var event = this.nextEventIgnoringWhitespace();
        if (!event.isEndElement()) {
            throw new ConditionsInitialesInvalides(
                "je m'attendais à la fin d'un tag, mais j'ai eu des données: " + event);
        }
    }

    private XMLEvent nextEventIgnoringWhitespace() throws ConditionsInitialesInvalides {
        while (true) {
            var event = this.nextEvent();
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }
            return event;
        }
    }

    private XMLEvent nextEvent() throws ConditionsInitialesInvalides {
        try {
            return this.reader.nextEvent();
        } catch (XMLStreamException e) {
            throw new ConditionsInitialesInvalides("le document se termine avant la fin", e);
        }
    }
}
