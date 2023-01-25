Ce projet contient des tutoriels pour se faire la main sur les nouvelles API de Java 8.

## Pré-requis :
* Maven installé sur son poste (désolé, c'était le plus simple pour gérer les dépendances) et configuré sur IntelliJ

## Exercices

### Ressources à lire sur les streams :
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * http://soat.developpez.com/tutoriels/java/projet-lambda-java8/
 * https://www.techempower.com/blog/2013/03/26/everything-about-java-8/
 * http://docs.oracle.com/javase/tutorial/collections/streams/reduction.html

### Ressources à lire sur Optional :
 * http://vanillajava.blogspot.fr/2015/01/java-8-optional-is-not-just-for.html
 * http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html

### Instructions Optional
 * Implémenter la classe exo.ExerciceOptional pour que les tests ExerciceOptionalTest passent tous.
 * Essayer d'écrire l'équivalent sans Optional.

### Instructions API Streams
Exercice 1 :
 * Implémenter la classe exo.ExerciceStream pour que les tests ExerciceStreamTest passent tous.
 * Réécrire l'implementation en Java 8 de la méthode IExerciceStream#getMainstreamMusicListeners()

Exercice 2 (Attention niveau confirmé) :
 * Implémenter la classe exo.PasswordStats pour que les tests PasswordsStatsTest passent tous









## Indices

### countBySpecialCharPosition
> 1. Filtrer
> 2. Transformer (map) en Liste de positions du caractère spécial (avec exo.IPasswordStats#getIndexOfSpecialChar(java.lang.String))
> 3. Ecraser la List<List<Integer>> en Liste<Integer> (flatMap(i -> i.stream()))
> 4. Créer une Map en regroupant par Position et compter les occurrences. Le lambda x -> x est l'équivalent de Function.identity()


### getAllBySpecialCharPosition
> 1. Filtrer
> 2. Pour chaque password, récupérer les positions des caractères spéciaux,
> 3. Parcourir ces positions et construire une paire (Position, Password) avec new AbstractMap.SimpleEntry<>(K, V)
> 4. Ecraser la collection Liste de Liste de paires en liste de paires
> 5. Grouper avec clé en construisant une liste des passwords correspondants avec grouping, mapping et toList()

