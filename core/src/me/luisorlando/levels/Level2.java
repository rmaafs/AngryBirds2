package me.luisorlando.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import me.luisorlando.entity.Entity;
import me.luisorlando.entity.birds.Bird;
import me.luisorlando.entity.birds.BirdRed;
import me.luisorlando.entity.birds.BirdWhite;
import me.luisorlando.entity.birds.BirdYellow;
import me.luisorlando.entity.enemys.Enemy;
import me.luisorlando.entity.enemys.EnemyCreator;
import me.luisorlando.entity.enemys.EnemyType;
import me.luisorlando.materials.BoxType;
import me.luisorlando.materials.Material;
import me.luisorlando.materials.MaterialCreator;
import me.luisorlando.materials.MaterialType;
import me.luisorlando.screen.GameScreen;

public class Level2 implements Level {

    private List<Material> materiales;
    private List<Enemy> enemigos;
    private List<Bird> pajaros;
    private float floor = 7.35f;
    private int currentBird = 0;

    public Level2() {
        materiales = new ArrayList<Material>();
        enemigos = new ArrayList<Enemy>();
        pajaros = new ArrayList<Bird>();
    }//Empieza a crear el mapa2

    public void play() {
        for (Material m : materiales) {
            m.startDraw();
        }

        for (Enemy m : enemigos) {
            m.startDraw();
        }

        for (Bird m : pajaros) {
            m.startDraw();
        }
        GameScreen.player.restartPuntos();
    }

    public Bird nextBird() {
        if (currentBird + 1 <= pajaros.size()) {
            return pajaros.get(currentBird++);
        }
        return null;
    }

    public void generate(Stage stage, World world) {
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.STICK, stage, world, new Vector2(43, floor)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.STICK, stage, world, new Vector2(35, floor)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.STICK, stage, world, new Vector2(27, floor)));

        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(24, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(24, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(24, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(24, floor + 8)));

        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(26, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(26, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(26, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(26, floor + 8)));

        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(28, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(28, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(28, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(28, floor + 8)));


        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(45, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(45, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(45, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(45, floor + 8)));

        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(43, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(43, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(43, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(43, floor + 8)));

        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(41, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(41, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(41, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.STONE, BoxType.BOX, stage, world, new Vector2(41, floor + 8)));

        generateEnemys(stage, world);
        generateBirds(stage, world);
    }

    public void generateEnemys(Stage stage, World world) {
        enemigos.add(new EnemyCreator(EnemyType.NORMAL, stage, world, new Vector2(32, floor + 2)));
        enemigos.add(new EnemyCreator(EnemyType.BIGOTE, stage, world, new Vector2(35, floor + 2)));
        enemigos.add(new EnemyCreator(EnemyType.CASCO, stage, world, new Vector2(38, floor + 2)));
    }

    public void generateBirds(Stage stage, World world) {
        float x = 9;
        pajaros.add(new BirdRed(stage, world, new Vector2(x -= 2f, floor)));
        pajaros.add(new BirdRed(stage, world, new Vector2(x -= 1.5, floor)));
        pajaros.add(new BirdWhite(stage, world, new Vector2(x -= 2, floor)));
        pajaros.add(new BirdWhite(stage, world, new Vector2(x -= 2, floor)));
    }

    public boolean sinEnemigos() {
        return enemigos.isEmpty();
    }

    public boolean sinPajaros() {
        return pajaros.isEmpty();
    }

    public void eliminarBird(Bird b) {
        pajaros.remove(b);
        currentBird--;
    }

    public void eliminarEnemigo(Enemy b) {
        enemigos.remove(b);
    }

    public int getPuntosPajarosRestantes() {
        int contador = 0;
        for (Bird bird : pajaros) {
            if (bird instanceof BirdRed) {
                contador += 500;
            } else if (bird instanceof BirdYellow) {
                contador += 700;
            } else if (bird instanceof BirdWhite) {
                contador += 900;
            }
        }
        return contador;
    }

    public Bird getBirdByBody(Body b) {
        for (Bird bird : pajaros) {
            if (b == ((Entity) bird).getBody()) {
                return bird;
            }
        }
        return null;
    }

    public Material getMaterialByBody(Body b) {
        for (Material material : materiales) {
            if (b == material.getBody()) {
                return material;
            }
        }
        return null;
    }

    public Enemy getEnemyByBody(Body b) {
        for (Enemy enemy : enemigos) {
            if (b == enemy.getBody()) {
                return enemy;
            }
        }
        return null;
    }

    @Override
    public int getNumber() {
        return 2;
    }
}
