package ntn4jg.cs2110.virginia.edu.ghosthunter;

/**
 * Created by ntnayda on 4/16/15.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BroadcastService  extends Service {
    private static final String TAG = "BroadcastService";
    public static final String BROADCAST_ACTION = "ntn4jg.cs2110.virginia.edu.ghosthunter.displayevent";
    private final Handler handler = new Handler();
    Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();

        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second

    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 1000); // 1 second
        }
    };

    private void DisplayLoggingInfo() {
        //Log.d(TAG, "entered DisplayLoggingInfo");

        intent.putExtra("money", String.valueOf(PlayActivity.money));
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }
}

