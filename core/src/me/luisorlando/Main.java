package me.luisorlando;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import me.luisorlando.levels.Level;
import me.luisorlando.screen.GameScreen;
import me.luisorlando.screen.IntroScreen;
import me.luisorlando.screen.LevelSelectorScreen;
import me.luisorlando.sounds.SoundEffect;

public class Main extends Game {
	public static InputMultiplexer inputs = new InputMultiplexer();
	public static AssetManager manager;
	public static SoundEffect sounds;

	@Override
	public void create() {
		manager = new AssetManager();
		sounds = new SoundEffect();

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

		manager.load("menus/win.png", Texture.class);
		manager.load("menus/lose.png", Texture.class);


		manager.finishLoading();

		openLevelSelector();
	}

	public void openIntro() {
		setScreen(new IntroScreen(this));
	}

	public void openLevelSelector() {
		if (getScreen() != null) getScreen().dispose();
		setScreen(new LevelSelectorScreen(this));
	}

	public void openGame(Level level) {
		setScreen(new GameScreen(this, level));
	}

	public AssetManager getManager() {
		return manager;
	}
}
