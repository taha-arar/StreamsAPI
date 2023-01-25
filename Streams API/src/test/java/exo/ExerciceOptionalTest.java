package exo;


import bean.Artiste;
import bean.Person;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ExerciceOptionalTest {

    private IExerciceOptional exo;

    @Before
    public void setUp() throws Exception {
        exo = new ExerciceOptional();
    }

    @Test
    public void should_return_default_value_if_exists() throws Exception {
        Person saul = new Person("Saul", 18, "M");
        String maybeName = exo.getNameFirstArtisteInIpod(saul);
        assertThat(maybeName).isEqualTo("unknown");

        saul.dansMonIpod = Lists.emptyList();
        maybeName = exo.getNameFirstArtisteInIpod(saul);
        assertThat(maybeName).isEqualTo("unknown");

        saul.dansMonIpod = Lists.newArrayList(
                new Artiste(null, 15),
                new Artiste("Dr John", 20));
        maybeName = exo.getNameFirstArtisteInIpod(saul);
        assertThat(maybeName).isEqualTo("unknown");

        saul.dansMonIpod = Lists.newArrayList(
                new Artiste("Prince", 15),
                new Artiste("Dr John", 20));
        maybeName = exo.getNameFirstArtisteInIpod(saul);
        assertThat(maybeName).isEqualTo("Prince");

        maybeName = exo.getNameFirstArtisteInIpod(null);
        assertThat(maybeName).isEqualTo("unknown");
    }

    @Test
    public void should_return_name_of_chef_if_exists() throws Exception {
        Person saul = new Person("Saul", 18, "M");
        String maybeName = exo.getNameOfChef(saul);
        assertThat(maybeName).isEqualTo("Eric");

        saul.chef = Optional.empty();
        maybeName = exo.getNameOfChef(saul);
        assertThat(maybeName).isEqualTo("Eric");

        Person walter = new Person("Walter", 56, "M");
        saul.chef = Optional.of(walter);
        maybeName = exo.getNameOfChef(saul);
        assertThat(maybeName).isEqualTo("Walter");

        maybeName = exo.getNameOfChef(null);
        assertThat(maybeName).isEqualTo("Eric");

    }
}
