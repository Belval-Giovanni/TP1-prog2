la premiere façon de gardé une trace de quel attribut ont été initialisé est de cree un tableau dont chaque position contient un boolean 
disant si oui ou non tel ou tel variable à été initialisé , ainsi en recupérant ce tableau via un getters on pourra savoir quelles
sont les attributs qui n'ont pas été initialisé.


La deuxieme façon de faire est de cree un tableau dans lequel chaque position contient un double indiquant la valeur de l'attribut après modification et 0 si l'attribut n'a pas été initialié (ou 0 sert aussi de valeur par défaut
).
 ainsi nous pourront recuperer ce tableau via un getter
pour pouvoir savoir quelles sont les attribut qui ont été initialisé ET leurs valeurs.
Seul le nom de la plante ne sera pas dans le tableau mais il suffira de voir si il s'agit d'une chaine vide , la aussi nous pouvons donné un nom par défaut.


j'ai personnellement choisi la deuxieme méthode car celle-ci me permet directement d'utilisé les valeurs dans le tableau pour 
instancié une plante , de plus si je vois qu'un élément du tableau est 0 (c'est a dire que cet attribut n'a pas été initialisé ) j'instance quand meme la plante car 0 est la valeur par défaut. Un autre avantage de cette méthode est que nous pouvons donné les valeur par défaut directement dans le tableau si nous souhaitons en modifié la valeur pour tel ou tel attribut.
