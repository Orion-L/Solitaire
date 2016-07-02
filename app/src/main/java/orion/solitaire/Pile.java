package orion.solitaire;

import java.util.LinkedList;

public class Pile {
	private LinkedList<Card> pile;
	
	public Pile() {
		pile = new LinkedList<Card>();
	}
	
	public void addCard(Card c) {
		pile.push(c);
	}
	
	public void removeCard() {
		pile.pop();
	}
	
	public Card getTop() {
		return pile.getFirst();
	}
	
	@Override
	public String toString() {
		String s = "Size = " + pile.size() + "\n\n";
		
		for (Card c : pile) {
			s += c.getSuit() + " " + c.getValue() + "\n";
		}
		
		return s;
	}
}
