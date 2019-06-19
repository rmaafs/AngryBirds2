package me.luisorlando.materials;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Constants;

import static me.luisorlando.Listener.bird.BirdColisionListener.bodyEliminator;

public class Material extends Actor{

    protected Texture texture;
    protected TextureRegion textureRegion;
    protected MaterialType type;
    protected BoxType boxType;
    protected int size = 1;
    protected float durability = 0f, initialDurability = 0f;
    protected Body body;

    protected Stage stage;

    public void startDraw(){
        stage.addActor(this);
    }

    public boolean dañar(float daño) {
        durability -= daño;
        if (durability < 0) durability = 0;

        int frame = 0;

        if (durability < initialDurability / 4) {
            frame = 3;
        } else if (durability < initialDurability / 3) {
            frame = 2;
        } else if (durability < initialDurability / 2) {
            frame = 1;
        }

        final int fframe = frame;

        if (boxType == BoxType.BOX) {
            textureRegion = new TextureRegion(texture, 85 * fframe, 85, 84, 84);
        } else {
            textureRegion = new TextureRegion(texture, 0, 20 * fframe, 203, 20);
        }
        if (durability == 0) {
            eliminar();
        }

        return durability == 0;
    }

    public void eliminar() {
        bodyEliminator.add(body);
        this.remove();
        this.setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (boxType == BoxType.BOX){
            setPosition((body.getPosition().x + 0.3f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.3f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        } else {
            setPosition((body.getPosition().x - 0.9f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y - 0.6f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        }

        batch.draw(textureRegion, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, (float) Math.toDegrees(body.getAngle()));
    }

    public Body getBody() {
        return body;
    }

    public MaterialType getType() {
        return type;
    }
}
