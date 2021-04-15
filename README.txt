
BELVAL GIOVANNI
YANN SAAH

Description : 

Ce dossier contient un ensmble de de classe , dont la principale est la Classe Simulation.
cet ensemble de classe a pour but de mettre a disposition un simulateur d'ecosysteme , et plus précisement de l'ecosysteme
d'un lac dont la seul interaction avec l'extérieur serait l'energie ressus par rayonnement solaire.
il y a trois tyes d'organisme qui interagisse (directement ou indirectement dan ce lac ) :
les plantes , les herbivores et les carnivores ; et c'est principalement leurs interaction qui seront au coeur de nos ligne de code.




I/ Les plantes

la premiere façon de gardé une trace de quel attribut ont été initialisé est de cree un tableau dont chaque position contient un boolean 
disant si oui ou non tel ou tel variable à été initialisé , ainsi en recupérant ce tableau via un getters on pourra savoir quelles
sont les attributs qui n'ont pas été initialisé.


La deuxieme façon de faire est de cree un tableau dans lequel chaque position contient un double indiquant la valeur de l'attribut après modification et 0 si l'attribut n'a pas été initialié (ou 0 sert aussi de valeur par défaut
).
 ainsi nous pourront recuperer ce tableau via un getter
pour pouvoir savoir quelles sont les attribut qui ont été initialisé ET leurs valeurs.
Seul le nom de la plante ne sera pas dans le tableau mais il suffira de voir si il s'agit d'une chaine vide , la aussi nous pouvons donné un nom par défaut.


j'ai personnellement choisi la deuxieme méthode car celle-ci me permet directement d'utilisé les valeurs dans le tableau pour 
instancié une plante , de plus si je vois qu'un élément du tableau est 0 (c'est a dire que cet attribut n'a pas été initialisé )
j'instancie quand meme la plante car 0 est la valeur par défaut. Un autre avantage de cette méthode est que nous pouvons donné les valeur par défaut directement dans le tableau si nous souhaitons en modifié la valeur pour tel ou tel attribut.



en ce qui concerne la l'implementation de la fonction tick():
j'ai du rajouter une methode dans ma classe Plante , me permettant de recuperer toute les informations numerique 
d'une instance dans un tableau , pour pouvoir instancier une plnate fille ayant les memes caractéristique que la mère.
la Liste plantes dans la class lac a du etre modifiée , en effet pour pouvoir supprimé les plantes mortes et rajouter les plantes
qui viennes de nettre il fallait pouvoir modifier les differants élement de la liste plantes, l'attribut final lui a 
donc été retiré


II/ les herbivores

pour ce qui concerne les classe Plante , Herbivores , ainsi que les les usines , j'ai opté pour la creation de classe abstraite.
cela m'a semblé plus adéquoit car dans les deux cas il y a enormément d'attribut en commun.j'ai donc cree unr classe abstraite
Organisme et une classe abstraite Usine qui sont resppectivement parent de Plante , Herbivore , et des deux classe usine.
en implemantant une classe abstraite pour les usines je peux factorisé beaucoup de code , et il ne me reste qu'a definir les methodes
de creation d'instance (ce qui prend peu de ligne ) et le rajout de quelque setters (seulement dans l'usine herbivore).



III/ Les carnivores:


en ce qui concerne les classe Carnvore et usineCarnivore j'ai réeutilisé du code , principalement celui de la classe herbivore.
ce choix etais preferable pour permettre une meilleure cohésion dans la comprehension du code par un autre Utilisateur , car 
tres bien pu declaré voraciteMin et voraciteMax en private puis faire hérité la Class Carnivore de la classe herbivore. mais pour un 
potentiel futur utilisateur ça n'aurrai pas necesserait été logique étant donné que ces deux class sotn utilisé differement et qu'on
a du mal a se dire qu'un herbivore hérite d'un carnivore.
pour economisé du code j'aurrai pu faire l'invere , faire hérité Herbivore a Carnivore et ensuite rajouté les attribut de voracité;
je ne l'ai pas fait en revanche car j'avais deja ecris le code pour la classe herbivore et ça m'aurrai demandé un temps identique (et un plus grand
risques d'erreur de supprimé la classe pour la recree) sans parler du probleme de cohésion dont on a parler precedement.

dans les conditions initiales j'ai réutilisé du code , exatement le meme que pour les herbivores , mais sans les attribut voracité 
.



