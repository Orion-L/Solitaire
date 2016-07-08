package orion.solitaire;

import android.content.Context;
import android.widget.RelativeLayout;

import PlayingCards.Card;

public class GoalController {
	public static final int NUM_GOALS = 4;

	private static final int NUM_CARDS = 52;
	private int numCards;
    private Pile[] stacks;

	public GoalController(SolitaireGame s, Context c, RelativeLayout l, int xPos, int yPos, int cardSpace, int width, int height, int baseId) {
		stacks = new Pile[NUM_GOALS];
		numCards = 0;
		int x = xPos;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + cardSpace;
			stacks[i] = new Pile(c, l, x, yPos, width, height, baseId);
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

    public int getSize(int index) {
        return stacks[index].getSize();
    }

	public boolean hasWon() {
		return (numCards == NUM_CARDS);
	}

    public void reset() {
        numCards = 0;

        for (Pile p : stacks) {
            p.empty();
        }
    }
}
