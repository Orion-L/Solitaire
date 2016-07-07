package orion.solitaire;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

import PlayingCards.Card;

public class Pile {
    private int xPos, yPos, width, height;
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

        if (card.isFaceUp()) {
            switch (card.getSuit()) {
                case SPADE:
                    newImg.setImageResource(c.getResources().getIdentifier("s" + card.getValue(), "drawable", c.getPackageName()));
                    break;
                case HEART:
                    newImg.setImageResource(c.getResources().getIdentifier("h" + card.getValue(), "drawable", c.getPackageName()));
                    break;
                case CLUB:
                    newImg.setImageResource(c.getResources().getIdentifier("c" + card.getValue(), "drawable", c.getPackageName()));
                    break;
                case DIAMOND:
                    newImg.setImageResource(c.getResources().getIdentifier("d" + card.getValue(), "drawable", c.getPackageName()));
                    break;
            }
        } else {
            newImg.setImageResource(R.drawable.back);
        }

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
}
