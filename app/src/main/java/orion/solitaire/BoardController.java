package orion.solitaire;

import android.content.Context;
import android.widget.RelativeLayout;

import PlayingCards.Card;

public class BoardController {
	public static final int NUM_BOARD_STACKS = 7;

	private Pile[] stacks;

	public BoardController(SolitaireGame s, Context c, RelativeLayout l, int xPos, int yPos, int cardSpace, int width, int height, int baseId) {
		stacks = new Pile[NUM_BOARD_STACKS];
        int x = xPos;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + cardSpace;
			stacks[i] = new Pile(s, c, l, PileType.BOARD, x, yPos, width, height, baseId);
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

    public int getSize(int index) {
        return stacks[index].getSize();
    }

    public void clear() {
        for (Pile p : stacks) {
            p.empty();
        }
    }
}
