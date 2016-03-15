package com.dsiedlarz.flappymac.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.dsiedlarz.flappymac.FlappyMac;
import com.dsiedlarz.flappymac.Screens.PlayScreen;

/**
 * Created by Dawid on 2016-03-11.
 */
public class ContactListenerW implements ContactListener {
    PlayScreen screen;

    public ContactListenerW(PlayScreen screen) {
        this.screen = screen;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA= contact.getFixtureA();
        Fixture fixB=contact.getFixtureB();

        int cdef =fixA.getFilterData().categoryBits|fixB.getFilterData().categoryBits;

        switch (cdef){case FlappyMac.FLAPPY_BIT | FlappyMac.ENIVRONMENT_BIT:
           case FlappyMac.FLAPPY_BIT|FlappyMac.PIPE_BIT:

                screen.gameOver();
               break;


            }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
