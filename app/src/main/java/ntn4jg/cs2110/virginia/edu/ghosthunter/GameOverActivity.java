package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ntnayda on 4/8/15.
 */
public class GameOverActivity extends Activity {
    public static boolean visibility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("vis:",""+visibility);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gameover);
        if(visibility) {
            TextView hs = (TextView)findViewById(R.id.newhs);
            hs.setVisibility(View.VISIBLE);
        }
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
    }
}
