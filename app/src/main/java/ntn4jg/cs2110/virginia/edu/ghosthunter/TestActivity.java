package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by ntnayda on 4/22/15.
 */
public class TestActivity extends Activity {
    private MediaPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sound = MediaPlayer.create(this,R.raw.gamemusic);

        sound.start();
    }
}
