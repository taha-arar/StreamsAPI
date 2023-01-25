package exo;

import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Test pour l'implémentation des statistiques de mots de passe.
 * Exercice Stream avancé.
 */
public class PasswordStatsTest {

    private IPasswordStats pstats;
    private Stream<String> stream;

    @Before
    public void setUp() throws Exception {
        pstats = new PasswordStats();
    }

    @After
    public void tearDown() throws Exception {
        if (stream != null) {
            stream.close();
        }
    }

    /**
     * Lecture du fichier et récupération du stream pour le fermer à la fin.
     * @return Suplier du Stream du fichier.
     */
    private Supplier<Stream<String>> readFile() {
        return () -> {
            stream = pstats.readResourceAsStream("leaked_passwords.txt");
                    //.onClose(() -> System.out.println("Close"));
            return stream;
        }
        ;
    }

    /**
     * Tests de lecture de fichier de resources.
     */
    @Test
    public void should_read_resources_file() {
        Stream<String> lines = pstats.readResourceAsStream("10k_most_common.txt");
        long nbLines = lines.count();
        assertThat(nbLines).isGreaterThan(1000);

        try {
            pstats.readResourceAsStream("unknown.txt");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith("Ressource inexistante :");
        }
    }

    @Test
    public void should_test_strong_passwords() {
        // Cas Valides
        assertThat(pstats.isStrongPassword("Ampo 783::")).isTrue();
        assertThat(pstats.isStrongPassword("Apo 783:?")).isTrue();

        // Cas invalides
        assertThat(pstats.isStrongPassword("Apo 666:?")).isFalse();
        assertThat(pstats.isStrongPassword("Apo14:?")).isFalse();
        assertThat(pstats.isStrongPassword("BLAH : Instruction de sécurité n°1, Systèmes d'Information – BLAH : Instruction de sécurité n°1, Systèmes d'Information – BLAH : Instruction de sécurité n°1, Systèmes d'Information – ")).isFalse();
        assertThat(pstats.isStrongPassword("Totototo1")).isFalse();
        assertThat(pstats.isStrongPassword("Totototoi!")).isFalse();
        assertThat(pstats.isStrongPassword("totototoi1%")).isFalse();
    }

    @Test
    public void should_get_the_index_of_special_char() {
        assertThat(pstats.getIndexOfSpecialChar("close-up")).isEqualTo(Lists.newArrayList(5));
        assertThat(pstats.getIndexOfSpecialChar("closeup!")).isEqualTo(Lists.newArrayList(7));
        assertThat(pstats.getIndexOfSpecialChar("clo-se-up")).isEqualTo(Lists.newArrayList(3,6));
    }

    @Test
    public void count_simple_stats() {
//        Supplier<Stream<String>> allPasswords = () -> pstats.readResourceAsStream("10k_most_common.txt");
        Supplier<Stream<String>> allPasswords = readFile();
        long countAllStrong = pstats.getAllStrong(allPasswords).size();
        long countAllWithSpecialChars = pstats.getAllWithSpecialChars(allPasswords).size();
        long countAllWithNumbers = pstats.getAllWithNumbers(allPasswords).size();
        long countAllWithUppercaseAndLowercase = pstats.getAllWithUppercaseAndLowercase(allPasswords).size();
        assertThat(countAllStrong).isEqualTo(0);
        assertThat(countAllWithSpecialChars).isEqualTo(804);
        assertThat(countAllWithNumbers).isEqualTo(14945);
        assertThat(countAllWithUppercaseAndLowercase).isEqualTo(13457);
    }



    @Test
    public void should_count_all_passwords_with_special_chars_by_position() {
        Supplier<Stream<String>> allPasswords = readFile();
        Map<Integer, Long> specialCharsCountMap = pstats.countBySpecialCharPosition(allPasswords);
        assertThat(specialCharsCountMap.get(3)).isEqualTo(102);
        assertThat(specialCharsCountMap.get(8)).isEqualTo(108);
        assertThat(specialCharsCountMap.get(11)).isEqualTo(28);
        assertThat(specialCharsCountMap.get(20)).isEqualTo(1);
    }

    @Test
    public void should_get_all_passwords_with_special_chars_by_position() throws Exception {
        Supplier<Stream<String>> allPasswords = readFile();
        Map<Integer, List<String>> posSpecialCharPwd = pstats.getAllBySpecialCharPosition(allPasswords);
        assertThat(posSpecialCharPwd).hasSize(18);
        assertThat(posSpecialCharPwd.get(15))
                .contains("Catchmeifyoucan!", "Whenwillthisend?", "Professorpeanut!", "Mybeautifulkids!");
    }

    @Test
    public void should_get_all_passwords_with_only_one_sp_char_at_end() throws Exception {
        Supplier<Stream<String>> allPasswords = readFile();
        List<String> pwdWithOnlyOneLastSpecialChar = pstats.getAllWithOnlyOneLastSpecialChar(allPasswords);
        assertThat(pwdWithOnlyOneLastSpecialChar).hasSize(295)
            .contains("Mybeth!", "Mywholefamily!", "Comeonbaby!");
    }

    @Test
    public void should_print_stats() {
        pstats.printStats("leaked_passwords.txt");
    }
}
