package PlayingCards;

/**
 * Represents a card using its value (1-13), suit, image id and orientation (face up or face down).
 */
public class Card {
	private int value;
	private Suit suit;
	private boolean faceUp;

	/**
	 * Create a new card object.
	 * @param value Value of card
	 * @param suit Suit of card
     */
	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
		faceUp = false;
	}

	/**
	 * Get the value of the card.
	 * @return Returns the card's value
     */
	public int getValue() {
		return value;
	}

	/**
	 * Get the suit of the card.
	 * @return Returns the card's suit enum
     */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Check whether card is face up.
	 * @return Returns true if card is face up, false otherwise
     */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * Set whether card is face up or down.
	 * @param b Boolean expression of whether card is facing up. Pass true for card facing up, false otherwise
     */
	public void setFaceUp(boolean b) {
		faceUp = b;
	}
}
