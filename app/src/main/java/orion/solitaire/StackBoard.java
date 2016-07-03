package orion.solitaire;

import android.content.Context;
import android.widget.RelativeLayout;

public class StackBoard {
	private Pile[] stacks;

	public StackBoard(Deck d, Context c, RelativeLayout l, int xPos, int yPos, int width, int height, int baseId) {
		stacks = new Pile[Constants.numBoardStacks];
        int x = xPos;
		Card card;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + Constants.cardSpacing;
			stacks[i] = new Pile(c, l, x, yPos, width, height, baseId);

			for (int j = 0; j <= i; j++) {
                card = d.removeDeck();
                if (j == i) card.setFace(true);
				stacks[i].addCard(card);
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
