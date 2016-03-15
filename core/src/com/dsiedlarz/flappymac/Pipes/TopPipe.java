package com.dsiedlarz.flappymac.Pipes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

/**
 * Created by Dawid on 2016-03-11.
 */
public class TopPipe extends Pipe {
    float y;
    public TopPipe(PlayScreen screen, float x, float y) {

        super(screen, x, y);
        this.y=y;
        setRegion(screen.getAtlas().findRegion("Atlas"), 110, 646, 54, 320);
    }
    //966,218
    @Override
    public void update(float dt) {
        super.update(dt);

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y );

        body.setLinearVelocity(velocity);
    }
    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fdef =new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-27,320).scl(1/ FlappyMac.PPM);
        vertice[1] = new Vector2(-27,0).scl(1/FlappyMac.PPM);
        vertice[2] = new Vector2(27,0).scl(1/FlappyMac.PPM);
        vertice[3] = new Vector2(27,320).scl(1/FlappyMac.PPM);

        fdef.filter.categoryBits=FlappyMac.PIPE_BIT;
        fdef.filter.maskBits= FlappyMac.FLAPPY_BIT;
        shape.set(vertice);

        fdef.shape=shape;
        body.createFixture(fdef).setUserData(this);


    }

    public void draw(Batch batch){

        super.draw(batch);



    }

}