package me.luisorlando.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import me.luisorlando.Main;
import me.luisorlando.savegame.Loader;

public abstract class Pantalla implements Screen {

    protected Main game;
    public static Loader gameLoader = new Loader();

    public Pantalla(Main game) {
        this.game = game;
    }

    public Texture getRecurso(String path) {
        return game.getManager().get(path);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
