package orion.solitaire;

import android.graphics.drawable.Drawable;

public class Card {
	private int value;
	private int imgId;
    private int backId;
	private Suit suit;
	private boolean faceUp;
	
	public Card(int value, Suit suit, int imgId, int backId) {
		this.value = value;
		this.suit = suit;
        this.imgId = imgId;
        this.backId = backId;
		faceUp = false;
	}
	
	public int getValue() {
		return value;
	}
	
	public Suit getSuit() {
		return suit;
	}

    public int getImageId() {
        if (faceUp) {
            return imgId;
        } else {
            return backId;
        }
    }
	
	public boolean getFace() {
		return faceUp;
	}
	
	public void setFace(boolean face) {
		faceUp = face;
	}
}
