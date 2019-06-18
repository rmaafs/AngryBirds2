package me.luisorlando.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Entity extends Actor {
    protected Texture texture;
    protected TextureRegion textureRegion;
    protected Stage stage;
    protected Body body;
    protected int size = 1;
    protected float x = 0, y = 0;

    public Entity(){

    }
}
