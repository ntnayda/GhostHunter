package ntn4jg.cs2110.virginia.edu.ghosthunter;

/**
 * Created by ntnayda on 4/6/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
/**
 * This file displays the splash screen activity and then starts the next activity which
 * (in this simple example) displays "Hello world!" (MainActivity)
 */
public class SplashActivity extends Activity {
    private static String TAG = SplashActivity.class.getName(); // Used to report an error in run()
    private static long SLEEP_TIME = 4; // Set the duration of the splash screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To create a minimal appearance, remove the title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ... and remove the notification bar if it exists
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash); // Refers to the splash.xml file in the layout directory
        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }
    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and then start new activity
         */
        public void run() {
            try {
                // Sleeping - displays the splash screen for this long before switching activities
                Thread.sleep(SLEEP_TIME*1000); // Display (sleep)
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            // Start main activity
            // Create an Intent in SplashActivity to start the MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
