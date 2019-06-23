package me.luisorlando.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.luisorlando.Constants;
import me.luisorlando.Main;

import static me.luisorlando.Constants.PIXELS_IN_METER;

public class WinScreen extends Actor {
    private Texture texture;
    private Stage stage;
    private World world;
    private Body body;
    private int size = 10;
    private ClickListener clickListener;

    public WinScreen(World world, Stage stage, Vector2 position, Main game) {
        this.world = world;
        this.stage = stage;
        texture = Main.manager.get("menus/win.png");
        createBox(position);
        stage.addActor(this);
        registrarEventos(game);
    }

    private void registrarEventos(final Main game) {
        clickListener = new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                removerEventos();
                game.openLevelSelector();
                return true;
            }
        };
        this.addListener(clickListener);
    }

    private void removerEventos() {
        this.removeListener(clickListener);
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

        shape.setAsBox(size * 1.5f, size);
        setSize(PIXELS_IN_METER * size * 3, PIXELS_IN_METER * size * 2);

        body = world.createBody(def);
        fixture = body.createFixture(shape, 1);
        disableColission(fixture);
        body.setUserData("SCREEN_WIN");
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
        setPosition((body.getPosition().x - 7f) * Constants.PIXELS_IN_METER - texture.getWidth(), (body.getPosition().y - 5f) * Constants.PIXELS_IN_METER - texture.getHeight());
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
