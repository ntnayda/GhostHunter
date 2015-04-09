package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ntnayda on 4/7/15.
 */

public class World extends View{

    private static String TAG = SplashActivity.class.getName(); // Used to report an error in run()
    private ArrayList<simpleGhost> simpleGhosts;
    private Grandma granny;
    private Bitmap bigghostpic = BitmapFactory.decodeResource(getResources(), R.drawable.simpleghost);
    private Bitmap ghostpic = Bitmap.createScaledBitmap(bigghostpic, 150, 150, false);
    private Bitmap biggrandmapic = BitmapFactory.decodeResource(getResources(), R.drawable.grandma);
    private Bitmap grandmapic = Bitmap.createScaledBitmap(biggrandmapic, 150, 150, false);
    private boolean running;

    public World(Context context) {
        super(context);
        granny = new Grandma();
        simpleGhosts = new ArrayList<simpleGhost>();
        simpleGhost ghost1 = new simpleGhost(200, 200);
        simpleGhosts.add(ghost1);
        running = false;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            canvas.save();
            canvas.drawColor(-16777216);
            float dx = granny.getgNextX() - granny.getgPosX();
            float dy = granny.getgNextY() - granny.getgPosY();
            if (dx > 0) {
                granny.setgPosX(granny.getgPosX() + 1);
            } else if (dx < 0) {
                granny.setgPosX(granny.getgPosX() - 1);
            }

            if (dy > 0) {
                granny.setgPosY(granny.getgPosY() + 1);
            } else if (dy < 0) {
                granny.setgPosY(granny.getgPosY() - 1);
            }
            canvas.drawBitmap(grandmapic, granny.getgPosX() - 75, granny.getgPosY() - 75, null);
            for (simpleGhost current : simpleGhosts) {
                canvas.drawBitmap(ghostpic, current.getPosX() - 75, current.getPosY() - 75, null);
                //current.setHitbox(Rect();
                //if (current.)
            }

            try {
                Thread.sleep(1); // Display (sleep)
            } catch (Exception exep) {
                Log.e(TAG, exep.getMessage());
            }
            canvas.restore();
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            //Finger touches the screen
            case MotionEvent.ACTION_DOWN:
                granny.setgNextX(e.getX());
                granny.setgNextY(e.getY());
                break;
        }
        return true;
    }
}
