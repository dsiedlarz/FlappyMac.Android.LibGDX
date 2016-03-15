package com.dsiedlarz.flappymac.Pipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Sceenes.Hud;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dawid on 2016-03-11.
 */
public class Pipes {
    Random generator;
    PlayScreen screen;
    LinkedList<DoublePipe> list = new LinkedList<DoublePipe>();


    public Pipes(PlayScreen screen){
        this.screen = screen;
        generator = new Random();
        list.add(new DoublePipe(screen,600+generator.nextFloat()*10,50+generator.nextFloat()*100,300+generator.nextFloat()*50));
        list.add(new DoublePipe(screen,800+generator.nextFloat()*10,50+generator.nextFloat()*100,300+generator.nextFloat()*50));
        list.add(new DoublePipe(screen,1000+generator.nextFloat()*10,50+generator.nextFloat()*100,300+generator.nextFloat()*50));
    }


    public void update(float delta){
        pipeSystem(delta);
//        for(DoublePipe dp:list){
//            dp.update(delta);
//        }




    }

    public void render(SpriteBatch batch){
        for(DoublePipe dp:list){
            dp.draw(batch);
        }

    }

    public void pipeSystem(float delta){
        for(DoublePipe dp:list){
            if(dp.getX()<100/ FlappyMac.PPM&&dp.point){
                Hud.addScore(1);
                dp.point=false;
            }

            if(dp.getX()<-50/FlappyMac.PPM){

                dp.setPosition(600+generator.nextFloat()*10,50+generator.nextFloat()*100,250+generator.nextFloat()*50);
            }


            dp.update(delta);
        }


    }


        public void stop(){








    }




}
