package exo;

import bean.Artiste;
import bean.Person;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Exercices sur Optional de Java 8.
 */
public class ExerciceOptional implements IExerciceOptional {


    /**
     * Retourne le nom du premier artiste de la liste ipod de la personne s'il existe.
     * Indices : Utiliser map et filter.
     *
     * @param person personne
     * @return nom s'il existe, "unknow" sinon.
     */


    @Override
    public String getNameFirstArtisteInIpod(Person person) {

        if (person!= null)
            if (person.dansMonIpod != null)
                return person.dansMonIpod.stream().findFirst()
                        .filter(artiste -> artiste.nom != null)
                        .map(artiste -> artiste.nom)
                        .orElse("unknown");
        return "unknown";

    }

    /**
     * Retourne le nom du chef s'il existe.
     * Indices : Utiliser map et flatMap.
     *
     * @param person le subordonné
     * @return nom du chef s'il existe, Eric sinon
     */
    @Override
    public String getNameOfChef(Person person) {
        String name="Eric";
        if(person != null){
            if(person.chef.isPresent()){
                  name=  person.chef
                            .map(person1 -> person1.nom)
                            .orElse("Eric");


                }

        }


        return name;
    }

}
