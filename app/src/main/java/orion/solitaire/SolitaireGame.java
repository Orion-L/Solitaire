package orion.solitaire;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.RelativeLayout;

import PlayingCards.Card;
import PlayingCards.Suit;

public class SolitaireGame {
    public static final int SPACING = 5;

    private Context c;
    private RelativeLayout l;
    private DeckController deckC;
    private BoardController boardC;
    private GoalController goalC;

    public SolitaireGame(Context c, RelativeLayout l) {
        this.c = c;
        this.l = l;

        int numStacks = BoardController.NUM_BOARD_STACKS;
        int numGoals = GoalController.NUM_GOALS;

        Configuration config = c.getResources().getConfiguration();

        float density = c.getResources().getDisplayMetrics().density;
        float screenWidth = (config.screenWidthDp) * density  - (c.getResources().getDimension(R.dimen.activity_horizontal_margin) * 2);

        double pxWidth = ((screenWidth - (numStacks - 1) * SPACING) / numStacks) + 0.5;
        double pxHeight = pxWidth * 1.445 + 0.5;

        int cardWidth = (int) pxWidth;
        int cardHeight = (int) pxHeight;

        int goalX = (int) (screenWidth - (numGoals * pxWidth + (numGoals - 1) * SPACING));
        int goalY = 0;
        int boardX = 0;
        int boardY = (int) (pxHeight + pxHeight / 2);

        int emptyId = c.getResources().getIdentifier("empty", "drawable", c.getPackageName());

        deckC = new DeckController(this, c, l, cardWidth, cardHeight, emptyId, c.getResources().getIdentifier("back", "drawable", c.getPackageName()));

        boardC = new BoardController(this, c, l, boardX, boardY, SPACING, cardWidth, cardHeight, emptyId);

        goalC = new GoalController(this, c, l, goalX, goalY, SPACING, cardWidth, cardHeight, emptyId);

        initialiseBoard();
    }

    public void reset() {
        deckC.reset();
        boardC.reset();
        goalC.reset();

        initialiseBoard();
    }

    public boolean addGoal(Card card) {
        Card top;

        for (int i = 0; i < GoalController.NUM_GOALS; i++) {
            if (goalC.getSize(i) > 0) {
                top = goalC.getTop(i);

                if (top.getSuit() == card.getSuit() && top.getValue() == card.getValue() - 1) {
                    goalC.addCard(card, i);
                    return true;
                }
            } else if (card.getValue() == 1) {
                goalC.addCard(card, i);
                return true;
            }
        }

        return false;
    }

    public boolean addBoard(Card card) {
        Card top;

        for (int i = 0; i < BoardController.NUM_BOARD_STACKS; i++) {
            if (boardC.getSize(i) > 0) {
                top = boardC.getTop(i);

                if (top.getValue() == card.getValue() + 1) {
                    switch (top.getSuit()) {
                        case SPADE:
                        case CLUB:
                            if (card.getSuit() == Suit.HEART || card.getSuit() == Suit.DIAMOND) {
                                boardC.addCard(card, i);
                                return true;
                            }

                            break;
                        case HEART:
                        case DIAMOND:
                            if (card.getSuit() == Suit.SPADE || card.getSuit() == Suit.CLUB) {
                                boardC.addCard(card, i);
                                return true;
                            }

                            break;
                    }
                }
            } else if (card.getValue() == 13) {
                goalC.addCard(card, i);
                return true;
            }
        }

        return false;
    }

    private void initialiseBoard() {
        Card card;

        for (int i = 0; i < BoardController.NUM_BOARD_STACKS; i++) {
            for (int j = 0; j <= i; j++) {
                card = deckC.removeTop();
                if (j == i) card.setFaceUp(true);
                boardC.addCard(card, i);
            }
        }
    }
}
