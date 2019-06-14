package me.luisorlando.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.materials.BoxType;
import me.luisorlando.materials.Material;
import me.luisorlando.materials.MaterialCreator;
import me.luisorlando.materials.MaterialType;

public class Level1 implements Level {

    public void play(){
        for (Material m : materiales){
            m.startDraw();
        }
    }

    public void generate(Stage stage, World world){
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(5, 15)));

        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.STICK, stage, world, new Vector2(12, 15)));
    }
}
