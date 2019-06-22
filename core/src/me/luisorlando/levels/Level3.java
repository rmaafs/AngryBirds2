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

public class Level3 implements Level {

    private List<Material> materiales;
    private List<Enemy> enemigos;
    private List<Bird> pajaros;
    private float floor = 7.35f;
    private int currentBird = 0;

    public Level3() {
        materiales = new ArrayList<Material>();
        enemigos = new ArrayList<Enemy>();
        pajaros = new ArrayList<Bird>();
    }

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
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor + 8)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor + 10)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(27, floor + 12)));

        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor + 2)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor + 4)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor + 6)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor + 8)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor + 10)));
        materiales.add(new MaterialCreator(MaterialType.GLASS, BoxType.BOX, stage, world, new Vector2(43, floor + 12)));

        generateEnemys(stage, world);
        generateBirds(stage, world);
    }

    public void generateEnemys(Stage stage, World world) {
        enemigos.add(new EnemyCreator(EnemyType.NORMAL, stage, world, new Vector2(27, floor + 14)));
        enemigos.add(new EnemyCreator(EnemyType.NORMAL, stage, world, new Vector2(43, floor + 14)));
    }

    public void generateBirds(Stage stage, World world) {
        float x = 9;
        pajaros.add(new BirdYellow(stage, world, new Vector2(x -= 2f, floor)));
        pajaros.add(new BirdYellow(stage, world, new Vector2(x -= 1.5, floor)));
        pajaros.add(new BirdYellow(stage, world, new Vector2(x -= 2, floor)));
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
}
