package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Color;

/**
 * Created by ntnayda on 4/7/15.
 */
public class Grandma {

    private float gPosX;
    private float gPosY;
    private float gNextX;
    private float gNextY;
    private float speed;

    public float getgPosX() {
        return gPosX;
    }

    public void setgPosX(float gPosX) {
        this.gPosX = gPosX;
    }

    public float getgPosY() {
        return gPosY;
    }

    public void setgPosY(float gPosY) {
        this.gPosY = gPosY;
    }

    public float getgNextX() {
        return gNextX;
    }

    public void setgNextX(float gNextX) {
        this.gNextX = gNextX;
    }

    public float getgNextY() {
        return gNextY;
    }

    public void setgNextY(float gNextY) {
        this.gNextY = gNextY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Grandma() {
        gPosX = 1024;
        gPosY = 1024;
        gNextX = 1024;
        gNextY = 1024;
        speed = 3;
    }



}
