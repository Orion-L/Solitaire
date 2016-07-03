package orion.solitaire;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlayScreen extends AppCompatActivity {
    private Deck deck;
    private GoalStacks goalStacks;
    private StackBoard boardStacks;

    private ImageView deckStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        int numStacks = Constants.numBoardStacks;
        int numGoals = Constants.numGoals;
        int spacing = Constants.cardSpacing;

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.playLayout);
        RelativeLayout.LayoutParams size;

        Configuration config = getResources().getConfiguration();
        float density = getResources().getDisplayMetrics().density;
        float screenWidth = (config.screenWidthDp) * density  - (getResources().getDimension(R.dimen.activity_horizontal_margin) * 2);
        System.out.println(R.dimen.activity_horizontal_margin);
        double pxWidth = ((screenWidth - (numStacks - 1) * spacing) / numStacks) + 0.5;
        double pxHeight = pxWidth * 1.445 + 0.5;
        int cardWidth = (int) pxWidth;
        int cardHeight = (int) pxHeight;

        goalStacks = new GoalStacks(this, layout, (int) (screenWidth - (numGoals * pxWidth + (numGoals - 1) * spacing)), 0,
                cardWidth, cardHeight, getResources().getIdentifier("empty2", "drawable", getPackageName()));

        deck = new Deck(this);
        boardStacks = new StackBoard(deck, this, layout, 0, (int) (pxHeight + pxHeight / 2),
                cardWidth, cardHeight, getResources().getIdentifier("empty2", "drawable", getPackageName()));

        deckStack = (ImageView) findViewById(R.id.deckView);
        size = (RelativeLayout.LayoutParams) deckStack.getLayoutParams();
        size.width = cardWidth;
        size.height = cardHeight;
    }
}
