package ntn4jg.cs2110.virginia.edu.ghosthunter;

/**
 * Created by ntnayda on 4/6/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * This file displays the splash screen activity and then starts the next activity which
 * (in this simple example) displays "Hello world!" (MainActivity)
 */


public class SplashActivity extends Activity {
    static String hs1 = "";
    static String hs2 = "";
    static String hs3 = "";
    static String hs4 = "";
    static String hs5 = "";
    static String hs6 = "";
    static String hs7 = "";
    static String hs8 = "";
    static String hs9 = "";
    static String hs10 = "";
    static boolean hsvisible;
    static int nhspos=0;
    private static String TAG = SplashActivity.class.getName(); // Used to report an error in run()
    private static long SLEEP_TIME = 4; // Set the duration of the splash screen


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // To create a minimal appearance, remove the title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ... and remove the notification bar if it exists
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPref = getSharedPreferences("HighScores", Context.MODE_PRIVATE);


            if (sharedPref.getString("h1",null)==null) {
                hs1 = "0;0";
            }
        else hs1 = sharedPref.getString("h1",null);

            if (sharedPref.getString("h2",null)==null) {
                hs2 = "0;0";
            }
            else hs2 = sharedPref.getString("h2",null);
            if (sharedPref.getString("h3",null)==null) {
                hs3 = "0;0";
            }
            else hs3 = sharedPref.getString("h3",null);
            if (sharedPref.getString("h4",null)==null) {
                hs4 = "0;0";
            }
            else hs4 = sharedPref.getString("h4",null);
            if (sharedPref.getString("h5",null)==null) {
                hs5 = "0;0";
            }
            else hs5 = sharedPref.getString("h5",null);
            if (sharedPref.getString("h6",null)==null) {
                hs6 = "0;0";
            }
            else hs6 = sharedPref.getString("h6",null);
            if (sharedPref.getString("h7",null)==null) {
                hs7 = "0;0";
            }
            else hs7 = sharedPref.getString("h7",null);
            if (sharedPref.getString("h8",null)==null) {
                hs8 = "0;0";
            }
            else hs8 = sharedPref.getString("h8",null);
            if (sharedPref.getString("h9",null)==null) {
                hs9 = "0;0";
            }
            else hs9 = sharedPref.getString("h9",null);
            if (sharedPref.getString("h10",null)==null) {
                hs10 = "0;0";
            }
            else hs10 = sharedPref.getString("h10",null);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash); // Refers to the splash.xml file in the layout directory

        // Start timer and launch main activity
        Log.d("hs1",hs1);
        //Log.d("hs2",hs2);
        //Log.d("hs3",hs3);
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
        }
    }
}
