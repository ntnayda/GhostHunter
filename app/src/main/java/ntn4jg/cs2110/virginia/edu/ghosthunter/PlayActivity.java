package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by ntnayda on 4/6/15.
 */
public class PlayActivity extends Activity implements SensorEventListener{
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
    static boolean pause;
    private SensorManager sensorManager = null;
    public static float xPosition, xAcceleration,xVelocity = 0.0f;
    public static float yPosition, yAcceleration,yVelocity = 0.0f;
    public static float frameTime = 0.1f;
    private MediaPlayer gamemusic;
    private MediaPlayer lose;

    public double getMoney() {
        return money;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set FullScreen & portrait
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gamemusic = MediaPlayer.create(this, R.raw.gamemusic);
        lose = MediaPlayer.create(this, R.raw.lose);
        gamemusic.start();
        gamemusic.setLooping(true);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
        money = 0;
        running = true;
        invincible=false;
        bomb=false;
        attract=false;
        rocket=false;
        repel =false;
        pause = false;
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
    public static TreeMap<Integer, Integer> SortByValue
    (HashMap<Integer, Integer> unsortedmap) {
        ValueComparator vc =  new ValueComparator(unsortedmap);
        TreeMap<Integer,Integer> sortedMap = new TreeMap<Integer,Integer>(vc);
        sortedMap.putAll(unsortedmap);
        return sortedMap;
    }
    @Override
    protected void onDestroy() {
        SplashActivity.hsvisible=false;
        HashMap<Integer, Integer> scoretocaptured = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> unsortedmap = new HashMap<Integer, Integer>();
        String score1[] = SplashActivity.hs1.split(";");
        String score2[] = SplashActivity.hs2.split(";");
        String score3[] = SplashActivity.hs3.split(";");
        String score4[] = SplashActivity.hs4.split(";");
        String score5[] = SplashActivity.hs5.split(";");
        String score6[] = SplashActivity.hs6.split(";");
        String score7[] = SplashActivity.hs7.split(";");
        String score8[] = SplashActivity.hs8.split(";");
        String score9[] = SplashActivity.hs9.split(";");
        String score10[] = SplashActivity.hs10.split(";");
        //add all the scores to the treemap to sort
        scoretocaptured.put(Integer.parseInt(score1[1]),Integer.parseInt(score1[0]));
        scoretocaptured.put(Integer.parseInt(score2[1]),Integer.parseInt(score2[0]));
        scoretocaptured.put(Integer.parseInt(score3[1]),Integer.parseInt(score3[0]));
        scoretocaptured.put(Integer.parseInt(score4[1]),Integer.parseInt(score4[0]));
        scoretocaptured.put(Integer.parseInt(score5[1]),Integer.parseInt(score5[0]));
        scoretocaptured.put(Integer.parseInt(score6[1]),Integer.parseInt(score6[0]));
        scoretocaptured.put(Integer.parseInt(score7[1]),Integer.parseInt(score7[0]));
        scoretocaptured.put(Integer.parseInt(score8[1]),Integer.parseInt(score8[0]));
        scoretocaptured.put(Integer.parseInt(score9[1]),Integer.parseInt(score9[0]));
        scoretocaptured.put(Integer.parseInt(score10[1]),Integer.parseInt(score10[0]));
        scoretocaptured.put(world.getScore(), world.getGhostsCaptured());

        unsortedmap.put(1,Integer.parseInt(score1[1]));
        unsortedmap.put(2,Integer.parseInt(score2[1]));
        unsortedmap.put(3,Integer.parseInt(score3[1]));
        unsortedmap.put(4,Integer.parseInt(score4[1]));
        unsortedmap.put(5,Integer.parseInt(score5[1]));
        unsortedmap.put(6,Integer.parseInt(score6[1]));
        unsortedmap.put(7,Integer.parseInt(score7[1]));
        unsortedmap.put(8,Integer.parseInt(score8[1]));
        unsortedmap.put(9,Integer.parseInt(score9[1]));
        unsortedmap.put(10,Integer.parseInt(score10[1]));
        unsortedmap.put(11,world.getScore());
        //sort list
        TreeMap<Integer, Integer> sortedMap = SortByValue(unsortedmap);

        //remove the lowest score
        sortedMap.remove(sortedMap.lastKey());
        //assign the values back to the strings
        List<Integer> list = new ArrayList<Integer>(sortedMap.values());
        SplashActivity.hs10 = "" + scoretocaptured.get(list.get(9)) + ";" + list.get(9);
        SplashActivity.hs9 = "" + scoretocaptured.get(list.get(8)) + ";" + list.get(8);
        SplashActivity.hs8 = "" + scoretocaptured.get(list.get(7)) + ";" + list.get(7);
        SplashActivity.hs7 = "" + scoretocaptured.get(list.get(6)) + ";" + list.get(6);
        SplashActivity.hs6 = "" + scoretocaptured.get(list.get(5)) + ";" + list.get(5);
        SplashActivity.hs5 = "" + scoretocaptured.get(list.get(4)) + ";" + list.get(4);
        SplashActivity.hs4 = "" + scoretocaptured.get(list.get(3)) + ";" + list.get(3);
        SplashActivity.hs3 = "" + scoretocaptured.get(list.get(2)) + ";" + list.get(2);
        SplashActivity.hs2 = "" + scoretocaptured.get(list.get(1)) + ";" + list.get(1);
        SplashActivity.hs1 = "" + scoretocaptured.get(list.get(0)) + ";" + list.get(0);

        if (sortedMap.containsKey(11)) {
            GameOverActivity.visibility = true;
            SplashActivity.nhspos = (list.indexOf(world.getScore()))+1;
        }
        gamemusic.stop();
        lose.start();
        Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
        startActivity(intent);
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
            onDestroy();

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
    public void pause(View view) {
        ImageButton pausebutton = (ImageButton)findViewById(R.id.pause);
        if (pause) {
            pausebutton.setImageResource(R.drawable.pause);
            pause = false;
            onResume();
        }
        else if (pause == false) {
            pausebutton.setImageResource(R.drawable.play);
            pause = true;
            onPause();
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
    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                //Set sensor values as acceleration
                yAcceleration = sensorEvent.values[1];
                xAcceleration = sensorEvent.values[2];
                xVelocity += (xAcceleration * frameTime);
                yVelocity += (yAcceleration * frameTime);
                if(xVelocity > 100) {
                    xVelocity = 100;
                }
                if(xVelocity < -100) {
                    xVelocity = -100;
                }
                if(yVelocity > 100) {
                    yVelocity = 100;
                }
                if(yVelocity < -100) {
                    yVelocity = -100;
                }



            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }



}

class ValueComparator implements Comparator<Integer> {

    Map<Integer, Integer> map;

    public ValueComparator(Map<Integer, Integer> base) {
        this.map = base;
    }

    public int compare(Integer a, Integer b) {
        if (map.get(a) >= map.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}