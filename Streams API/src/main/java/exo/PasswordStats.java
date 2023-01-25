package exo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Statistiques sur les mots de passes. Solution.
 */
public class PasswordStats implements IPasswordStats {

    /**
     * N'est pas vide.
     */
    public Predicate<String> isNotBlank = s -> s != null && s.length() > 0;

    /**
     * Contient au moins une lettre capitale.
     */
    public Predicate<String> hasUppercase = s -> s.matches(".*[A-Z]+.*");

    /**
     * Contient au moins une lettre minuscule.
     */
    public Predicate<String> hasLowercase = s -> s.matches(".*[a-z]+.*");

    /**
     * Contient au moins un chiffre.
     */
    public Predicate<String> hasNumber = s -> s.matches(".*[0-9]+.*");

    /**
     * Contient au moins un caractère spécial.
     */
    public Predicate<String> hasSpecial = s -> s.matches(".*[ !\"#$%&'()*+,-\\./:;<=>?@\\[\\]^_`{|}~]+.*");

    /**
     * Est assez long (8).
     */
    public Predicate<String> isLongEnough = s -> s.length() >= 8;

    /**
     * N'est pas trop long (128).
     */
    public Predicate<String> isNotTooLong = s -> s.length() <= 128;

    /**
     * N'a pas plus 2 caractères identiques à la suite.
     */
    public Predicate<String> hasNoRepetition = s -> !s.matches(".*(.)\\1\\1+.*");

    /**
     * Est un mot de passe costaud.
     * TODO : Composer les prédicats.
     */
    public Predicate<String> isStrongPassword =
            //TODO
            isNotBlank;

    /**
     * Est un mot de passe costaud.
     * @param password Mot de passe à tester
     * @return true si mot de passe fort
     */
    @Override
    public boolean isStrongPassword(String password) {
        return isStrongPassword.test(password);
    }

    /**
     * Retourne tous les mots de passe ayant au moins un chiffre.
     * Indices : Récupérer le flux du Supplier et filtrer
     *
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    @Override
    public List<String> getAllWithNumbers(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new ArrayList<>();
    }

    /**
     * Retourne tous les mots de passe ayant au moins une lettre capitale et une minuscule.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    @Override
    public List<String> getAllWithUppercaseAndLowercase(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new ArrayList<>();
    }

    /**
     * Retourne tous les mots de passe ayant au moins un caractère spécial.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    @Override
    public List<String> getAllWithSpecialChars(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new ArrayList<>();
    }

    /**
     * Retourne tous les mots de passe forts.
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    @Override
    public List<String> getAllStrong(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new ArrayList<>();
    }

    /**
     * Compte les mots de passe en fonction de la position des caractères spéciaux dans le mot.
     * Ex: pour "b1op!", "#bli!", le résultat sera [(0, 1), (4, 2)]
     * Indices si vous êtes bloqué : voir le readme ou le wiki.
     *
     * @param allPasswords Stream de mots de passe
     * @return Map<Position du char, compte>
     */
    @Override
    public Map<Integer, Long> countBySpecialCharPosition(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new HashMap<>();
    }

    /**
     * Renvoie la liste des mots de passe avec caractère spécial en fonction des positions des caractères spéciaux.
     * Ex: pour "b1op!", "#bli!", le résultat sera [(0, ["#bli!"]), (4, ["blop!, "#bli!])]
     * Indices si vous êtes bloqué : voir le readme ou le wiki.
     *
     * @param allPasswords Stream de mots de passe
     * @return Map<Position du char, Liste des mots de passe.>
     */
    @Override
    public Map<Integer, List<String>> getAllBySpecialCharPosition(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new HashMap<>();
    }

    /**
     * Renvoie la liste des mots de passe avec un seul caractère spécial à la fin.
     * Indices si vous êtes bloqué:
     * filter directement en vérifiant qu'il n'y a qu'un caractère spécial
     * et que sa position est à la fin du mot de passe.
     *
     * @param allPasswords Stream de mots de passe
     * @return tous ces mots de passe
     */
    @Override
    public List<String> getAllWithOnlyOneLastSpecialChar(Supplier<Stream<String>> allPasswords) {
        //TODO
        return new ArrayList<>();
    }
}
