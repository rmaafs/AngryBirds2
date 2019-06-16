package me.luisorlando.entity.enemys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Constants;

public class Enemy extends Actor {
    protected Texture texture;
    protected TextureRegion textureRegion;
    protected Stage stage;
    protected Body body;
    protected EnemyType type;
    protected int x = 0, y = 0, size = 1;
    protected float vida = 0f;

    public void startDraw() {
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (type == EnemyType.NORMAL) {
            setPosition((body.getPosition().x + 0.55f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.5f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        }

        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }

}
