package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ntnayda on 4/8/15.
 */
public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
    }

    public void openMainMenu(View view) {
        Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void openHighScores1(View view)
    {
        Intent intent = new Intent(GameOverActivity.this, HighScoresActivity.class);
        startActivity(intent);
        this.finish();
    }
}
