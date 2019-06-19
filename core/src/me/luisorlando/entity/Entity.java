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

public class Entity extends Actor {
    protected Texture texture;
    protected TextureRegion textureRegion;
    protected Stage stage;
    protected Body body;
    protected int size = 1;
    protected float x = 0, y = 0, velocity = 0f;
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
        System.out.println("Angulo: " + angle);

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
        System.out.println("Velocidad: " + velocity);
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

    public void removeVelocity(float velocity) {
        this.velocity -= velocity;
        if (this.velocity < 0) this.velocity = 0;
        body.applyForceToCenter(-velocity * 5, 0f, true);
    }
}
