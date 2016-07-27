package PlayingCards;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Represents a deck of Card objects.
 */
public class Deck {
	private LinkedList<Card> deck;

    /**
     * Create a new empty deck.
     */
	public Deck() {
		deck = new LinkedList<>();
	}

    /**
     * Initialise a deck of 52 cards in random order.
     */
	public void init() {
        deck.clear();
        Card c;

        for (Suit s : Suit.values()) {
            for (int i = 1; i < 14; i++) {
                c = new Card(i, s);
                deck.add(c);
            }
        }

        Collections.shuffle(deck);
    }

    /**
     * Add a Card to the top of the deck.
     * @param c Card to be added to deck
     */
    public void add(Card c) {
        deck.push(c);
    }

    /**
     * Remove a Card from the top of the deck.
     * @return Returns the top Card
     */
	public Card draw() {
		return deck.pop();
	}

    /**
     * Get the number of cards in the deck.
     * @return Returns the size of the deck
     */
    public int getSize() {
        return deck.size();
    }

    public Deck copy() {
        Card temp;
        Deck newDeck = new Deck();

        for (Card c : deck) {
            temp = new Card(c.getValue(), c.getSuit());
            newDeck.add(temp);
        }

        return newDeck;
    }
}
