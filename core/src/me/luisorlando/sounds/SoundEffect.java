package me.luisorlando.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundEffect {
    private HashMap<SoundType, Sound> sounds;

    public SoundEffect() {
        sounds = new HashMap<SoundType, Sound>();
        loadSound(SoundType.BREAK_GLASS, "sounds/break_glass.mp3");
        loadSound(SoundType.BREAK_STONE, "sounds/break_stone.mp3");
        loadSound(SoundType.BREAK_WOOD, "sounds/break_wood.mp3");
        loadSound(SoundType.FLY_RED, "sounds/fly_red.mp3");
        loadSound(SoundType.FLY_WHITE, "sounds/fly_white.mp3");
        loadSound(SoundType.LOSE_BIRDS, "sounds/lose_birds.mp3");
        loadSound(SoundType.WIN_BIRDS, "sounds/win_birds.mp3");
        loadSound(SoundType.OUCH_RED, "sounds/ouch_red.mp3");
        loadSound(SoundType.OUCH_YELLOW, "sounds/ouch_yellow.mp3");
        loadSound(SoundType.OUCH_WHITE, "sounds/ouch_white.mp3");
        loadSound(SoundType.RESORTERA, "sounds/resortera1.mp3");
        loadSound(SoundType.START_LEVEL, "sounds/start_level.mp3");
        loadSound(SoundType.BIRD_BREAK, "sounds/bird_break.mp3");
    }

    public void loadSound(final SoundType type, final String path) {
        new Thread() {
            @Override
            public void run() {
                sounds.put(type, Gdx.audio.newSound(Gdx.files.internal(path)));
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }.start();
    }

    public void play(SoundType type) {
        sounds.get(type).play();
    }

    public void play(SoundType type, float volumen) {
        sounds.get(type).play(volumen);
    }

    public void dispose() {
        sounds.clear();
    }
}
