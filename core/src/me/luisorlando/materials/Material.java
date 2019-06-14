package me.luisorlando.materials;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Constants;

public class Material extends Actor{

    protected Texture texture;
    protected TextureRegion textureRegion;
    protected MaterialType type;
    protected BoxType boxType;
    protected int size = 1;
    protected float durability = 0f;
    protected Body body;

    protected Stage stage;

    public void startDraw(){
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (boxType == BoxType.BOX){
            setPosition((body.getPosition().x + 0.3f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y + 0.3f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        } else {
            setPosition((body.getPosition().x - 0.9f) * Constants.PIXELS_IN_METER - textureRegion.getRegionWidth(), (body.getPosition().y - 0.6f) * Constants.PIXELS_IN_METER - textureRegion.getRegionHeight());
        }

        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }
}
