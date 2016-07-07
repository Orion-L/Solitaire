package orion.solitaire;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlayScreen extends AppCompatActivity {
    private static final int spacing = 5;

    private DeckController deckC;
    private GoalController goalC;
    private BoardController boardC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        int numStacks = Constants.numBoardStacks;
        int numGoals = Constants.numGoals;

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.playLayout);
        RelativeLayout.LayoutParams size;

        Configuration config = getResources().getConfiguration();

        float density = getResources().getDisplayMetrics().density;
        float screenWidth = (config.screenWidthDp) * density  - (getResources().getDimension(R.dimen.activity_horizontal_margin) * 2);

        double pxWidth = ((screenWidth - (numStacks - 1) * spacing) / numStacks) + 0.5;
        double pxHeight = pxWidth * 1.445 + 0.5;

        int cardWidth = (int) pxWidth;
        int cardHeight = (int) pxHeight;

        int goalX = (int) (screenWidth - (numGoals * pxWidth + (numGoals - 1) * spacing));
        int goalY = 0;
        int boardX = 0;
        int boardY = (int) (pxHeight + pxHeight / 2);

        int emptyId = getResources().getIdentifier("empty", "drawable", getPackageName());

        deckC = new DeckController(this, layout, cardWidth, cardHeight, emptyId, getResources().getIdentifier("back", "drawable", getPackageName()));

        boardC = new BoardController(deckC, this, layout, boardX, boardY, spacing, cardWidth, cardHeight, emptyId);

        goalC = new GoalController(this, layout, goalX, goalY, spacing, cardWidth, cardHeight, emptyId);
    }
}
