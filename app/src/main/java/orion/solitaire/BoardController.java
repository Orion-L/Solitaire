package orion.solitaire;

import android.content.Context;
import android.widget.RelativeLayout;

import PlayingCards.Card;

public class BoardController {
	private Pile[] stacks;

	public BoardController(DeckController d, Context c, RelativeLayout l, int xPos, int yPos, int cardSpace, int width, int height, int baseId) {
		stacks = new Pile[Constants.numBoardStacks];
        int x = xPos;
		Card card;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + cardSpace;
			stacks[i] = new Pile(c, l, x, yPos, width, height, baseId);

			for (int j = 0; j <= i; j++) {
                card = d.removeTop();
                if (j == i) card.setFaceUp(true);
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
}
