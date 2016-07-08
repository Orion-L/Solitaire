package orion.solitaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class PlayScreen extends AppCompatActivity {
    private SolitaireGame s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.playLayout);

        s = new SolitaireGame(this, layout);
    }

    public void resetGame(View v) {
        s.reset();
    }
}
