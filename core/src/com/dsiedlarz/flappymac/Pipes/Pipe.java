package com.dsiedlarz.flappymac.Pipes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;
import com.dsiedlarz.flappymac.Sprites.Flappy;

/**
 * Created by Dawid on 2016-03-11.
 */
public abstract class Pipe extends Sprite {

    protected PlayScreen screen;
    protected World world;
    protected boolean toDeestroy;
    protected boolean destroyed;
    protected Body body;
    protected Vector2 velocity;

    public Pipe(PlayScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        setBounds(getX(), getY(), 58 / FlappyMac.PPM, 320 / FlappyMac.PPM);
        defineItem();
        toDeestroy = false;
        destroyed = false;
        body.setGravityScale(0);
        velocity= new Vector2(0,0);

    }


    public abstract void defineItem();

    public void update(float dt) {
        velocity.x=((screen.getState()== PlayScreen.State.START  ||
                screen.getState()== PlayScreen.State.GAME_OVER)
                ?0:-1.5f);


    }

    public void draw(Batch batch) {
        if (!destroyed) {
            super.draw(batch);
        }

    }
}
