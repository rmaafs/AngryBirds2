package me.luisorlando.entity.enemys;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Main;

import static me.luisorlando.Constants.PIXELS_IN_METER;

public class EnemyCreator extends Enemy {

    public EnemyCreator(EnemyType type, Stage stage, World world, Vector2 position) {
        this.type = type;
        if (type == EnemyType.NORMAL) {
            texture = Main.manager.get("enemigos/normal.png");
            vida = 50f;
        } else if (type == EnemyType.CASCO) {
            texture = Main.manager.get("enemigos/casco.png");
            vida = 200f;
        } else if (type == EnemyType.BIGOTE) {
            texture = Main.manager.get("enemigos/bigote.png");
            vida = 100f;
        }

        createBox(world, stage, position);
    }

    private void createBox(World world, Stage stage, Vector2 position) {
        this.stage = stage;
        Fixture fixture;
        BodyDef def;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;

        CircleShape circle = new CircleShape();

        if (type == EnemyType.NORMAL) {
            circle.setRadius(1f);
            textureRegion = new TextureRegion(texture, 0, 0, 98, 97);
            setSize(PIXELS_IN_METER * size * 2, PIXELS_IN_METER * size * 2);
        } else if (type == EnemyType.CASCO) {
            circle.setRadius(1f);
            textureRegion = new TextureRegion(texture, 0, 0, 93, 83);
            setSize(PIXELS_IN_METER * size * 2, PIXELS_IN_METER * size * 2);
        } else if (type == EnemyType.BIGOTE) {
            circle.setRadius(1.5f);
            textureRegion = new TextureRegion(texture, 0, 0, 109, 99);
            setSize(PIXELS_IN_METER * size * 3, PIXELS_IN_METER * size * 3);
        }

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        body = world.createBody(def);
        fixture = body.createFixture(fixtureDef);
        body.setUserData("ENEMY_" + type.toString());
    }

}
