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
import me.luisorlando.levels.Level1;
import me.luisorlando.menus.BotonLevel;

import static me.luisorlando.Main.inputs;

public class LevelSelectorScreen extends Pantalla {
    private boolean debugBox2d = false;

    private Stage stage;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Texture texture;

    private BotonLevel btnLevel1, btnLevel2, btnLevel3, btnLevel4, btnLevel5, btnLevel6;

    private int zoom = 3;

    public LevelSelectorScreen(final Main game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        stage.setDebugAll(false);
        inputs.addProcessor(stage);
        world = new World(new Vector2(0, -40), true);
        texture = getRecurso("menus/levelselector.png");


        btnLevel1 = new BotonLevel("menus/botones/level.png", 1, world, stage, new Vector2(5, 27), 0.9f, 0.4f) {
            @Override
            public void onclick() {
                game.openGame(new Level1());
            }
        };

        btnLevel2 = new BotonLevel("menus/botones/level.png", 2, world, stage, new Vector2(11, 27), 0.5f, -0.1f) {
            @Override
            public void onclick() {
                System.out.println("JE");
            }
        };

        btnLevel3 = new BotonLevel("menus/botones/level.png", 3, world, stage, new Vector2(17, 27), 0.2f, -0.3f) {
            @Override
            public void onclick() {
                System.out.println("JE");
            }
        };

        btnLevel4 = new BotonLevel("menus/botones/level.png", 4, world, stage, new Vector2(24, 27), 0.65f, 0.2f) {
            @Override
            public void onclick() {
                System.out.println("JE");
            }
        };

        btnLevel5 = new BotonLevel("menus/botones/level.png", 5, world, stage, new Vector2(30, 27), 0.3f, -0.1f) {
            @Override
            public void onclick() {
                System.out.println("JE");
            }
        };

        btnLevel6 = new BotonLevel("menus/botones/level.png", 6, world, stage, new Vector2(37, 27), 0.9f, 0.3f) {
            @Override
            public void onclick() {
                System.out.println("JE");
            }
        };

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
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        if (debugBox2d) renderer.dispose();
    }
}
