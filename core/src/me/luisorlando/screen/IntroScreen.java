package me.luisorlando.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.luisorlando.Constants;
import me.luisorlando.Main;
import me.luisorlando.menus.BotonCirculo;
import me.luisorlando.menus.IntroPlay;

import static me.luisorlando.Main.inputs;

public class IntroScreen extends Pantalla {
    private boolean debugBox2d = false;

    private Stage stage;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private IntroPlay btnPlay;
    private BotonCirculo btnSalir;

    private Texture texture;

    private int zoom = 3;

    public IntroScreen(Main game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        stage.setDebugAll(false);
        inputs.addProcessor(stage);
        world = new World(new Vector2(0, -40), true);

        btnPlay = new IntroPlay(world, stage, new Vector2(25, 15));

        btnSalir = new BotonCirculo("menus/botones/salir.png", world, stage, new Vector2(2, 2)) {
            @Override
            public void onclick() {
                Gdx.app.exit();
            }
        };

        texture = getRecurso("fondo.png");

        if (debugBox2d) {
            renderer = new Box2DDebugRenderer();
            camera = new OrthographicCamera(stage.getWidth() / Constants.PIXELS_IN_METER, stage.getHeight() / Constants.PIXELS_IN_METER);
        }

        world.setGravity(new Vector2(0, -9.81f));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputs);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getCamera().update();

        stage.getBatch().begin();
        stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() * 3);
        stage.getBatch().end();

        stage.act();
        world.step(delta, 8, 3);
        stage.draw();

        if (debugBox2d) {
            camera.update();
            camera.position.x = stage.getCamera().position.x / Constants.PIXELS_IN_METER;
            camera.position.y = stage.getCamera().position.y / Constants.PIXELS_IN_METER;
            renderer.render(world, camera.combined);
        }

        if (btnPlay.clickeoJugar) {
            this.dispose();
            game.openLevelSelector();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        if (debugBox2d) renderer.dispose();
    }
}
