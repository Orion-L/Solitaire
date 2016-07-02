package orion.solitaire;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.StackView;

public class PlayScreen extends AppCompatActivity {
    private static final int numBoardStack = 7;
    private static final int numGoalStack = 4;

    private Deck d;
    private ImageView[] goalStack;
    private ImageView[] boardStack;
    private ImageView deckStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        //StackView stackView = (StackView) findViewById(R.id.stackView);
        //stackView.setAdapter(new ImageAdapter(this));

        Configuration config = getResources().getConfiguration();
        float density = getResources().getDisplayMetrics().density;
        float screenWidth = (config.screenWidthDp - (getResources().getDimension(R.dimen.activity_horizontal_margin) / 2)) * density;
        double pxWidth = (screenWidth / numBoardStack) - (numBoardStack - 1) + 0.5;
        double pxHeight = pxWidth * 1.445 + 0.5;
        int cardWidth = (int) pxWidth;
        int cardHeight = (int) pxHeight;

        RelativeLayout.LayoutParams size;

        goalStack = new ImageView[numGoalStack];
        int id;

        for (int i = 0; i < goalStack.length; i++) {
            id = getResources().getIdentifier("goalView" + (i + 1), "id", getPackageName());
            goalStack[i] = (ImageView) findViewById(id);
            size = (RelativeLayout.LayoutParams) goalStack[i].getLayoutParams();
            size.width = cardWidth;
            size.height = cardHeight;
        }

        boardStack = new ImageView[numBoardStack];

        for (int i = 0; i < boardStack.length; i++) {
            id = getResources().getIdentifier("boardView" + (i + 1), "id", getPackageName());
            boardStack[i] = (ImageView) findViewById(id);
            size = (RelativeLayout.LayoutParams) boardStack[i].getLayoutParams();
            size.width = cardWidth;
            size.height = cardHeight;
        }

        deckStack = (ImageView) findViewById(R.id.deckView);
        size = (RelativeLayout.LayoutParams) deckStack.getLayoutParams();
        size.width = cardWidth;
        size.height = cardHeight;
    }
}
