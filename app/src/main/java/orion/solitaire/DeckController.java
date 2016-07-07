package orion.solitaire;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import PlayingCards.Card;
import PlayingCards.Deck;

public class DeckController {
    private static final int deckDrawMargin = 15;

    private int width, height, baseId, backId;
    private Context c;
    private RelativeLayout l;
    private RelativeLayout.LayoutParams lp;
    private ImageView deckView;
    private ImageView[] drawView;
    private Deck d;
    private Deck drawn;

    public DeckController(Context c, RelativeLayout l, int width, int height, int baseId, int backId) {
        this.c = c;
        this.l = l;
        this.width = width;
        this.height = height;
        this.baseId = baseId;
        this.backId = backId;

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

    private void flipThree() {
        if (d.getSize() == 0) {
            flipDeck();
            deckView.setImageResource(backId);

            for (int i = 0; i < 3; i++) {
                drawView[i].setVisibility(View.INVISIBLE);
            }
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

                drawView[i].setImageResource(id);
                drawView[i].setVisibility(View.VISIBLE);
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
        }
    }
}
