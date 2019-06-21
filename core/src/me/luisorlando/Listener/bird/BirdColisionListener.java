package me.luisorlando.Listener.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.ArrayList;
import java.util.List;

import me.luisorlando.entity.Entity;
import me.luisorlando.entity.birds.Bird;
import me.luisorlando.entity.birds.BirdType;
import me.luisorlando.entity.enemys.Enemy;
import me.luisorlando.levels.Level;
import me.luisorlando.materials.Material;
import me.luisorlando.materials.MaterialType;
import me.luisorlando.player.Player;

public class BirdColisionListener implements ContactListener {

    public static List<Body> bodyEliminator = new ArrayList<Body>();

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

            Bird b = nivel.getBirdByBody(fixtureA.getUserData().toString().contains("BIRD") ? fixtureA : fixtureB);
            Material material = nivel.getMaterialByBody(fixtureA.getUserData().toString().contains("MATERIAL") ? fixtureA : fixtureB);

            if (b != null && ((Entity) b).isFirstColission()) {
                b.setTextureDañado();
                boolean romperMaterial = material.dañar(((Entity) b).getVelocity());
                if (romperMaterial) {
                    contact.setEnabled(false);
                    ((Entity) b).setFirstColission(true);
                }

                if (b.getType() == BirdType.RED) {
                    if (material.getType() == MaterialType.WOOD) {
                        ((Entity) b).removeVelocity(400f, romperMaterial);
                    } else if (material.getType() == MaterialType.STONE) {
                        ((Entity) b).removeVelocity(600f, romperMaterial);
                    } else if (material.getType() == MaterialType.GLASS) {
                        ((Entity) b).removeVelocity(400f, romperMaterial);
                    }
                } else if (b.getType() == BirdType.YELLOW) {
                    if (material.getType() == MaterialType.WOOD) {
                        ((Entity) b).removeVelocity(200f, romperMaterial);
                    } else if (material.getType() == MaterialType.STONE) {
                        ((Entity) b).removeVelocity(800f, romperMaterial);
                    } else if (material.getType() == MaterialType.GLASS) {
                        ((Entity) b).removeVelocity(600f, romperMaterial);
                    }
                } else if (b.getType() == BirdType.WHITE) {
                    if (material.getType() == MaterialType.WOOD) {
                        ((Entity) b).removeVelocity(800f, romperMaterial);
                    } else if (material.getType() == MaterialType.STONE) {
                        ((Entity) b).removeVelocity(800f, romperMaterial);
                    } else if (material.getType() == MaterialType.GLASS) {
                        ((Entity) b).removeVelocity(800f, romperMaterial);
                    }
                }
            }
        }
        if ((fixtureA.getUserData().toString().contains("ENEMY") && fixtureB.getUserData().toString().contains("BIRD"))
                || (fixtureB.getUserData().toString().contains("ENEMY") && fixtureA.getUserData().toString().contains("BIRD"))) {
            Enemy e = nivel.getEnemyByBody(fixtureA.getUserData().toString().contains("ENEMY") ? fixtureA : fixtureB);
            if (e != null) {
                Bird b = nivel.getBirdByBody(fixtureA.getUserData().toString().contains("BIRD") ? fixtureA : fixtureB);
                e.dañar();

                if (((Entity) b).isFirstColission()) {
                    b.setTextureDañado();
                }
            }
        }
        if ((fixtureA.getUserData().toString().contains("ENEMY") && fixtureB.getUserData().toString().contains("MATERIAL"))
                || (fixtureB.getUserData().toString().contains("ENEMY") && fixtureA.getUserData().toString().contains("MATERIAL"))) {
            Enemy e = nivel.getEnemyByBody(fixtureA.getUserData().toString().contains("ENEMY") ? fixtureA : fixtureB);
            if (e != null) {
                e.dañar();
            }
        }
        if ((fixtureA.getUserData().toString().contains("HUEVO") || fixtureB.getUserData().toString().contains("HUEVO"))) {
            if ((fixtureA.getUserData().toString().contains("ENEMY") || fixtureB.getUserData().toString().contains("ENEMY"))) {
                Enemy e = nivel.getEnemyByBody(fixtureA.getUserData().toString().contains("ENEMY") ? fixtureA : fixtureB);
                if (e != null) {
                    e.dañar(5f);
                }
            }
            if ((fixtureA.getUserData().toString().contains("MATERIAL") || fixtureB.getUserData().toString().contains("MATERIAL"))) {
                Material material = nivel.getMaterialByBody(fixtureA.getUserData().toString().contains("MATERIAL") ? fixtureA : fixtureB);
                if (material != null) {
                    boolean romperMaterial = material.dañar(100);
                    if (romperMaterial) {
                        contact.setEnabled(false);
                    }
                }
            }
        }
        if ((fixtureA.getUserData().toString().contains("FLOOR") && fixtureB.getUserData().toString().contains("BIRD"))
                || (fixtureB.getUserData().toString().contains("FLOOR") && fixtureA.getUserData().toString().contains("BIRD"))) {
            Bird b = nivel.getBirdByBody(fixtureA.getUserData().toString().contains("BIRD") ? fixtureA : fixtureB);
            if (b != null && ((Entity) b).isFirstColission()) {
                b.setTextureDañado();
                ((Entity) b).setFirstColission(true);
            }
        }
        if ((fixtureA.getUserData().toString().contains("ENEMY") && fixtureB.getUserData().toString().contains("FLOOR"))
                || (fixtureB.getUserData().toString().contains("ENEMY") && fixtureA.getUserData().toString().contains("FLOOR"))) {
            Enemy e = nivel.getEnemyByBody(fixtureA.getUserData().toString().contains("ENEMY") ? fixtureA : fixtureB);
            if (e != null) {
                e.dañar(2);
            }
        }


        if (!bodyEliminator.isEmpty()) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    for (Body b : BirdColisionListener.bodyEliminator) {
                        try {
                            b.setTransform(-10f, 0f, 0f);
                        } catch (Exception e) {
                        }
                    }
                }
            });
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
