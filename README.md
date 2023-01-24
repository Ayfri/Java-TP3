# Java-TP3

My third set of exercices to do for school.

## Comment lancer

Pour lancer le programme principal, il faut se placer dans le dossier du projet et lancer `./gradlew run`.
Java 19 est requis.
Toutes les dépendances seront automatiquement téléchargées et installées.

**Attention :**<br>
La commande `./gradlew yugiohapi:run` est à lancer en premier lieu pour télécharger un JSON contenant quelques centaines de cartes YuGiOh
pour l'exercice 3.
Cela permet d'éviter de le re-télécharger à chaque lancement du programme.

**Note :**<br>
Les images des cartes seront téléchargées dans le dossier `/src/main/resources/cards` pour éviter de les re-télécharger à chaque lancement
du programme.
Après plusieurs dizaines de cartes affichées, le dossier peut prendre plusieurs MB de stockage.

Internet est donc requis pour lancer l'exercice YuGiOh.
