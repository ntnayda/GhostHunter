package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

/**
 * Created by ntnayda on 4/7/15.
 */
public class simpleGhost{

    private float posX;
    private float posY;
    private float nextX;
    private float nextY;
    private float speed;

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getNextX() {
        return nextX;
    }

    public void setNextX(float nextX) {
        this.nextX = nextX;
    }

    public float getNextY() {
        return nextY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setNextY(float nextY) {
        this.nextY = nextY;
    }

    public simpleGhost() {
        Random r = new Random();
        speed = 2;
        posX = r.nextInt(2048);
        posY = r.nextInt(2048);
        nextX = posX;
        nextY = posY;
    }

}
