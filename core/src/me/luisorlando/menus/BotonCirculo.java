package me.luisorlando.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.luisorlando.Constants;
import me.luisorlando.Main;

import static me.luisorlando.Constants.PIXELS_IN_METER;

public class BotonCirculo extends Actor implements BotonClickeable {
    private Texture texture;
    private World world;
    private Stage stage;
    private Body body;

    private int size = 3;

    public BotonCirculo(String path, World world, Stage stage, Vector2 position) {
        this.world = world;
        this.stage = stage;
        texture = Main.manager.get(path);
        createBox(position);
        stage.addActor(this);
        registrarEventos();
    }

    private void registrarEventos() {
        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onclick();
                return true;
            }
        });
    }

    public void onclick() {
    }

    private void createBox(Vector2 position) {
        Fixture fixture;
        BodyDef def;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;

        CircleShape circle = new CircleShape();
        circle.setRadius(1.5f);
        setSize(PIXELS_IN_METER * size, PIXELS_IN_METER * size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        body = world.createBody(def);
        fixture = body.createFixture(fixtureDef);
        disableColission(fixture);
    }

    private void disableColission(Fixture fixture) {
        Filter boxBreakFilter = new Filter();
        boxBreakFilter.categoryBits = 1;
        boxBreakFilter.groupIndex = 2;
        boxBreakFilter.maskBits = (short) 0;
        fixture.setFilterData(boxBreakFilter);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x) * Constants.PIXELS_IN_METER - texture.getWidth(), (body.getPosition().y) * Constants.PIXELS_IN_METER - texture.getHeight());
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
