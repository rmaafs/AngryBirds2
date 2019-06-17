package me.luisorlando.entity.birds;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Bird {
    void startDraw();

    void draw(Batch batch, float parentAlpha);
}
