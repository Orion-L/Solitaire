package orion.solitaire;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Controls a number of goal stacks
 */
public class GoalStacks {
	private int numCards;
	private int width;
    private int height;

    private Context c;
    private Pile[] stacks;

    /**
     * Create new goal stack objects
     * @param c Activity being used in
     * @param l Layout to add images to
     * @param xPos X-Coordinate (in pixels, from left) of goal stacks
     * @param yPos Y-Coordinate (in pixels, from top) of goal stacks
     * @param width Width of each card in stacks
     * @param height Height of each card in stacks
     * @param baseId Image id of the empty card
     */
	public GoalStacks(Context c, RelativeLayout l, int xPos, int yPos, int width, int height, int baseId) {
		stacks = new Pile[Constants.numGoals];
		numCards = 0;
		int x = xPos;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + 5;
			stacks[i] = new Pile(c, l, x, yPos, width, height, baseId);
		}
	}

    /**
     * Add a card to a given stack
     * @param c Card to add
     * @param index Index of stack
     */
	public void addCard(Card c, int index) {
		numCards++;
		stacks[index].addCard(c);
	}

    /**
     * Remove the top card from a given stack
     * @param index Index of stack
     */
	public void removeCard(int index) {
		numCards--;
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

    /**
     * Check if all cards have been added to goal stacks
     * @return Returns true if all cards are in the goal stacks, false otherwise
     */
	public boolean hasWon() {
		return (numCards == Constants.numCards);
	}
}
