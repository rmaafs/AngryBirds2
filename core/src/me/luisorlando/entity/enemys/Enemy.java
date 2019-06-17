package me.luisorlando.entity.enemys;

import com.badlogic.gdx.graphics.g2d.Batch;

import me.luisorlando.Constants;
import me.luisorlando.entity.Entity;

public class Enemy extends Entity {

    protected EnemyType type;
    protected float vida = 0f;

    public void startDraw() {
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (type == EnemyType.NORMAL) {
            setPosition((body.getPosition().x + 0.55f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.5f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        } else if (type == EnemyType.CASCO) {
            setPosition((body.getPosition().x + 0.4f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.35f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        } else if (type == EnemyType.BIGOTE) {
            setPosition((body.getPosition().x + 0.2f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.1f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        }

        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }

}
