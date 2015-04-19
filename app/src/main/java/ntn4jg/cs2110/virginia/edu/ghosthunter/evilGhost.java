package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by ntnayda on 4/13/15.
 */
public class evilGhost {

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

    public void setNextY(float nextY) {
        this.nextY = nextY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public evilGhost(float x, float y) {
        Random r = new Random();
        speed = (float) (.5 + r.nextFloat());
        posX = r.nextInt(2048);
        posY = r.nextInt(2048);
        while(posX > x-200 && posX < x+350 && posY > y-200 && posY < y+350)
        {
            posX = r.nextInt(2048);
            posY = r.nextInt(2048);
        }
        nextX = posX;
        nextY = posY;
    }
}
