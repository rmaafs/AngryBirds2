package me.luisorlando;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import me.luisorlando.levels.Level;
import me.luisorlando.screen.GameScreen;
import me.luisorlando.screen.IntroScreen;
import me.luisorlando.screen.LevelSelectorScreen;

public class Main extends Game {
	public static InputMultiplexer inputs = new InputMultiplexer();
	public static AssetManager manager;

	private IntroScreen introScreen;
	private LevelSelectorScreen levelSelectorScreen;

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

		manager.load("menus/levelselector.png", Texture.class);
		manager.load("menus/botones/level.png", Texture.class);
        manager.load("menus/botones/play.png", Texture.class);
		manager.load("menus/botones/salir.png", Texture.class);
		manager.load("menus/botones/pausar.png", Texture.class);
		manager.load("menus/botones/restart.png", Texture.class);


		manager.finishLoading();

		openLevelSelector();
	}

	public void openIntro() {
		if (introScreen == null) {
			introScreen = new IntroScreen(this);
		}
		setScreen(introScreen);
	}

	public void openLevelSelector() {
		/*if (levelSelectorScreen == null){
			levelSelectorScreen = new LevelSelectorScreen(this);
		}
		if (GameScreen.player != null){
			GameScreen.player = null;
		}*/
		setScreen(new LevelSelectorScreen(this));
	}

	public void openGame(Level level) {
		setScreen(new GameScreen(this, level));
	}

	public AssetManager getManager() {
		return manager;
	}
}
