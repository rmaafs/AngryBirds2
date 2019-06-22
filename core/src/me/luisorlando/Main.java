package me.luisorlando;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import me.luisorlando.screen.IntroScreen;

public class Main extends Game {
	public static InputMultiplexer inputs = new InputMultiplexer();
	public static AssetManager manager;

	@Override
	public void create() {
		manager = new AssetManager();

		manager.load("fondo.png", Texture.class);
        manager.load("resortera.png", Texture.class);

		manager.load("materials/glass.png", Texture.class);
		manager.load("materials/stone.png", Texture.class);
		manager.load("materials/wood.png", Texture.class);

		manager.load("birds/red.png", Texture.class);
		manager.load("birds/white.png", Texture.class);
		manager.load("birds/yellow.png", Texture.class);

        manager.load("enemigos/normal.png", Texture.class);
        manager.load("enemigos/casco.png", Texture.class);
        manager.load("enemigos/bigote.png", Texture.class);

        manager.load("menus/botones/play.png", Texture.class);


		manager.finishLoading();
        setScreen(new IntroScreen(this));
        //setScreen(new GameScreen(this));
	}

	public AssetManager getManager() {
		return manager;
	}
}
