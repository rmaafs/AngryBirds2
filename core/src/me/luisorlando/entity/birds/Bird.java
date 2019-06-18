package me.luisorlando.entity.birds;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public interface Bird {

    void startDraw();

    void cambiarPosicion(Vector2 position);
    void draw(Batch batch, float parentAlpha);

    void disableMovement(boolean disable);
}
