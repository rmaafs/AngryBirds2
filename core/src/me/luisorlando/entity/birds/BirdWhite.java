package me.luisorlando.entity.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Constants;
import me.luisorlando.Main;
import me.luisorlando.entity.Entity;

import static me.luisorlando.Constants.PIXELS_IN_METER;

public class BirdWhite extends Entity implements Bird {
    private BirdType type = BirdType.WHITE;

    public BirdWhite(Stage stage, World world, Vector2 position) {
        texture = Main.manager.get("birds/white.png");
        createBox(world, stage, position);
    }

    public void startDraw() {
        stage.addActor(this);
    }

    public void setTextureVolando() {
        textureRegion = new TextureRegion(texture, 79, 0, 80, 93);
    }

    public void setTextureDañado() {
        textureRegion = new TextureRegion(texture, 240, 0, 80, 93);
    }

    private void createBox(World world, Stage stage, Vector2 position) {
        this.stage = stage;
        Fixture fixture;
        BodyDef def;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;

        CircleShape circle = new CircleShape();
        circle.setRadius(1f);
        textureRegion = new TextureRegion(texture, 0, 0, 80, 93);
        setSize(PIXELS_IN_METER * size * 1.7f, PIXELS_IN_METER * size * 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        body = world.createBody(def);
        fixture = body.createFixture(fixtureDef);
        body.setUserData("BIRD_" + type.toString());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (followMouse) {
            setPosition(Gdx.input.getX() * 3 - getWidth() / 2, (Gdx.graphics.getHeight() - Gdx.input.getY()) * 3 - getHeight() / 2);
            cambiarPosicion(new Vector2((getX() + getWidth() / 2) / PIXELS_IN_METER, (getY() + getHeight() / 2) / PIXELS_IN_METER));
        } else {
            setPosition((body.getPosition().x + 0.35f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.5f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        }
        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }
}
