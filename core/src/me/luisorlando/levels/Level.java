package me.luisorlando.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import me.luisorlando.materials.Material;

public interface Level {

    List<Material> materiales = new ArrayList<Material>();

    void play();
    void generate(Stage stage, World world);
}
