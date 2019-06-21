package me.luisorlando.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import me.luisorlando.entity.birds.Bird;
import me.luisorlando.entity.birds.BirdWhite;
import me.luisorlando.screen.GameScreen;

import static me.luisorlando.Listener.bird.BirdColisionListener.bodyEliminator;

public class Entity extends Actor {
    protected Texture texture;
    protected TextureRegion textureRegion;
    protected Stage stage;
    protected Body body;
    protected int size = 1;
    protected float x = 0, y = 0, velocity = 0f, prex, prey;
    protected boolean followMouse = false;
    protected boolean firstColission = true;
    protected boolean flying = false;

    private List<EventListener> listeners;

    public Entity(){
        listeners = new ArrayList<EventListener>();
    }

    public void cambiarPosicion(Vector2 pos) {
        body.setTransform(pos, body.getAngle());
    }

    public void disableMovement(boolean disable) {
        if (disable) {
            body.setType(BodyDef.BodyType.StaticBody);
        } else {
            body.setType(BodyDef.BodyType.DynamicBody);
        }
    }

    public void agregarListener(EventListener ob) {
        listeners.add(ob);
        this.addListener(ob);
    }

    public void setFollowMouse(boolean followMouse) {
        this.followMouse = followMouse;
        if (followMouse) {
            x = getX();
            y = getY();
        }
    }

    public void lanzar() {
        followMouse = false;
        flying = true;
        float angle = (float) Math.toDegrees(Math.atan2(y - getY(), x - getX()));
        if (angle < 0) {
            angle += 360;
        }

        for (EventListener ob : listeners) {
            this.removeListener(ob);
        }

        for (EventListener ob : listeners) {
            this.removeListener(ob);
        }

        firstColission = true;
        disableMovement(false);
        body.applyLinearImpulse(new Vector2((x - getX()) / 10, (y - getY()) / 10), new Vector2(x, y), true);
        velocity = (x - getX());

        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(1500);
                } catch (Exception e) {
                }
                GameScreen.player.nextBird();
                prex = body.getPosition().x;
                prey = body.getPosition().y;
                contadorEliminar();
            }
        }.start();
    }

    public void contadorEliminar() {
        final boolean huevo = ((Bird) this) instanceof BirdWhite;
        final boolean soltoHuevo = huevo ? ((BirdWhite) this).isSoltoHuevo() : false;
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        this.sleep(3000);
                    } catch (Exception e) {
                    }

                    if ((huevo && (soltoHuevo && body.getPosition().y > 100)) || ((prex - 10 < body.getPosition().x && prex + 10 > body.getPosition().x)
                            && (prey - 10 < body.getPosition().y && prey + 10 > body.getPosition().y))) {
                        eliminar();
                        break;
                    }

                    prex = body.getPosition().x;
                    prey = body.getPosition().y;
                }
            }
        }.start();
    }

    public void eliminar() {
        bodyEliminator.add(body);
        this.remove();
        this.setVisible(false);
        GameScreen.player.getNivel().eliminarBird(((Bird) this));
    }

    public Body getBody() {
        return body;
    }

    public boolean isFirstColission() {
        return firstColission;
    }

    public void setFirstColission(boolean firstColission) {
        this.firstColission = firstColission;
    }

    public boolean isFlying() {
        return flying;
    }

    public float getVelocity() {
        return velocity;
    }

    public void removeVelocity(float velocity, boolean blockRoto) {
        this.velocity -= velocity;
        if (this.velocity < 0) this.velocity = 0;
        if (!blockRoto) {
            this.velocity = this.velocity / 50;
        }
        body.applyForceToCenter(-this.velocity * 5, 0f, true);
    }
}
