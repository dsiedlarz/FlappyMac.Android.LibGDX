package com.dsiedlarz.flappymac.Environment;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

/**
 * Created by Dawid on 2016-03-11.
 */
public class Environment extends Sprite {
    PlayScreen screen;
    World world;
    Body body;

    public Environment(PlayScreen screen) {
        this.screen=screen;
        this.world=screen.getWorld();




        defineEnvironment();



    }

    void defineEnvironment(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(0,0);
        bdef.type = BodyDef.BodyType.StaticBody;
       body = world.createBody(bdef);
        FixtureDef fdef =new FixtureDef();
        fdef.filter.categoryBits = FlappyMac.ENIVRONMENT_BIT;
        fdef.filter.maskBits=FlappyMac.FLAPPY_BIT;

        PolygonShape down = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(0,0).scl(1/FlappyMac.PPM);
        vertice[1] = new Vector2(0,100).scl(1 / FlappyMac.PPM);
        vertice[2] = new Vector2(FlappyMac.V_WIDTH,100).scl(1 / FlappyMac.PPM);
        vertice[3] = new Vector2(FlappyMac.V_WIDTH,0).scl(1/FlappyMac.PPM);
        down.set(vertice);



        fdef.shape=down;

        body.createFixture(fdef).setUserData(this);

        PolygonShape up = new PolygonShape();
        Vector2[] vertice1 = new Vector2[4];
        vertice1[0] = new Vector2(0,FlappyMac.V_HEIGHT).scl(1/FlappyMac.PPM);
        vertice1[1] = new Vector2(FlappyMac.V_WIDTH,FlappyMac.V_HEIGHT).scl(1/FlappyMac.PPM);
        vertice1[2] = new Vector2(FlappyMac.V_WIDTH,FlappyMac.V_HEIGHT+1).scl(1 / FlappyMac.PPM);
        vertice1[3] = new Vector2(0,FlappyMac.V_HEIGHT+1).scl(1/FlappyMac.PPM);
        up.set(vertice1);

        fdef.shape =up;
        //fdef.restitution =0.5f;
        fdef.filter.categoryBits = FlappyMac.ENIVRONMENT_BIT;
        fdef.filter.maskBits=FlappyMac.FLAPPY_BIT;
        body.createFixture(fdef).setUserData(this);

        body.setGravityScale(0);


    }

    public void update(float delta){

    }


}


