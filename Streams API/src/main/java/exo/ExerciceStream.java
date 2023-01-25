package exo;

import bean.Artiste;
import bean.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Exercices sur les Streams Java 8.
 *
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * http://soat.developpez.com/tutoriels/java/projet-lambda-java8/
 * https://www.techempower.com/blog/2013/03/26/everything-about-java-8/
 * http://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 */
public class ExerciceStream implements IExerciceStream {

    /**
     * Ordonner par âge croissant et extraire le nom.
     * Indices : sorted() et Comparator.???
     *
     * @param persons Liste de personnes
     * @return Liste de noms
     */
    @Override
    public List<String> getNamesSortedByAge(final List<Person> persons) {

        return persons
                .stream()
                .sorted(Comparator.comparingInt(Person::getAge)).map(person -> person.nom).collect(Collectors.toList());
    }

    /**
     * Ordonner par âge croissant et concaténer les noms pour affichage avec séparateur,
     * préfixe et suffixe.
     * Indices : Collectors.joining()
     *
     * @param persons Liste de personnes
     * @return "Du plus jeune au plus âgé: <Liste de noms séparés par une virgule>".
     */
    @Override
    public String displayNamesFromYoungestToOldest(final List<Person> persons) {
           String names= "Du plus jeune au plus âgé:"+persons
                    .stream()
                    .sorted((o1, o2) -> o1.getAge()> o2.getAge() ? 1 : -1)
                    .map(person -> person.nom)
                    .collect(Collectors.joining(", "," ","."));
        return names;
    }

    /**
     * Faire une moyenne des âges.
     * @param persons Liste de personnes
     * @return moyenne des âges
     */
    @Override
    public double averageAge(final List<Person> persons) {

       return persons
               .stream()
               .mapToDouble(person -> person.getAge())
               .average().orElse(0.0);



    }

    /**
     * Faire une moyenne des âges des hommes.
     * @param persons Liste de personnes
     * @return moyenne des âges
     */
    @Override
    public double averageAgeMale(final List<Person> persons) {
        return persons.stream()
                .filter(person -> person.sexe.equals("M"))
                .mapToDouble(Person::getAge).average().orElse(0.0);

    }

    /**
     * Faire une moyenne des âges des personnes dont le nom commence par une lettre.
     * @param persons Liste de personnes
     * @param letter initiale
     * @return moyenne des âges
     */
    @Override
    public double averageAgeByInitial(final List<Person> persons, final String letter) {

       return  persons
                .stream()
                .filter(person -> person.nom.startsWith(letter))
                .mapToDouble(Person::getAge)
                .average().orElse(0.0);

    }

    /**
     * Faire une moyenne des âges en fonction du sexe.
     * Indices : Collectors.groupingBy, Collector.averagingInt
     * @param persons Liste de personnes
     * @return Map avec la moyenne d'âge en fonction du sexe
     */
    @Override
    public Map<String, Double> averageAgeBySex(final List<Person> persons) {

      return   persons
                .stream()
                .collect(Collectors.groupingBy(person -> person.sexe,Collectors.toList()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .collect(Collectors.averagingInt(Person::getAge))));

    }


    /**
     * Retourner la liste, classée par ordre alphabétique, de personnes qui écoutent un artiste très populaire.
     * Implémentation en Java 8 de la méthode IExercicesStream#getMainstreamMusicListeners()
     * @param persons Liste de personnes
     * @return liste de personnes qui écoutent un artiste très populaire
     */
    @Override
    public List<Person> getMainstreamMusicListenersJava8(final List<Person> persons){
       return  persons
                .stream()
                .sorted()
                .filter(artiste -> artiste.dansMonIpod.stream().filter(artiste1 -> artiste1.classement==1).isParallel()

                        ).collect(Collectors.toList());


    }


}
