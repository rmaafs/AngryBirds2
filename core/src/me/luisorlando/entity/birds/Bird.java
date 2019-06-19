package me.luisorlando.entity.birds;

public interface Bird {
    void startDraw();
    void setTextureVolando();
    void setTextureDañado();

    void poderEspecial();

    BirdType getType();
}
