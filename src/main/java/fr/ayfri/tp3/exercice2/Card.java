package fr.ayfri.tp3.exercice2;

/**
 * Le record Card permettant de créer des cartes.
 *
 * @param number Le numéro de la carte.
 * @param type Le type de la carte.
 *
 * @author Pierre
 */
public record Card(int number, CardType type) {
	/**
	 * Le type de la carte.
	 *
	 * @author Pierre
	 */
	enum CardType {
		CLUBS,
		DIAMONDS,
		HEARTS,
		SPADES
	}
}
