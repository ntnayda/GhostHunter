package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openHighScores(View view)
    {
        Intent intent = new Intent(MainActivity.this, HighScoresActivity.class);
        startActivity(intent);
        this.finish();
    }public void openPlay(View view)
    {
        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
        startActivity(intent);
        this.finish();
    }

}
