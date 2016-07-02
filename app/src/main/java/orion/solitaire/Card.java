package orion.solitaire;

import android.graphics.drawable.Drawable;

public class Card {
	private int value;
	private Suit suit;
    private Drawable d;
	private boolean faceUp;
	
	public Card(int value, Suit suit, Drawable d) {
		this.value = value;
		this.suit = suit;
        this.d = d;
		faceUp = false;
	}
	
	public int getValue() {
		return value;
	}
	
	public Suit getSuit() {
		return suit;
	}

    public Drawable getImage() {
        return d;
    }
	
	public boolean getFace() {
		return faceUp;
	}
	
	public void setFace(boolean face) {
		faceUp = face;
	}
}
