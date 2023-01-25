package exo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Statistiques sur les mots de passes.
 * Exercice avancé sur les Streams Java 8.
 */
public interface IPasswordStats {
    /**
     * Pattern pour les caractères spéciaux.
     */
    Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[\\\\ !\"#$%&'()*+,-\\./:;<=>?@\\[\\]^_`{|}~]");

    /**
     * Est un mot de passe costaud.
     * @param password Mot de passe à tester
     * @return true si mot de passe fort
     */
    boolean isStrongPassword(String password);

    /**
     * Retourne tous les mots de passe ayant au moins une lettre capitale et une minuscule.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    List<String> getAllWithUppercaseAndLowercase(Supplier<Stream<String>> allPasswords);

    /**
     * Retourne tous les mots de passe ayant au moins un chiffre.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    List<String> getAllWithNumbers(Supplier<Stream<String>> allPasswords);

    /**
     * Retourne tous les mots de passe ayant au moins un caractère spécial.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    List<String> getAllWithSpecialChars(Supplier<Stream<String>> allPasswords);

    /**
     * Retourne tous les mots de passe forts.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    List<String> getAllStrong(Supplier<Stream<String>> allPasswords);

    /**
     * Compte les mots de passe en fonction de leur position dans le mot.
     * @param allPasswords Stream de mots de passe
     * @return Map<Position du char, compte>
     */
    Map<Integer, Long> countBySpecialCharPosition(Supplier<Stream<String>> allPasswords);

    /**
     * Renvoie la liste des mots de passe avec caractère spécial en fonction de la position du caractère spécial.
     * @param allPasswords Stream de mots de passe
     * @return Map<Position du char, Liste des mots de passe.>
     */
    Map<Integer, List<String>> getAllBySpecialCharPosition(Supplier<Stream<String>> allPasswords);

    /**
     * Renvoie la liste des mots de passe avec un seul caractère spécial à la fin.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    List<String> getAllWithOnlyOneLastSpecialChar(Supplier<Stream<String>> allPasswords);

    /**
     * Lecture d'un fichier en ressource.
     *
     * @param filename nom du fichier en ressource
     * @return Stream ligne par ligne.
     */
    default Stream<String> readResourceAsStream(final String filename) {
        Stream<String> stream;
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            stream = Files.lines(path, Charset.defaultCharset());
        } catch (NullPointerException | URISyntaxException | IOException e) {
            throw new IllegalArgumentException("Ressource inexistante : " + filename);
        }
        return stream;
    }

    /**
     * Renvoie les positions des caractères spéciaux de la String.
     * @param testString String à tester
     * @return liste de positions (vide s'il n'y a pas de caratère spécial)
     */
    default List<Integer> getIndexOfSpecialChar(final String testString) {
        Matcher matcher = SPECIAL_CHAR_PATTERN.matcher(testString);
        List<Integer> result = new ArrayList<>();
        while(matcher.find()) {
            result.add(matcher.start());
        }
        return result;
    }

    /**
     * Affichage des stats dans la console.
     * @param filename nom du fichier à analyser
     */
    default void printStats(final String filename) {
        long tStart = System.currentTimeMillis();
        Supplier<Stream<String>> allPasswords = () -> this.readResourceAsStream(filename);
        long countAllStrong = this.getAllStrong(allPasswords).size();
        long countAllWithSpecialChars = this.getAllWithSpecialChars(allPasswords).size();
        long countAllWithNumbers = this.getAllWithNumbers(allPasswords).size();
        long countAllWithUppercaseAndLowercase = this.getAllWithUppercaseAndLowercase(allPasswords).size();
        long countAllWithOnlyOneLastSpecialChar = this.getAllWithOnlyOneLastSpecialChar(allPasswords).size();

        System.out.println("Number of strong passwords : " + countAllStrong);
        System.out.println("Number of passwords with at least 1 special char : " + countAllWithSpecialChars);
        System.out.println("Number of passwords with at least 1 number : " + countAllWithNumbers);
        System.out.println("Number of passwords mixing upper and lower case  : " + countAllWithUppercaseAndLowercase);
        System.out.println("Count of passwords by special char position : ");
        this.countBySpecialCharPosition(allPasswords).entrySet().stream()
                .forEach(e -> System.out.println(" - Position " + e.getKey() + " : " + e.getValue()));

        System.out.println("\nCount of passwords with only one special char at the end : " + countAllWithOnlyOneLastSpecialChar);
        long tEnd = System.currentTimeMillis();
        double elapsedSeconds = (tEnd - tStart) / 1000.0;
        System.out.println("Total time : " + elapsedSeconds + 's');
    }
}
