package me.luisorlando.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.luisorlando.Constants;
import me.luisorlando.Listener.bird.BirdColisionListener;
import me.luisorlando.Main;
import me.luisorlando.entity.Floor;
import me.luisorlando.player.Player;

import static me.luisorlando.Main.inputs;

public class GameScreen extends Pantalla {

    private boolean debugBox2d = true;

    private Stage stage;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private Texture texture;

    private FrameRate rate;

    private Player player;

    private int zoom = 3;

    public GameScreen(Main game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        stage.setDebugAll(false);
        inputs.addProcessor(stage);
        world = new World(new Vector2(0, -40), true);
        rate = new FrameRate(world, stage);

        texture = getRecurso("fondo.png");

        player = new Player(world, stage);
        new Floor(world, stage, new Vector2(10, 4.3f));

        if (debugBox2d) {
            renderer = new Box2DDebugRenderer();
            camera = new OrthographicCamera(stage.getWidth() / Constants.PIXELS_IN_METER, stage.getHeight() / Constants.PIXELS_IN_METER);
        }


        world.setGravity(new Vector2(0, -9.81f));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputs);
        world.setContactListener(new BirdColisionListener(player));
        player.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkTeclas();


        stage.getCamera().update();
        rate.update();

        stage.getBatch().begin();
        stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() * 3);
        stage.getBatch().end();

        player.dibujarResortera();

        stage.act();
        world.step(delta, 8, 3);
        stage.draw();

        rate.render();



        if (debugBox2d) {
            camera.update();
            camera.position.x = stage.getCamera().position.x / Constants.PIXELS_IN_METER;
            camera.position.y = stage.getCamera().position.y / Constants.PIXELS_IN_METER;
            renderer.render(world, camera.combined);
        }
    }

    private void checkTeclas() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            player.nextBird();
        }

        if (Gdx.input.justTouched()) {
            System.out.println("Cursor: " + Gdx.input.getX() / Constants.PIXELS_IN_METER + " (" + Gdx.input.getX() + "), " + Gdx.input.getY() / Constants.PIXELS_IN_METER);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        if (debugBox2d) renderer.dispose();
    }
}
