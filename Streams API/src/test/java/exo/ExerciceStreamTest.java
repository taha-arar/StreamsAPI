package exo;

import bean.Artiste;
import bean.Person;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;


/**
 * Partie 1 : API Fonctionnelle.
 *
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * http://soat.developpez.com/tutoriels/java/projet-lambda-java8/
 * https://www.techempower.com/blog/2013/03/26/everything-about-java-8/
 * http://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 */

public class ExerciceStreamTest {

    private List<Person> myHeroes;

    private IExerciceStream exo;

    @Before
    public void setUp() throws Exception {
        exo = new ExerciceStream();
        myHeroes = Lists.newArrayList(
                new Person("Batman", 35, "M"),
                new Person("Daredevil", 28, "M"),
                new Person("Spiderman", 18, "M"),
                new Person("SuperDupont", 51, "M"),
                new Person("Catwoman", 22, "F")
        );

    }

    /**
     * Tri, transformation et concaténation.
     */
    @Test
    public void validate_ex_1() {
        // ordonner par âge croissant et extraire le nom
        List<String> heroesNamesSortedByAge = exo.getNamesSortedByAge(myHeroes);
        assertThat(heroesNamesSortedByAge).containsSequence("Spiderman", "Catwoman", "Daredevil", "Batman", "SuperDupont");
        assertThat(exo.getNamesSortedByAge(Lists.emptyList())).isEmpty();

        // Concaténer cette liste pour affichage avec un séparateur.
        String prettyString = exo.displayNamesFromYoungestToOldest(myHeroes);
        assertThat(prettyString).isEqualTo("Du plus jeune au plus âgé: Spiderman, Catwoman, Daredevil, Batman, SuperDupont.");
    }

    /**
     * Moyenne, filtres et collecte.
     */
    @Test
    public void validate_ex_2() {
        SoftAssertions softly = new SoftAssertions();
        // faire une moyenne des âges
        double moyenneAge = exo.averageAge(myHeroes);
        softly.assertThat(moyenneAge).isEqualTo(30.8);
        softly.assertThat(exo.averageAge(Lists.emptyList())).isEqualTo(0);

        // faire une moyenne des âges des hommes
        double moyenneAgeHomme = exo.averageAgeMale(myHeroes);
        softly.assertThat(moyenneAgeHomme).isEqualTo(33.0);
        softly.assertThat(exo.averageAgeMale(Lists.emptyList())).isEqualTo(0);

        // faire une moyenne des âges des Héros commençant par 'S'
        double moyenneAgeCommencantParS = exo.averageAgeByInitial(myHeroes, "S");
        softly.assertThat(moyenneAgeCommencantParS).isEqualTo(34.5);
        softly.assertThat(exo.averageAgeByInitial(Lists.emptyList(), "S")).isEqualTo(0);

        // faire une moyenne des âges des hommes et des femmes
        Map<String, Double> mapAverageAgeBySex = exo.averageAgeBySex(myHeroes);
        softly.assertThat(mapAverageAgeBySex).contains(
                entry("F", 22.0),
                entry("M", 33.0));
        softly.assertThat(exo.averageAgeBySex(Lists.emptyList())).isEmpty();

        softly.assertAll();
    }

    /**
     * Réécriture d'un traitement en Java 8.
     */
    @Test
    public void check_new_impl_getMainstreamMusicListeners() {
        Person p1 = new Person("Kevin", 26, "M");
        p1.dansMonIpod = Lists.newArrayList(
                new Artiste("M. Pokora", 2),
                new Artiste("Lara Fabian", 7),
                new Artiste("Idina Menzel", 1)
        );

        Person p2 = new Person("Natacha", 27, "F");
        p2.dansMonIpod = Lists.newArrayList(
                new Artiste("La Boulangerie", 42),
                new Artiste("La fine équipe", 187),
                new Artiste("SBTRKT", 12)
        );

        Person p3 = new Person("Christophe", 36, "M");
        p3.dansMonIpod = Lists.newArrayList(
                new Artiste("Fist of the Northern Star", 56),
                new Artiste("Mylène Farmer", 1),
                new Artiste("ADX", 189)
        );

        Person p4 = new Person("Simone", 28, "F");
        p4.dansMonIpod = Lists.newArrayList(
                new Artiste("Simone, elle est bonne", 99),
                new Artiste("Salut, c'est cool", 89),
                new Artiste("Archive", 34)
        );

        Person p5 = new Person("Eric", 42, "M");
        p5.dansMonIpod = Lists.newArrayList(
                new Artiste("Juda's Priest", 77),
                new Artiste("Trust",35)
        );
        Person p6 = new Person("Lang", 33, "M");
        p6.dansMonIpod = Lists.newArrayList(
                new Artiste("dEUS", 13),
                new Artiste("Dr John", 56),
                new Artiste("Jamie Lidell", 45)
        );

        List<Person> personnes = Lists.newArrayList(p1, p2, p3, p4, p5, p6);
        List<Person> oldResult = exo.getMainstreamMusicListeners(personnes);
        List<Person> newResult = exo.getMainstreamMusicListenersJava8(personnes);
        assertThat(newResult).isEqualTo(oldResult);
    }

}
