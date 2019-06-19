package me.luisorlando.entity.enemys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import me.luisorlando.Constants;
import me.luisorlando.entity.Entity;

public class Enemy extends Entity {

    protected EnemyType type;
    protected float vida = 0f, initialVida = 0f;

    public void startDraw() {
        stage.addActor(this);
    }

    public void dañar(final World world) {
        if (vida - 1 > 0) vida--;
        int frame = 0;

        if (vida <= initialVida / 2) {
            frame = 1;
        } else if (vida <= initialVida / 3) {
            frame = 2;
        }

        final int fframe = frame;

        new Thread() {
            @Override
            public void run() {
                try {
                    if (type == EnemyType.NORMAL) {
                        textureRegion = new TextureRegion(texture, 98 * fframe, 0, 98, 97);
                    } else if (type == EnemyType.CASCO) {
                        textureRegion = new TextureRegion(texture, 93 * fframe, 0, 93, 83);
                    } else if (type == EnemyType.BIGOTE) {
                        textureRegion = new TextureRegion(texture, 109 * fframe, 0, 109, 99);
                    }
                    if (vida == 0) {
                        eliminar(world);
                    }
                } catch (Exception e) {
                    System.out.println("error aca compa");
                }
            }
        }.start();
    }

    public void eliminar(World world) {
        world.destroyBody(body);
        this.remove();
        this.setVisible(false);
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
