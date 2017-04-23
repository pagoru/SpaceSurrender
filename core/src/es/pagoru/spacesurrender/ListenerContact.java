package es.pagoru.spacesurrender;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.Random;

/**
 * Created by Pablo on 23/04/17.
 */

public class ListenerContact implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        System.out.println(contact.getFixtureA().getDensity() + "-" + contact.getFixtureB().getDensity());
        SpaceSurrender.batman.addMoney(randInt(100, 500));
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

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
};