package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by ntnayda on 4/7/15.
 */
public class simpleGhost{

    private float posX;
    private float posY;
    private float nextX;
    private float nextY;
    private Rect hitbox;

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

    public Rect getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }

    public simpleGhost(float x, float y) {
        posX = x;
        posY = y;
        nextX = x;
        nextY = y;
        hitbox = new Rect((int) posX,(int) posX+150,(int) posY,(int) posY+150);
    }

}
