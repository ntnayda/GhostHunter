package ntn4jg.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ntnayda on 4/7/15.
 */

public class World extends View{

    private ArrayList<simpleGhost> simpleGhosts;
    private ArrayList<evilGhost> evilGhosts;
    private Grandma granny;
    private Bitmap bigghostpic = BitmapFactory.decodeResource(getResources(), R.drawable.simpleghost);
    private Bitmap ghostpic = Bitmap.createScaledBitmap(bigghostpic, 150, 150, false);
    private Bitmap biggrandmapic = BitmapFactory.decodeResource(getResources(), R.drawable.grandma);
    private Bitmap grandmapic = Bitmap.createScaledBitmap(biggrandmapic, 150, 150, false);
    private Bitmap bigbackround = BitmapFactory.decodeResource(getResources(), R.drawable.backround2);//source:http://hsto.org/storage2/d8c/f89/4bd/d8cf894bdade75b9db47ff2cd83f68f8.png
    private Bitmap backround = Bitmap.createScaledBitmap(bigbackround, 2048, 2048, false);
    private Bitmap bigevilghost = BitmapFactory.decodeResource(getResources(), R.drawable.evilghost1);
    private Bitmap evilghost = Bitmap.createScaledBitmap(bigevilghost, 150, 150, false);
    private int ghostsCaptured;

    /*private Bitmap bomb = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
    private Bitmap coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin1);
    private Bitmap magnet = BitmapFactory.decodeResource(getResources(), R.drawable.magnet);
    private Bitmap pause = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
    private Bitmap rocket = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
    private Bitmap spray = BitmapFactory.decodeResource(getResources(), R.drawable.spray);
    private Bitmap superman = BitmapFactory.decodeResource(getResources(), R.drawable.superman);*/
    private final int defaulttranslatex;
    private final int defaulttranslatey;
    private int translatex;
    private int translatey;
    public int screenWidth;
    public int screenHeight;
    private int boundSize = 100;
    double time=0;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public World(Context context, int screenWidth,int screenHeight){//}, PlayActivity play) {
        super(context);
        bigghostpic =null;
        bigbackround=null;
        bigevilghost=null;
        biggrandmapic=null;
        ghostsCaptured=0;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        granny = new Grandma();
        evilGhosts = new ArrayList<evilGhost>();
        simpleGhosts = new ArrayList<simpleGhost>();
        for(int i = 0;i<20;i++)
        {
            simpleGhost ghost1 = new simpleGhost();
            simpleGhosts.add(ghost1);
        }

        defaulttranslatex = -(1024 - (screenWidth/2));
        defaulttranslatey = -(1024 - (screenHeight/2));
        translatex = defaulttranslatex;
        translatey = defaulttranslatey;
        evilGhosts.add(new evilGhost(granny.getgPosX(), granny.getgPosY()));
        System.gc();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        time++;
        canvas.save();
        moveScreen(canvas, (int) granny.getgPosX(), (int) granny.getgPosY());
        canvas.translate(translatex, translatey);
        createEvilGhosts(time);
        if (PlayActivity.bomb){
            detonate();
        }
        moveGrandma();
        moveGhost();
        moveEvilGhosts();
        checkGameOver();
        removeCaptured();
        drawObjects(canvas);
        PlayActivity.money++;
        canvas.restore();
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            //Finger touches the screen
            case MotionEvent.ACTION_DOWN:
                granny.setgNextX(e.getX()-translatex);
                granny.setgNextY(e.getY()-translatey);
                if (granny.getgNextX() > 2048) {
                    granny.setgNextX(2048);
                }
                if (granny.getgNextX() < 0) {
                    granny.setgNextX(0);
                }
                if (granny.getgNextY() > 2048) {
                    granny.setgNextY(2048);
                }
                if (granny.getgNextY() < 0) {
                    granny.setgNextY(0);
                }
                break;
        }
        return true;
    }

    public void moveGrandma() {
        if (PlayActivity.rocket){
            granny.setSpeed(6);
        }
        else granny.setSpeed(3);
        float dx = granny.getgNextX() - granny.getgPosX();
        float dy = granny.getgNextY() - granny.getgPosY();
        if (dx > granny.getSpeed()) {
            granny.setgPosX(granny.getgPosX() + granny.getSpeed());
        } else if (dx < -granny.getSpeed()) {
            granny.setgPosX(granny.getgPosX() - granny.getSpeed());
        }
        else if (dx >-granny.getSpeed() && dx < granny.getSpeed()) {
            granny.setgPosX(granny.getgNextX());
        }

        if (dy > granny.getSpeed()) {
            granny.setgPosY(granny.getgPosY() + granny.getSpeed());
        } else if (dy < -granny.getSpeed()) {
            granny.setgPosY(granny.getgPosY() - granny.getSpeed());
        }
        else if (dy < granny.getSpeed() && dy > -granny.getSpeed()) {
            granny.setgPosY(granny.getgNextY());
        }
    }
    public void moveScreen(Canvas canvas, int x, int y) {
        if (x > (1024 + screenWidth/4)){
            translatex = defaulttranslatex + ((1024 + screenWidth/4)-x);
        }
        if (x < (1024 - screenWidth/4)){
            translatex = defaulttranslatex - (x-(1024 - screenWidth/4));
        }
        if (y < (1024 - screenHeight/4)){
            translatey = defaulttranslatey -(y-(1024 - screenHeight/4));
        }
        if (y > (1024 + screenHeight/4)){
            translatey = defaulttranslatey + ((1024 + screenHeight/4)-y);
        }
        if (translatex > 0) {
            translatex = 0;
        }
        if (translatey > 0) {
            translatey = 0;
        }
        if (translatex < defaulttranslatex-screenHeight/2) {
            translatex = defaulttranslatex-screenHeight/2;
        }
        if (translatey < defaulttranslatey-screenWidth/2) {
            translatey = defaulttranslatey-screenWidth/2;
        }
    }

    public void moveGhost()
    {
        if (PlayActivity.attract) {
            attract();
        }
        else {
            for (simpleGhost current : simpleGhosts) {
                float dx = current.getNextX() - current.getPosX();
                float dy = current.getNextY() - current.getPosY();
                Random r = new Random();
                if (Math.abs(dx) < 50) {
                    current.setNextX(r.nextInt(2048));
                    dx = current.getNextX() - current.getPosX();
                }
                if (Math.abs(dy) < 50) {
                    current.setNextY(r.nextInt(2048));
                    dy = current.getNextY() - current.getPosY();
                }
                if (dx > current.getSpeed()) {
                    current.setPosX(current.getPosX() + current.getSpeed());
                } else if (dx < -current.getSpeed()) {
                    current.setPosX(current.getPosX() - current.getSpeed());
                } else if (dx > -current.getSpeed() && dx < current.getSpeed()) {
                    current.setPosX(current.getNextX());
                }

                if (dy > current.getSpeed()) {
                    current.setPosY(current.getPosY() + current.getSpeed());
                } else if (dy < -current.getSpeed()) {
                    current.setPosY(current.getPosY() - current.getSpeed());
                } else if (dy < current.getSpeed() && dy > -current.getSpeed()) {
                    current.setPosY(current.getNextY());
                }
            }
        }

    }
    public void drawObjects(Canvas canvas) {
        canvas.drawBitmap(backround,0,0,null);
        for (evilGhost current : evilGhosts) {
            canvas.drawBitmap(evilghost, current.getPosX() - 75, current.getPosY() - 75, null);
        }
        for (simpleGhost current : simpleGhosts) {
            canvas.drawBitmap(ghostpic, current.getPosX() - 75, current.getPosY() - 75, null);
        }
        canvas.drawBitmap(grandmapic, granny.getgPosX() - 75, granny.getgPosY() - 75, null);
    }

    public void removeCaptured() {
        ArrayList<Integer> remove = new ArrayList<Integer>();
        float x = granny.getgPosX();
        float y = granny.getgPosY();
        for (int b=0; b < simpleGhosts.size(); b++) {
            float gx = simpleGhosts.get(b).getPosX();
            float gy = simpleGhosts.get(b).getPosY();
            if (((x > gx)&&(x < (gx + boundSize))&&(y > gy)&&(y < (gy+boundSize)))||(((x+boundSize) > gx)&&((x+boundSize) < (gx + boundSize))&&(y > gy)&&(y < (gy+boundSize)))||((x > gx)&&(x < (gx + boundSize))&&((y+boundSize) > gy)&&((y+boundSize) < (gy+boundSize)))||(((x+boundSize) > gx)&&((x+boundSize) < (gx + boundSize))&&((y+boundSize) > gy)&&((y+boundSize) < (gy+boundSize)))) {
                remove.add(b);
            }
        }
        for (int i = 0; i < remove.size(); i++) {
            simpleGhosts.remove(remove.get(i)-i);
            PlayActivity.money += 100;
            ghostsCaptured++;
            simpleGhost spawn = new simpleGhost();
            simpleGhosts.add(spawn);
        }

    }
    public void checkGameOver() {
        if (PlayActivity.invincible==false) {
            float x = granny.getgPosX();
            float y = granny.getgPosY();
            for (evilGhost current : evilGhosts) {
                float gx = current.getPosX();
                float gy = current.getPosY();
                if (((x > gx) && (x < (gx + boundSize)) && (y > gy) && (y < (gy + boundSize))) || (((x + boundSize) > gx) && ((x + boundSize) < (gx + boundSize)) && (y > gy) && (y < (gy + boundSize))) || ((x > gx) && (x < (gx + boundSize)) && ((y + boundSize) > gy) && ((y + boundSize) < (gy + boundSize))) || (((x + boundSize) > gx) && ((x + boundSize) < (gx + boundSize)) && ((y + boundSize) > gy) && ((y + boundSize) < (gy + boundSize)))) {
                    PlayActivity.setRunning(false);
                }
            }
        }
    }

    public void moveEvilGhosts() {
        if (PlayActivity.repel) {
            repel();
        }
        else {
            for (evilGhost current : evilGhosts) {
                current.setNextY(granny.getgPosY() + 75);
                current.setNextX(granny.getgPosX() + 75);
                float dx = current.getNextX() - current.getPosX();
                float dy = current.getNextY() - current.getPosY();
                if (dx > current.getSpeed()) {
                    current.setPosX(current.getPosX() + current.getSpeed());
                } else if (dx < -current.getSpeed()) {
                    current.setPosX(current.getPosX() - current.getSpeed());
                } else if (dx > -current.getSpeed() && dx < current.getSpeed()) {
                    current.setPosX(current.getNextX());
                }

                if (dy > current.getSpeed()) {
                    current.setPosY(current.getPosY() + current.getSpeed());
                } else if (dy < -current.getSpeed()) {
                    current.setPosY(current.getPosY() - current.getSpeed());
                } else if (dy < current.getSpeed() && dy > -current.getSpeed()) {
                    current.setPosY(current.getNextY());
                }
            }
        }
    }
    public void createEvilGhosts(double time) {
        if (time%1000==0) {
            evilGhost evilGhost1 = new evilGhost(granny.getgPosX(),granny.getgPosY());
            evilGhosts.add(evilGhost1);
        }
    }

    public void detonate()
    {
        ArrayList<Integer> removes = new ArrayList<Integer>();
        for(int i=0;i<evilGhosts.size();i++)
        {
            if (Math.abs(granny.getgPosX()-evilGhosts.get(i).getPosX())<=500&&Math.abs(granny.getgPosY()-evilGhosts.get(i).getPosY())<=500) {
                removes.add(i);
            }
        }
        for(int i=0; i<removes.size();i++){
            evilGhosts.remove(removes.get(i)-i);
        }
    }

    public void repel()
    {
        for (evilGhost current: evilGhosts) {
            if(granny.getgPosX() > current.getPosX())
            {
                current.setNextX(current.getPosX() - (granny.getgPosX() - current.getPosX()));
            }
            else
            {
                current.setNextX(current.getPosX()-(current.getPosX()-granny.getgPosX()));
            }
            if(granny.getgPosY() > current.getPosY())
            {
                current.setNextY(current.getPosY() - (granny.getgPosY() - current.getPosY()));
            }
            else
            {
                current.setNextY(current.getPosY() - (current.getPosY() - granny.getgPosY()));
            }
            float dx = current.getNextX() - current.getPosX();
            float dy = current.getNextY() - current.getPosY();
            if (dx > current.getSpeed()) {
                current.setPosX(current.getPosX() + current.getSpeed());
            } else if (dx < -current.getSpeed()) {
                current.setPosX(current.getPosX() - current.getSpeed());
            }
            else if (dx >-current.getSpeed() && dx < current.getSpeed()) {
                current.setPosX(current.getNextX());
            }

            if (dy > current.getSpeed()) {
                current.setPosY(current.getPosY() + current.getSpeed());
            } else if (dy < -current.getSpeed()) {
                current.setPosY(current.getPosY() - current.getSpeed());
            }
            else if (dy < current.getSpeed() && dy > -current.getSpeed()) {
                current.setPosY(current.getNextY());
            }
        }
    }

    public void attract()
    {
        for (simpleGhost current: simpleGhosts) {
            current.setNextY(granny.getgPosY()+75);
            current.setNextX(granny.getgPosX()+75);
            float dx = current.getNextX() - current.getPosX();
            float dy = current.getNextY() - current.getPosY();
            if (dx > current.getSpeed()) {
                current.setPosX(current.getPosX() + current.getSpeed());
            } else if (dx < -current.getSpeed()) {
                current.setPosX(current.getPosX() - current.getSpeed());
            }
            else if (dx >-current.getSpeed() && dx < current.getSpeed()) {
                current.setPosX(current.getNextX());
            }

            if (dy > current.getSpeed()) {
                current.setPosY(current.getPosY() + current.getSpeed());
            } else if (dy < -current.getSpeed()) {
                current.setPosY(current.getPosY() - current.getSpeed());
            }
            else if (dy < current.getSpeed() && dy > -current.getSpeed()) {
                current.setPosY(current.getNextY());
            }
        }
    }



}
