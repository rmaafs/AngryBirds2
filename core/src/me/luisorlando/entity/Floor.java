package me.luisorlando.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Floor extends Actor {

    private Body body;

    public Floor(World world, Stage stage, Vector2 position) {
        Fixture fixture;
        BodyDef def;
        PolygonShape shape;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        shape = new PolygonShape();

        shape.setAsBox(40, 2);

        body = world.createBody(def);
        fixture = body.createFixture(shape, 1);
        body.setUserData("FLOOR");

        stage.addActor(this);
        //Este es el suelo
    }
}
