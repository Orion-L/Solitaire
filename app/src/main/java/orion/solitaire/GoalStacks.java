package orion.solitaire;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

public class GoalStacks {
	private int numCards;
	private int width;
    private int height;

    private Context c;
    private Pile[] stacks;
	
	public GoalStacks(Context c, RelativeLayout l, int xPos, int yPos, int width, int height, int baseId) {
		stacks = new Pile[Constants.numGoals];
		numCards = 0;
		int x = xPos;

		for (int i = 0; i < stacks.length; i++) {
            if (i > 0) x += width + 5;
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
	
	public boolean hasWon() {
		return (numCards == 52);
	}
}
