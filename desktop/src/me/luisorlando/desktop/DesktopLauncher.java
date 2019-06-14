package me.luisorlando.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import me.luisorlando.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 640;
        config.foregroundFPS = 60;
        config.title = "Angry Birds";
        new LwjglApplication(new Main(), config);
    }
}
