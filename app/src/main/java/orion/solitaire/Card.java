package orion.solitaire;

/**
 * Represents a card using its value (1-13), suit, image id and orientation (face up or face down)
 */
public class Card {
	private int value;
	private int imgId;
	private int backId;
	private Suit suit;
	private boolean faceUp;

	/**
	 * Create a new card object
	 * @param value Value of card
	 * @param suit Suit of card
	 * @param imgId Id of card image
     */
	public Card(int value, Suit suit, int imgId, int backId) {
		this.value = value;
		this.suit = suit;
        this.imgId = imgId;
		this.backId = backId;
		faceUp = false;
	}

	/**
	 * Get the value of the card
	 * @return Returns the card's value
     */
	public int getValue() {
		return value;
	}

	/**
	 * Get the suit of the card
	 * @return Returns the card's suit enum
     */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Get the image id of the card
	 * @return Returns the card's image id
     */
    public int getImageId() {
		if (faceUp) {
			return imgId;
		} else {
			return backId;
		}
    }

	/**
	 * Check whether card is face up
	 * @return Returns true if card is face up, false otherwise
     */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * Set whether card is face up or down
	 * @param b Boolean expression of whether card is facing up. Pass true for card facing up, false otherwise
     */
	public void setFaceUp(boolean b) {
		faceUp = b;
	}
}
