package bean;

import java.util.List;
import java.util.Optional;

/**
 * Bean pour un super bonhomme.
 */
public class Person {

    /**
     * Age du capitaine.
     */
    private int age;

    /**
     * Nom public.
     */
    public String nom;

    /**
     * M : Homme, F : femme ou autre.
     *
     */
    public String sexe;

    /**
     * Liste d'artistes dans l'iPod de la personne.
     */
    public List<Artiste> dansMonIpod;

    /**
     * Responsable de la personne.
     */
    public Optional<Person> chef = Optional.empty();

    /**
     * Constructeur.
     * @param nom nom usuel
     * @param age age
     * @param sexe M ou F ?
     */
    public Person(String nom, int age, String sexe) {
        this.age = age;
        this.nom = nom;
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
