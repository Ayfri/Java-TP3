package fr.ayfri.tp3.exercice2;

public record Card(int number, CardType type) {
	enum CardType {
		CLUBS,
		DIAMONDS,
		HEARTS,
		SPADES
	}
}
