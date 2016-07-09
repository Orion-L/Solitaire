package orion.solitaire;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;

import PlayingCards.Card;
import PlayingCards.Deck;

public class DeckController {
    private static final int deckDrawMargin = 15;

    private int baseId, backId;
    private SolitaireGame s;
    private Context c;
    private RelativeLayout.LayoutParams lp;
    private ImageView deckView;
    private ImageView[] drawView;
    private Deck d;
    private Deck drawn;
    private LinkedList<Integer> drawnId;

    public DeckController(SolitaireGame s, Context c, RelativeLayout l, int width, int height, int baseId, int backId) {
        this.s = s;
        this.c = c;
        this.baseId = baseId;
        this.backId = backId;
        this.drawnId = new LinkedList<>();

        d = new Deck();
        drawn = new Deck();

        d.init();

        deckView = new ImageView(c);
        deckView.setImageResource(backId);
        deckView.setVisibility(View.VISIBLE);
        deckView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipThree();
            }
        });

        lp = new RelativeLayout.LayoutParams(width, height);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        deckView.setLayoutParams(lp);

        l.addView(deckView);

        drawView = new ImageView[3];

        int cardMargin = (width / 3 + width / 12);

        for (int i = 0; i < drawView.length; i++) {
            drawView[i] = new ImageView(c);
            drawView[i].setVisibility(View.INVISIBLE);

            lp = new RelativeLayout.LayoutParams(width, height);
            lp.setMargins(width + deckDrawMargin + i * cardMargin, 0, 0, 0);
            drawView[i].setLayoutParams(lp);

            l.addView(drawView[i]);
        }
    }

    public Card removeTop() {
        if (d.getSize() > 0) return d.draw();

        return null;
    }

    public void reset() {
        for (int i = 2; i >= 0; i--) {
            if (drawView[i].getVisibility() == View.VISIBLE) {
                drawView[i].setOnClickListener(null);
                break;
            }
        }

        flipDeck();
        deckView.setImageResource(backId);
        d.init();
    }

    private void flipThree() {
        for (int i = 2; i >= 0; i--) {
            if (drawView[i].getVisibility() == View.VISIBLE) {
                drawView[i].setOnClickListener(null);
                break;
            }
        }

        if (d.getSize() == 0) {
            flipDeck();
            deckView.setImageResource(backId);
        } else {
            Card card;
            int s = d.getSize();

            for (int i = 0; i < 3 && i < s; i++) {
                card = d.draw();
                card.setFaceUp(true);
                drawn.add(card);

                int id;
                switch (card.getSuit()) {
                    case SPADE:
                        id = c.getResources().getIdentifier("s" + card.getValue(), "drawable", c.getPackageName());
                        break;
                    case HEART:
                        id = c.getResources().getIdentifier("h" + card.getValue(), "drawable", c.getPackageName());
                        break;
                    case CLUB:
                        id = c.getResources().getIdentifier("c" + card.getValue(), "drawable", c.getPackageName());
                        break;
                    case DIAMOND:
                        id = c.getResources().getIdentifier("d" + card.getValue(), "drawable", c.getPackageName());
                        break;
                    default:
                        id = c.getResources().getIdentifier("empty", "drawable", c.getPackageName());
                }

                drawnId.push(id);

                if (drawView[i].getVisibility() == View.INVISIBLE) {
                    drawView[i].setImageResource(id);
                    drawView[i].setVisibility(View.VISIBLE);
                } else {
                    int j;
                    for (j = 1; j < 3 && drawView[j].getVisibility() != View.INVISIBLE; j++) {
                        drawView[j - 1].setImageDrawable(drawView[j].getDrawable());
                    }

                    if (j == 3) j -= 1;
                    drawView[j].setImageResource(id);
                    drawView[j].setVisibility(View.VISIBLE);
                }
            }

            for (int i = 2; i >= 0; i--) {
                if (drawView[i].getVisibility() == View.VISIBLE) {
                    addClickListener(i);
                    break;
                }
            }

            if (d.getSize() == 0) deckView.setImageResource(baseId);
        }
    }

    private void flipDeck() {
        Card card;
        int s = drawn.getSize();

        for (int i = 0; i < s; i++)  {
            card = drawn.draw();
            card.setFaceUp(false);
            d.add(card);

            if (i < 3) drawView[i].setVisibility(View.INVISIBLE);
        }
    }

    private void addClickListener(final int index) {
        drawView[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card clicked = drawn.draw();
                SolitaireGame s = DeckController.this.s;

                if (s.addGoal(clicked) || s.addBoard(clicked)) {
                    drawnId.pop();

                    if (drawn.getSize() < 3) {
                        drawView[index].setOnClickListener(null);
                        drawView[index].setVisibility(View.INVISIBLE);

                        if (index > 0) {
                            addClickListener(index - 1);
                        }
                    } else {
                        int j;
                        for (j = 2; j > 0 && drawView[j].getVisibility() != View.INVISIBLE; j--) {
                            drawView[j].setImageDrawable(drawView[j - 1].getDrawable());
                        }

                        drawView[j].setImageResource(drawnId.get(2));
                    }
                } else {
                    drawn.add(clicked);
                }
            }
        });
    }
}
