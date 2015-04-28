package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ntnayda on 4/6/15.
 */
public class HighScoresActivity extends Activity{
    //public static final String PREFS_NAME = "MyPrefsFile";
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_scores);
        position = SplashActivity.nhspos;
        //get text view

        TextView captured1 = (TextView)findViewById(R.id.captured1);
        TextView score1 = (TextView)findViewById(R.id.score1);
        String[] h1 = SplashActivity.hs1.split(";");
        captured1.setText(h1[0]);
        score1.setText(h1[1]);

        TextView captured2 = (TextView)findViewById(R.id.captured2);
        TextView score2 = (TextView)findViewById(R.id.score2);
        String[] h2 = SplashActivity.hs2.split(";");
        captured2.setText(h2[0]);
        score2.setText(h2[1]);

        TextView captured3 = (TextView)findViewById(R.id.captured3);
        TextView score3 = (TextView)findViewById(R.id.score3);
        String[] h3 = SplashActivity.hs3.split(";");
        captured3.setText(h3[0]);
        score3.setText(h3[1]);

        TextView captured4 = (TextView)findViewById(R.id.captured4);
        TextView score4 = (TextView)findViewById(R.id.score4);
        String[] h4 = SplashActivity.hs4.split(";");
        captured4.setText(h4[0]);
        score4.setText(h4[1]);

        TextView captured5 = (TextView)findViewById(R.id.captured5);
        TextView score5 = (TextView)findViewById(R.id.score5);
        String[] h5 = SplashActivity.hs5.split(";");
        captured5.setText(h5[0]);
        score5.setText(h5[1]);

        TextView captured6 = (TextView)findViewById(R.id.captured6);
        TextView score6 = (TextView)findViewById(R.id.score6);
        String[] h6 = SplashActivity.hs6.split(";");
        captured6.setText(h6[0]);
        score6.setText(h6[1]);

        TextView captured7 = (TextView)findViewById(R.id.captured7);
        TextView score7 = (TextView)findViewById(R.id.score7);
        String[] h7 = SplashActivity.hs7.split(";");
        captured7.setText(h7[0]);
        score7.setText(h7[1]);

        TextView captured8 = (TextView)findViewById(R.id.captured8);
        TextView score8 = (TextView)findViewById(R.id.score8);
        String[] h8 = SplashActivity.hs8.split(";");
        captured8.setText(h8[0]);
        score8.setText(h8[1]);

        TextView captured9 = (TextView)findViewById(R.id.captured9);
        TextView score9 = (TextView)findViewById(R.id.score9);
        String[] h9 = SplashActivity.hs9.split(";");
        captured9.setText(h9[0]);
        score9.setText(h9[1]);

        TextView captured10 = (TextView)findViewById(R.id.captured10);
        TextView score10 = (TextView)findViewById(R.id.score10);
        String[] h10 = SplashActivity.hs10.split(";");
        captured10.setText(h10[0]);
        score10.setText(h10[1]);


        SharedPreferences sharedPref = getSharedPreferences("HighScores",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("h1", SplashActivity.hs1);
        editor.putString("h2", SplashActivity.hs2);
        editor.putString("h3", SplashActivity.hs3);
        editor.putString("h4", SplashActivity.hs4);
        editor.putString("h5", SplashActivity.hs5);
        editor.putString("h6", SplashActivity.hs6);
        editor.putString("h7", SplashActivity.hs7);
        editor.putString("h8", SplashActivity.hs8);
        editor.putString("h9", SplashActivity.hs9);
        editor.putString("h10", SplashActivity.hs10);
        editor.apply();

        if (position == 1) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row1);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 2) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row2);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 3) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row3);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 4) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row4);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 5) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row5);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 6) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row6);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 7) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row7);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 8) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row8);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 9) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row9);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
        if (position == 10) {
            RelativeLayout row = (RelativeLayout)findViewById(R.id.row10);
            row.setBackgroundColor(getResources().getColor(R.color.orange));
        }
    }
}
