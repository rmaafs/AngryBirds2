package me.luisorlando.Listener.bird;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import me.luisorlando.entity.birds.Bird;
import me.luisorlando.levels.Level;
import me.luisorlando.player.Player;

public class BirdColisionListener implements ContactListener {

    private Player player;
    private Level nivel;

    public BirdColisionListener(Player player) {
        this.player = player;
        this.nivel = player.getNivel();
    }

    @Override
    public void beginContact(Contact contact) {
        Body fixtureA = contact.getFixtureA().getBody();
        Body fixtureB = contact.getFixtureB().getBody();

        if ((fixtureA.getUserData().toString().contains("BIRD") && fixtureB.getUserData().toString().contains("MATERIAL"))
                || (fixtureB.getUserData().toString().contains("BIRD") && fixtureA.getUserData().toString().contains("MATERIAL"))) {
            System.out.println("COLISIONANDO - ");

            Bird b = nivel.getBirdByBody(fixtureA.getUserData().toString().contains("BIRD") ? fixtureA : fixtureB);
            b.setTextureDañado();
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
