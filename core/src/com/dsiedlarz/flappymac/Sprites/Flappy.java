package com.dsiedlarz.flappymac.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

/**
 * Created by Dawid on 2016-03-11.
 */
public class Flappy extends Sprite {
    private PlayScreen screen;
    public enum State {FALLING,JUMPING,DEAD}
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;

    private Animation flappyJump;
    private TextureRegion flappyFall;
    private Animation flappyDead;
    private boolean notStarted;



    private float stateTimer;

    public boolean isNotStarted() {
        return notStarted;
    }

    public void setNotStarted(boolean notStarted) {
        this.notStarted = notStarted;
    }

    public Flappy(PlayScreen screen) {
        this.screen=screen;
        this.world=screen.getWorld();
        currentState = State.FALLING;
        previousState= State.FALLING;
        stateTimer =0;

        //6,40,982,1006
        flappyFall = new TextureRegion(screen.getAtlas().findRegion("Atlas"),6,982,34,28);
        setRegion(flappyFall);


        defineFlappy();

        setBounds(0, 0, 32 / FlappyMac.PPM, 30 / FlappyMac.PPM);

        notStarted=true;

    }

    void defineFlappy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / FlappyMac.PPM, 250 / FlappyMac.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef =new FixtureDef();
        fdef.filter.categoryBits=FlappyMac.FLAPPY_BIT;
        fdef.filter.maskBits= FlappyMac.PIPE_BIT|
        FlappyMac.ENIVRONMENT_BIT;
        CircleShape shape = new CircleShape();
        shape.setRadius(16 / FlappyMac.PPM);


        fdef.shape=shape;
        b2body.createFixture(fdef).setUserData(this);
        b2body.setGravityScale(0);
       //b2body.setLinearVelocity(2,0 );




    }

    public void update(float dt) {

        b2body.setGravityScale(screen.getState()==PlayScreen.state.START?0:1.5f);
        b2body.setActive(true);
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

    }

    TextureRegion getFrame(float delta){


        return flappyFall;
    }



}
