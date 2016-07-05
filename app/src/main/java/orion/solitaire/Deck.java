package orion.solitaire;

import android.content.Context;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Represents a deck of Card objects
 */
public class Deck {
	private LinkedList<Card> deck;
	private LinkedList<Card> flipped;

    /**
     * Create a new deck of Card objects, representing a standard deck of cards, and shuffle the deck.
     * @param context Activity deck is being used in
     */
	public Deck(Context context) {
		deck = new LinkedList<>();
		flipped = new LinkedList<>();
		Card c;
        int id;
		int back = context.getResources().getIdentifier("back", "drawable", context.getPackageName());

		for (Suit s : Suit.values()) {
			for (int i = 1; i < 14; i++) {
                String image;
				switch (s) {
                    case SPADE:
                        image = "s" + i;
                        break;
                    case HEART:
                        image = "h" + i;
                        break;
                    case CLUB:
                        image = "c" + i;
                        break;
                    case DIAMOND:
                        image = "d" + i;
                        break;
                    default:
                        image = "empty";
                }

                id = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
				c = new Card(i, s, id, back);
				deck.add(c);
			}
		}
		
		Collections.shuffle(deck);
	}

    /**
     * Remove a Card from the top of the deck
     * @return Returns the top Card
     */
	public Card removeDeck() {
		return deck.pop();
	}

    /**
     * Draw three cards from the deck and place in the drawn pile, restore deck if empty
     */
	public void drawThree() {
		if (deck.size() == 0) {
			flipDeck();
		} else {
			Card c;
			int s = deck.size();
			
			for (int i = 0; i < 3 && i < s; i++) {
				c = deck.pop();
				c.setFaceUp(true);
				flipped.push(c);
			}
		}
	}

    /**
     * Remove a Card from the top of the drawn pile.
     * @return Returns the top drawn Card
     */
	public Card removeDrawn() {
		return flipped.pop();
	}

    /**
     * Flip all cards in deck
     */
	private void flipDeck() {
		deck.addAll(flipped);
		Collections.reverse(deck);
		flipped.clear();
		
		for (Card c : deck) {
			c.setFaceUp(false);
		}
	}
}
