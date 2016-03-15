package com.dsiedlarz.flappymac.Pipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

/**
 * Created by Dawid on 2016-03-11.
 */
public class DoublePipe {
    BottomPipe bp;
    TopPipe tp;
    PlayScreen screen;
    boolean point;

    public DoublePipe(PlayScreen screen, float x,float y,float h){
        this.screen= screen;
        bp = new BottomPipe(screen,x/FlappyMac.PPM,y/FlappyMac.PPM);
        tp = new TopPipe(screen,x/FlappyMac.PPM,(y+h)/FlappyMac.PPM);
        point=true;
    }

    public void setPosition(float x,float y,float h){

       // bp.body.setTransform((x)/FlappyMac.PPM-bp.getX(),(y)/FlappyMac.PPM-bp.getY(),0);
      //  bp.body.setTransform((x-tp.getX())/FlappyMac.PPM-tp.getX(),((y+h)-tp.getY())/FlappyMac.PPM-tp.getY(),0);
        bp.body.setTransform((x)/FlappyMac.PPM,(y)/FlappyMac.PPM,0);
        tp.body.setTransform((x-tp.getX())/FlappyMac.PPM,((y+h))/FlappyMac.PPM,0);

        point=true;
    }

    public float getX(){


        return bp.getX();
    }


    public void draw(SpriteBatch batch){
        bp.draw(batch);
        tp.draw(batch);
    }

    public void update(float delta){
  //     bp.setPosition(1.2f,0f);
        bp.update(delta);


        tp.update(delta);
    }

}
