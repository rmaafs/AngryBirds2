package me.luisorlando.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.luisorlando.Main;

public class IntroPlay extends Actor {

    private Texture texture;
    private Stage stage;

    public IntroPlay(Stage stage) {
        this.stage = stage;
        texture = Main.manager.get("menus/botones/play.png");
        stage.addActor(this);
        registrarEventos();
    }

    private void registrarEventos() {
        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("clickee");
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), texture.getWidth() * 3, texture.getHeight() * 3);
    }
}
