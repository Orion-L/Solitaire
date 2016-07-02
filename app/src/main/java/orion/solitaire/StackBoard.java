package orion.solitaire;

public class StackBoard {
	private Pile[] stacks;

	public StackBoard(Deck d) {
		stacks = new Pile[7];

		for (int i = 0; i < stacks.length; i++) {
			stacks[i] = new Pile();

			for (int j = 0; j <= i; j++) {
				stacks[i].addCard(d.removeDeck());
			}
		}
	}
	
	public void addCard(Card c, int index) {
		stacks[index].addCard(c);
	}
	
	public void removeCard(int index) {
		stacks[index].removeCard();
	}
	
	public Card getTop(int index) {
		return stacks[index].getTop();
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for (int i = 0; i < stacks.length; i++) {
			s += "==== STACK " + i + " ====\n" + stacks[i] + "\n";
		}
		
		return s;
	}
}
