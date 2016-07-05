package orion.solitaire;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Controls the stacks in the play area
 */
public class StackBoard {
	private Pile[] stacks;

	/**
	 * Create a new Stack Board object
	 * @param d Deck used in game
	 * @param c Activity being used in
	 * @param l Layout to add images to
	 * @param xPos X-Coordinate (in pixels, from left) of Stack Board
	 * @param yPos Y-Coordinate (in pixels, from top) of Stack Board
	 * @param width Width of each card
     * @param height Height of each card
     * @param baseId Image id of the empty card
     */
	public StackBoard(Deck d, Context c, RelativeLayout l, int xPos, int yPos, int width, int height, int baseId) {
		stacks = new Pile[Constants.numBoardStacks];
        int x = xPos;
		Card card;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + Constants.cardSpacing;
			stacks[i] = new Pile(c, l, x, yPos, width, height, baseId);

			for (int j = 0; j <= i; j++) {
                card = d.removeDeck();
                if (j == i) card.setFaceUp(true);
				stacks[i].addCard(card);
			}
		}
	}

    /**
     * Add a card to a given stack
     * @param c Card to add
     * @param index Index of stack
     */
	public void addCard(Card c, int index) {
		stacks[index].addCard(c);
	}

    /**
     * Remove the top card of a given stack
     * @param index Index of stack to remove from
     */
	public void removeCard(int index) {
		stacks[index].removeCard();
	}

    /**
     * Get the top card of a given stack
     * @param index Index of stack
     * @return Returns the top card of the given stack
     */
	public Card getTop(int index) {
		return stacks[index].getTop();
	}
}
