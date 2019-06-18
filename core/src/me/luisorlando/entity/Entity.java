package me.luisorlando.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Entity extends Actor {
    protected Texture texture;
    protected TextureRegion textureRegion;
    protected Stage stage;
    protected Body body;
    protected int size = 1;
    protected float x = 0, y = 0;
    protected boolean followMouse = false;

    public Entity(){

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

    public void addListener(Object ob) {
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
        float angle = (float) Math.toDegrees(Math.atan2(y - getY(), x - getX()));
        if (angle < 0) {
            angle += 360;
        }
        System.out.println("Angulo: " + angle);
        disableMovement(false);
        body.applyLinearImpulse(new Vector2((x - getX()) / 10, (y - getY()) / 10), new Vector2(x, y), true);
    }
}
