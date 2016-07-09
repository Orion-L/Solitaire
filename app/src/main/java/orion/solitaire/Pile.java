package orion.solitaire;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

import PlayingCards.Card;

public class Pile {
    private int xPos, yPos, width, height, baseId;
    private SolitaireGame s;
    private Context c;
    private RelativeLayout l;
    private RelativeLayout.LayoutParams lp;
    private PileType type;
	private LinkedList<Card> pile;
	private LinkedList<ImageView> images;

	public Pile(SolitaireGame s, Context c, RelativeLayout l, PileType type, int xPos, int yPos, int width, int height, int baseId) {
		this.s = s;
        this.c = c;
        this.l = l;
        this.type = type;
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
        int yCoord;
        ImageView newImg;

        if (type == PileType.BOARD) {
            newImg = new ImageView(c);
            int cardMargin;

            if (pile.size() > 0 && pile.getFirst().isFaceUp()) {
                cardMargin = (height / 3 + height / 12);
            } else {
                cardMargin = height / 5;
            }

            if (images.size() > 0) {
                lp = (RelativeLayout.LayoutParams) images.getFirst().getLayoutParams();
                yCoord = lp.topMargin + cardMargin;
            } else {
                yCoord = yPos;
            }

            l.addView(newImg);
            images.push(newImg);

            if (card.isFaceUp()) addBoardClick(card);
        } else if (type == PileType.GOAL) {
            yCoord = yPos;

            if (images.size() > 0) {
                newImg = images.getFirst();
            } else {
                newImg = new ImageView(c);
                images.add(newImg);
                l.addView(newImg);
                addGoalClick();
            }
        } else {
            return;
        }

        if (card.isFaceUp() || type == PileType.GOAL) {
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
        lp.setMargins(xPos, yCoord, 0, 0);
        newImg.setLayoutParams(lp);

        pile.push(card);
    }

	public void removeCard() {
        pile.pop();
        ImageView i = images.getFirst();

        if (type == PileType.BOARD) {
            images.pop();
            l.removeView(i);
        } else if (type == PileType.GOAL) {
            if (pile.size() > 0) {
                Card card = pile.getFirst();
                switch (card.getSuit()) {
                    case SPADE:
                        i.setImageResource(c.getResources().getIdentifier("s" + card.getValue(), "drawable", c.getPackageName()));
                        break;
                    case HEART:
                        i.setImageResource(c.getResources().getIdentifier("h" + card.getValue(), "drawable", c.getPackageName()));
                        break;
                    case CLUB:
                        i.setImageResource(c.getResources().getIdentifier("c" + card.getValue(), "drawable", c.getPackageName()));
                        break;
                    case DIAMOND:
                        i.setImageResource(c.getResources().getIdentifier("d" + card.getValue(), "drawable", c.getPackageName()));
                        break;
                }
            } else {
                images.pop();
                l.removeView(i);
            }
        }
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

    private void addBoardClick(final Card clicked) {
        ImageView img = images.getFirst();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked.equals(pile.getFirst())) {
                    if (s.addGoal(clicked) || s.addBoard(clicked)) {
                        removeCard();

                        if (pile.size() > 0 && !pile.getFirst().isFaceUp()) flipView();
                    }
                } else {
                    LinkedList<Card> cardStack = new LinkedList<>();
                    int index = pile.indexOf(clicked);

                    for (int i = 0; i <= index; i++) {
                        cardStack.push(pile.get(i));
                    }

                    if (s.addBoardStack(cardStack)) {
                        for (int i = 0; i <= index; i++) {
                            removeCard();
                        }

                        if (pile.size() > 0 && !pile.getFirst().isFaceUp()) flipView();
                    }
                }
            }
        });
    }

    private void addGoalClick() {
        ImageView img = images.getFirst();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card clicked = pile.getFirst();
                if (s.addBoard(clicked)) {
                    removeCard();
                }
            }
        });
    }

    private void flipView() {
        Card card = pile.getFirst();
        card.setFaceUp(true);

        ImageView i = images.getFirst();

        switch (card.getSuit()) {
            case SPADE:
                i.setImageResource(c.getResources().getIdentifier("s" + card.getValue(), "drawable", c.getPackageName()));
                break;
            case HEART:
                i.setImageResource(c.getResources().getIdentifier("h" + card.getValue(), "drawable", c.getPackageName()));
                break;
            case CLUB:
                i.setImageResource(c.getResources().getIdentifier("c" + card.getValue(), "drawable", c.getPackageName()));
                break;
            case DIAMOND:
                i.setImageResource(c.getResources().getIdentifier("d" + card.getValue(), "drawable", c.getPackageName()));
                break;
        }

        addBoardClick(card);
    }
}
