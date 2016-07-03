package orion.solitaire;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

public class Pile {
    private int xPos, yPos, width, height, baseId;
    private Context c;
    private RelativeLayout l;
    private RelativeLayout.LayoutParams lp;
	private LinkedList<Card> pile;
	private LinkedList<ImageView> images;
	
	public Pile(Context c, RelativeLayout l, int xPos, int yPos, int width, int height, int baseId) {
		this.c = c;
        this.l = l;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.baseId = baseId;

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
	
	public void removeCard() {
		pile.pop();

        ImageView i = images.pop();
        l.removeView(i);
	}
	
	public Card getTop() {
		return pile.getFirst();
	}
	
	@Override
	public String toString() {
		String s = "Size = " + pile.size() + "\n\n";
		
		for (Card c : pile) {
			s += c.getSuit() + " " + c.getValue() + "\n";
		}
		
		return s;
	}
}
