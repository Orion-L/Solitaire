package orion.solitaire;

import android.content.Context;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	private LinkedList<Card> deck;
	private LinkedList<Card> flipped;
	
	public Deck(Context context) {
		deck = new LinkedList<>();
		flipped = new LinkedList<>();
		Card c;
        int id;
		int back = context.getResources().getIdentifier("back", "drawable", context.getPackageName());

		for (Suit s : Suit.values()) {
			for (int i = 1; i < 14; i++) {
				id = context.getResources().getIdentifier("c" + i, "drawable", context.getPackageName());
				c = new Card(i, s, id, back);
				deck.add(c);
			}
		}
		
		Collections.shuffle(deck);
	}
	
	public Card removeDeck() {
		return deck.pop();
	}
	
	public void drawThree() {
		if (deck.size() == 0) {
			flipDeck();
		} else {
			Card c;
			int s = deck.size();
			
			for (int i = 0; i < 3 && i < s; i++) {
				c = deck.pop();
				c.setFace(true);
				flipped.push(c);
			}
		}
	}
	
	public Card removeDrawn() {
		return flipped.pop();
	}
	
	@Override
	public String toString() {
		String s =  "Size = " + deck.size() + "\n\n";
		
		for (Card c : deck) {
			s += c.getSuit() + " " + c.getValue() + "\n";
		}
		
		return s;
	}
	
	private void flipDeck() {
		deck.addAll(flipped);
		Collections.reverse(deck);
		flipped.clear();
		
		for (Card c : deck) {
			c.setFace(false);
		}
	}
}
