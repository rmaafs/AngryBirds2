package me.luisorlando.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Main;

public class Resortera extends Actor {
    private TextureRegion texture1, texture2;
    private Vector2 position;

    private float size = 2f;

    public Resortera(Vector2 position) {
        this.position = position;
        Texture texture = Main.manager.get("resortera.png");
        texture2 = new TextureRegion(texture, 0, 0, 39, 199);
        texture1 = new TextureRegion(texture, 39, 0, 43, 124);
    }

    public void play(Stage stage) {
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture1, position.x - 50, position.y + 165, texture1.getRegionWidth() * size, texture1.getRegionHeight() * size);
    }

    public void drawSegundaParte(Batch batch) {
        batch.begin();
        batch.draw(texture2, position.x, position.y, texture2.getRegionWidth() * size, texture2.getRegionHeight() * size);
        batch.end();
    }
}