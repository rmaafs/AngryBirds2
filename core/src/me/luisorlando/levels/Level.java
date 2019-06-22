package me.luisorlando.levels;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.luisorlando.entity.birds.Bird;
import me.luisorlando.entity.enemys.Enemy;
import me.luisorlando.materials.Material;

public interface Level {

    void play();
    void generate(Stage stage, World world);
    void generateEnemys(Stage stage, World world);
    void generateBirds(Stage stage, World world);
    Bird nextBird();
    Bird getBirdByBody(Body b);
    Material getMaterialByBody(Body b);
    Enemy getEnemyByBody(Body b);
    boolean sinEnemigos();
    boolean sinPajaros();
    void eliminarBird(Bird b);
    void eliminarEnemigo(Enemy b);
    int getPuntosPajarosRestantes();
}
