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

        int cardMargin;

        if (pile.size() > 0 && pile.getFirst().isFaceUp()) {
            cardMargin = (height / 3 + height / 12);
        } else {
            cardMargin = height / 5;
        }

        int yCoord;

        if (images.size() > 0) {
            lp = (RelativeLayout.LayoutParams) images.getFirst().getLayoutParams();
            yCoord = lp.topMargin + cardMargin;
        } else {
            yCoord = yPos;
        }

        lp = new RelativeLayout.LayoutParams(width, height);
        lp.setMargins(xPos, yCoord, 0, 0);
        newImg.setLayoutParams(lp);

        l.addView(newImg);

        images.push(newImg);
        pile.push(card);

    }

	public void removeCard() {
		pile.pop();

        ImageView i = images.pop();
        l.removeView(i);
	}

	public Card getTop() {
		return pile.getFirst();
	}

    public int getSize() {
        return pile.size();
    }

    public void empty() {
        for (ImageView i : images) l.removeView(i);

        pile.clear();
        images.clear();
    }
}
