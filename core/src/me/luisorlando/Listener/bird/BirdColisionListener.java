package me.luisorlando.Listener.bird;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import me.luisorlando.entity.Entity;
import me.luisorlando.entity.birds.Bird;
import me.luisorlando.entity.birds.BirdType;
import me.luisorlando.levels.Level;
import me.luisorlando.materials.Material;
import me.luisorlando.materials.MaterialType;
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

            final Bird b = nivel.getBirdByBody(fixtureA.getUserData().toString().contains("BIRD") ? fixtureA : fixtureB);
            final Material material = nivel.getMaterialByBody(fixtureA.getUserData().toString().contains("MATERIAL") ? fixtureA : fixtureB);

            if (((Entity) b).isFirstColission()) {
                b.setTextureDañado();
                final boolean romperMaterial = material.dañar(((Entity) b).getVelocity());
                if (romperMaterial) {
                    contact.setEnabled(false);
                    ((Entity) b).setFirstColission(true);
                }

                if (b.getType() == BirdType.RED) {
                    if (material.getType() == MaterialType.WOOD) {
                        ((Entity) b).removeVelocity(400f);
                    } else if (material.getType() == MaterialType.STONE) {
                        ((Entity) b).removeVelocity(600f);
                    } else if (material.getType() == MaterialType.GLASS) {
                        ((Entity) b).removeVelocity(400f);
                    }
                } else if (b.getType() == BirdType.YELLOW) {
                    if (material.getType() == MaterialType.WOOD) {
                        ((Entity) b).removeVelocity(200f);
                    } else if (material.getType() == MaterialType.STONE) {
                        ((Entity) b).removeVelocity(800f);
                    } else if (material.getType() == MaterialType.GLASS) {
                        ((Entity) b).removeVelocity(600f);
                    }
                } else if (b.getType() == BirdType.WHITE) {
                    if (material.getType() == MaterialType.WOOD) {
                        ((Entity) b).removeVelocity(800f);
                    } else if (material.getType() == MaterialType.STONE) {
                        ((Entity) b).removeVelocity(800f);
                    } else if (material.getType() == MaterialType.GLASS) {
                        ((Entity) b).removeVelocity(800f);
                    }
                }

                new Thread() {
                    @Override
                    public void run() {
                        if (romperMaterial) {
                            material.eliminar(player.getWorld());
                        }
                        player.nextBird();
                    }
                }.start();
            }
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
