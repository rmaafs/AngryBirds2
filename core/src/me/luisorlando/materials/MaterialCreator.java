package me.luisorlando.materials;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.Main;

import static me.luisorlando.Constants.PIXELS_IN_METER;

public class MaterialCreator extends Material {

    public MaterialCreator(MaterialType type, BoxType boxType, Stage stage, World world, Vector2 position){
        this.type = type;
        this.boxType = boxType;
        if (type == MaterialType.WOOD){
            texture = Main.manager.get("materials/wood.png");
            durability = 100f;
        } else if (type == MaterialType.STONE){
            texture = Main.manager.get("materials/stone.png");
            durability = 200f;
        } else if (type == MaterialType.GLASS){
            texture = Main.manager.get("materials/glass.png");
            durability = 50f;
        }

        createBox(world, stage, position);
    }

    private void createBox(World world, Stage stage, Vector2 position){
        this.stage = stage;
        Fixture fixture;
        BodyDef def;
        PolygonShape shape;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        shape = new PolygonShape();

        if (boxType == BoxType.STICK){

            shape.setAsBox(size * 4, size);
            textureRegion = new TextureRegion(texture, 0, 0, 203, 20);
            setSize(PIXELS_IN_METER * size * 2 * 4, PIXELS_IN_METER * size * 2);

        } else if (boxType == BoxType.BOX){
            shape.setAsBox(size, size);
            textureRegion = new TextureRegion(texture, 55, 0, 84, 84);
            setSize(PIXELS_IN_METER * size * 2, PIXELS_IN_METER * size * 2);
        }

        body = world.createBody(def);
        fixture = body.createFixture(shape, 1);
        body.setUserData("MATERIAL_" + type.toString() + "_" + boxType.toString());
    }
}
