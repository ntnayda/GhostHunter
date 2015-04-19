package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ntnayda on 4/6/15.
 */
public class PlayActivity extends Activity {
    static private boolean running;

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        PlayActivity.running = running;
    }
    static int money;
    public int screenWidth;
    public int screenHeight;
    public World world;
    private Intent intent;
    static boolean invincible;
    static boolean bomb;
    static boolean attract;
    static boolean rocket;
    static boolean repel;
    static long sleepTime = 5;


    public double getMoney() {
        return money;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        money = 0;
        running = true;
        invincible=false;
        bomb=false;
        attract=false;
        rocket=false;
        repel =false;
        intent = new Intent(this, BroadcastService.class);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth= size.x;
        screenHeight = size.y;
        setContentView(R.layout.play_screen);
        LinearLayout gameView = (LinearLayout) findViewById(R.id.playscreen);
        world = new World(this,screenWidth,screenHeight);
        gameView.addView(world);
        new runCheck().execute();
        intent = new Intent(this, BroadcastService.class);
    }
    /*public static void gameOver() {
        Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
        finish();
        startActivity(intent);
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class runCheck extends AsyncTask<Void, Integer, String>
    {
        private int time = 0;
        protected void onPreExecute (){
            Log.d("PreExceute","On pre Exceute......");
        }

        protected String doInBackground(Void...arg0) {
            Log.d("on:","do in backround");
            while (running) {
            }
            return "You are at PostExecute";
        }

        protected void onProgressUpdate(Integer...a){
            //Log.d("You are in progres ","1");
        }

        protected void onPostExecute(String result) {
            Log.d(" ","done");

            Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
            finish();
            startActivity(intent);
        }
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        stopService(intent);
    }

    private void updateUI(Intent intent) {
        String moneyvalue = intent.getStringExtra("money");
        TextView moneyDisplay = (TextView) findViewById(R.id.yourScore);
        moneyDisplay.setText(moneyvalue);
    }

    public void bomb(View view) {
        bomb=true;
        BombWait wait = new BombWait();
        wait.start();
    }

    public void repel(View view) {
        if (money > 1000) {
            money -= 1000;
            repel = true;
            RepelWait wait = new RepelWait();
            wait.start();
        }
    }
    public void attract(View view) {
        if (money > 1000) {
            money -= 1000;
            attract = true;
            AttractWait wait = new AttractWait();
            wait.start();
        }
    }
    public void invincible(View view) {
        if (money > 1000) {
            money -= 1000;
            invincible = true;
            InvincibleWait wait = new InvincibleWait();
            wait.start();
        }
    }
    public void rocket(View view) {
        if (money > 1000) {
            money -= 1000;
            rocket = true;
            RocketWait wait = new RocketWait();
            wait.start();
        }
    }
    private class BombWait extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime * 1000);
            }
            catch (Exception e){
                Log.e("Wait error",e.getMessage());
            }
            bomb=false;

        }
    }
    private class RepelWait extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime * 1000);
            }
            catch (Exception e){
                Log.e("Wait error",e.getMessage());
            }
            repel=false;

        }
    }
    private class InvincibleWait extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime * 1000);
            }
            catch (Exception e){
                Log.e("Wait error",e.getMessage());
            }
            invincible=false;

        }
    }
    private class AttractWait extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime * 1000);
            }
            catch (Exception e){
                Log.e("Wait error",e.getMessage());
            }
            attract=false;

        }
    }
    private class RocketWait extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime * 1000);
            }
            catch (Exception e){
                Log.e("Wait error",e.getMessage());
            }
            rocket=false;

        }
    }
}
