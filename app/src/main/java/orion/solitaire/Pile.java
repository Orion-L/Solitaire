package orion.solitaire;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

/**
 * Represents a pile of Card objects
 */
public class Pile {
    private int xPos, yPos, width, height;
    private Context c;
    private RelativeLayout l;
    private RelativeLayout.LayoutParams lp;
	private LinkedList<Card> pile;
	private LinkedList<ImageView> images;

    /**
     * Create a new Pile object
     * @param c Activity being used in
     * @param l Layout to add images to
     * @param xPos X-Coordinate (in pixels, from left) of pile
     * @param yPos Y-Coordinate (in pixels, from top) of pile
     * @param width Width of each card in pile
     * @param height Height of each card in pile
     * @param baseId Image id of the empty card
     */
	public Pile(Context c, RelativeLayout l, int xPos, int yPos, int width, int height, int baseId) {
		this.c = c;
        this.l = l;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        pile = new LinkedList<>();
        images = new LinkedList<>();

        ImageView base = new ImageView(c);
        base.setImageResource(baseId);
        base.setVisibility(View.VISIBLE);

        lp = new RelativeLayout.LayoutParams(width, height);
        lp.setMargins(xPos, yPos, 0, 0);
        base.setLayoutParams(lp);

        l.addView(base);

	}

    /**
     * Add a card to the pile
     * @param card Card to be added
     */
	public void addCard(Card card) {
		pile.push(card);

        ImageView newImg = new ImageView(c);
        newImg.setImageResource(card.getImageId());

        lp = new RelativeLayout.LayoutParams(width, height);
        lp.setMargins(xPos, yPos + images.size() * (height / 5), 0, 0);
        newImg.setLayoutParams(lp);

        l.addView(newImg);

        images.push(newImg);
	}

    /**
     * Remove the top card from the pile
     */
	public void removeCard() {
		pile.pop();

        ImageView i = images.pop();
        l.removeView(i);
	}

    /**
     * Get the top card of the pile
     * @return Returns the top card
     */
	public Card getTop() {
		return pile.getFirst();
	}
}
