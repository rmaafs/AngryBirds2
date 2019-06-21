package me.luisorlando.entity.enemys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.luisorlando.Constants;
import me.luisorlando.entity.Entity;
import me.luisorlando.screen.GameScreen;

import static me.luisorlando.Listener.bird.BirdColisionListener.bodyEliminator;

public class Enemy extends Entity {

    protected EnemyType type;
    protected float vida = 0f, initialVida = 0f;

    public void startDraw() {
        stage.addActor(this);
    }

    public void dañar() {
        if (vida - 1 >= 0) vida--;
        int frame = 0;

        if (vida <= initialVida / 2) {
            frame = 1;
        } else if (vida <= initialVida / 3) {
            frame = 2;
        }

        if (type == EnemyType.NORMAL) {
            textureRegion = new TextureRegion(texture, 98 * frame, 0, 98, 97);
        } else if (type == EnemyType.CASCO) {
            textureRegion = new TextureRegion(texture, 93 * frame, 0, 93, 83);
        } else if (type == EnemyType.BIGOTE) {
            textureRegion = new TextureRegion(texture, 109 * frame, 0, 109, 99);
        }
        if (vida == 0) {
            eliminar();
        }
    }

    public void eliminar() {
        bodyEliminator.add(body);
        this.remove();
        this.setVisible(false);
        GameScreen.player.getNivel().eliminarEnemigo(this);
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
