package exo;

import bean.Artiste;
import bean.Person;

import java.util.*;

/**
 * Interface pour les exercices sur les Streams Java 8.
 */
public interface IExerciceStream {
    /**
     * Ordonner par âge croissant et extraire le nom.
     *
     * @param persons Liste de personnes
     * @return Liste de noms
     */
    List<String> getNamesSortedByAge(List<Person> persons);

    /**
     * Ordonner par âge croissant et concaténer les noms pour affichage avec séparateur, préfixe et suffixe.
     *
     * @param persons Liste de personnes
     * @return "Du plus jeune au plus âgé: <Liste de noms séparés par une virgule>."
     */
    String displayNamesFromYoungestToOldest(List<Person> persons);

    /**
     * Faire une moyenne des âges.
     *
     * @param persons Liste de personnes
     * @return moyenne des âges
     */
    double averageAge(List<Person> persons);

    /**
     * Faire une moyenne des âges des hommes.
     *
     * @param persons Liste de personnes
     * @return moyenne des âges
     */
    double averageAgeMale(List<Person> persons);

    /**
     * Faire une moyenne des âges des personnes dont le nom commence par une lettre.
     *
     * @param persons Liste de personnes
     * @param letter  initiale
     * @return moyenne des âges
     */
    double averageAgeByInitial(List<Person> persons, String letter);

    /**
     * Faire une moyenne des âges en fonction du sexe.
     *
     * @param persons Liste de personnes
     * @return Map avec la moyenne d'âge en fonction du sexe
     */
    Map<String, Double> averageAgeBySex(List<Person> persons);

    /**
     * Retourner la liste, classée par ordre alphabétique, de personnes qui écoutent un artiste très populaire.
     *
     * @param personnes Liste de personnes
     * @return liste de personnes qui écoutent un artiste très populaire
     */
    default List<Person> getMainstreamMusicListeners(List<Person> personnes) {
        List<Person> listeners = new ArrayList<>();
        for (Person person : personnes) {
            boolean isBestSeller = false;
            for (Artiste a : person.dansMonIpod) {
                if (a.classement <= 10) {
                    isBestSeller = true;
                    break;
                }
            }
            if (isBestSeller)
                listeners.add(person);
        }
        Collections.sort(listeners, new Comparator<Person>() {
            public int compare(Person a1, Person a2) {
                return a1.nom.compareTo(a2.nom);
            }
        });
        return listeners;
    }

    /**
     * Retourner la liste de personnes qui écoutent un artiste très populaire.
     * Implémentation en Java 8
     *
     * @param persons Liste de personnes
     * @return liste de personnes qui écoutent un artiste très populaire
     */
    List<Person> getMainstreamMusicListenersJava8(List<Person> persons);
}
