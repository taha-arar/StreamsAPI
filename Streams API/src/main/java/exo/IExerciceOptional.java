package exo;

import bean.Person;

/**
 * User: lh712825
 */
public interface IExerciceOptional {
    /**
     * Retourne le nom du premier artiste de la liste ipod de la personne s'il existe.
     * @param person personne
     * @return nom s'il existe, "unknow" sinon.
     */
    String getNameFirstArtisteInIpod(Person person);

    /**
     * Retourne le nom du chef s'il existe.
     * @param person le subordonné
     * @return nom du chef s'il existe, Eric sinon
     */
    String getNameOfChef(Person person);
}
