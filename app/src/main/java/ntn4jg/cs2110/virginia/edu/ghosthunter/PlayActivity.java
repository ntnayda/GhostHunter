package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ntnayda on 4/6/15.
 */
public class PlayActivity extends Activity {
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new World(this));
    }
    public void onGameOver(View view){
        Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
        startActivity(intent);
    }
}
