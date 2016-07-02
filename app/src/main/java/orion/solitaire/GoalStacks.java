package orion.solitaire;

public class GoalStacks {
	private Pile[] stacks;
	private int numCards;
	
	public GoalStacks() {
		stacks = new Pile[4];
		numCards = 0;
		
		for (int i = 0; i < 4; i++) {
			stacks[i] = new Pile();
		}
	}
	
	public void addCard(Card c, int index) {
		numCards++;
		stacks[index].addCard(c);
	}
	
	public void removeCard(int index) {
		numCards--;
		stacks[index].removeCard();
	}
	
	public Card getTop(int index) {
		return stacks[index].getTop();
	}
	
	public boolean hasWon() {
		return (numCards == 52);
	}
}
