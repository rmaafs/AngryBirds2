package me.luisorlando.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.entity.enemys.Enemy;
import me.luisorlando.entity.enemys.EnemyCreator;
import me.luisorlando.entity.enemys.EnemyType;
import me.luisorlando.materials.BoxType;
import me.luisorlando.materials.Material;
import me.luisorlando.materials.MaterialCreator;
import me.luisorlando.materials.MaterialType;

public class Level1 implements Level {

    private float floor = 7.35f;

    public void play(){
        for (Material m : materiales){
            m.startDraw();
        }

        for (Enemy m : enemigos) {
            m.startDraw();
        }
    }

    public void generate(Stage stage, World world){
        //materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(30, 15)));

        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.STICK, stage, world, new Vector2(43, floor)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.STICK, stage, world, new Vector2(35, floor)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.STICK, stage, world, new Vector2(27, floor)));

        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(27, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(27, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(27, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(27, floor + 8)));

        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(43, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(43, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(43, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.WOOD, BoxType.BOX, stage, world, new Vector2(43, floor + 8)));

        generateEnemys(stage, world);
    }

    public void generateEnemys(Stage stage, World world) {
        enemigos.add(new EnemyCreator(EnemyType.NORMAL, stage, world, new Vector2(32, floor + 2)));
        enemigos.add(new EnemyCreator(EnemyType.CASCO, stage, world, new Vector2(37, floor + 2)));
    }
}
