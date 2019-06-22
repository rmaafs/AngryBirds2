package me.luisorlando.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.luisorlando.Constants;
import me.luisorlando.Main;

import static me.luisorlando.Constants.PIXELS_IN_METER;

public class BotonLevel extends Actor implements BotonClickeable {
    private Texture texture;
    private World world;
    private Stage stage;
    private Body body;
    private int level;

    private int size = 2;
    private float paddingX;//Recorrer en la coordenada X una ligera cantidad.
    private float paddingLetra;//Recorrer el número del nivel en ciertas coordenadas.

    public BotonLevel(String path, int level, World world, Stage stage, Vector2 position, float paddingX, float paddingLetra) {
        this.world = world;
        this.stage = stage;
        this.level = level;
        this.paddingX = paddingX;
        this.paddingLetra = paddingLetra;
        texture = Main.manager.get(path);
        createBox(position);
        stage.addActor(this);
        addHologram(position);
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
        this.stage = stage;
        Fixture fixture;
        BodyDef def;
        PolygonShape shape;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        shape = new PolygonShape();

        shape.setAsBox(size, size);
        setSize(PIXELS_IN_METER * size * 2.35f, PIXELS_IN_METER * size * 2.2f);

        body = world.createBody(def);
        fixture = body.createFixture(shape, 1);
    }

    private void addHologram(Vector2 position) {
        Label label = new Label("" + level, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        label.setFontScale(5f);
        label.setPosition((position.x - paddingLetra) * PIXELS_IN_METER, position.y * PIXELS_IN_METER);
        stage.addActor(label);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - paddingX) * Constants.PIXELS_IN_METER - texture.getWidth(), (body.getPosition().y - 0.3f) * Constants.PIXELS_IN_METER - texture.getHeight());
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
