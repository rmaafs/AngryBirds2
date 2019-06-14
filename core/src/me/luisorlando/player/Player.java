package me.luisorlando.player;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import me.luisorlando.entity.Entity;
import me.luisorlando.levels.Level;
import me.luisorlando.levels.Level1;

public class Player {
    private List<Entity> birds;
    private Level nivel;
    private int currentNivel = 1;

    private World world;
    private Stage stage;

    public Player(World world, Stage stage){
        this.world = world;
        this.stage = stage;
        birds = new ArrayList<Entity>();
        switch (currentNivel){
            case 1:
                nivel = new Level1();
                break;
        }

        generateLevel();
    }

    public void generateLevel(){
        nivel.generate(stage, world);
    }

    public void play(){
        nivel.play();
    }
}
