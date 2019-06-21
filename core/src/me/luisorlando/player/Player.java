package me.luisorlando.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import me.luisorlando.entity.Entity;
import me.luisorlando.entity.birds.Bird;
import me.luisorlando.levels.Level;
import me.luisorlando.levels.Level1;
import me.luisorlando.screen.GameScreen;

public class Player {
    private List<Entity> birds;
    private Level nivel;
    private int currentNivel = 1;
    private boolean playing = false;
    private int puntos = 0;
    private boolean cambiandoNivel = false;

    private World world;
    private Stage stage;

    private Resortera resortera;

    public Player(World world, Stage stage){
        this.world = world;
        this.stage = stage;

        birds = new ArrayList<Entity>();
        resortera = new Resortera(new Vector2(500, 400));

        switch (currentNivel){
            case 1:
                nivel = new Level1();
                break;
        }

        generateLevel();
    }

    public void nextBird() {
        Bird b = nivel.nextBird();
        if (b != null) {
            resortera.ponerBird(b);
        }
    }

    public void dibujarResortera() {
        resortera.drawSegundaParte(stage.getBatch());
    }

    public void generateLevel(){
        nivel.generate(stage, world);
    }

    public void play(){
        nivel.play();
        resortera.play(stage);
        nextBird();
        playing = true;
        cambiandoNivel = false;
    }

    public void comprobarJuegoTerminado() {
        if (!cambiandoNivel) {
            if (nivel.sinPajaros()) {
                gameOver();
            } else if (nivel.sinEnemigos()) {
                gameWin();
            }
        }
    }

    private void gameOver() {
        cambiandoNivel = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                System.out.println("Juego perdido.");
            }
        }.start();
    }

    private void gameWin() {
        cambiandoNivel = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                GameScreen.player.addPuntos(nivel.getPuntosPajarosRestantes());
                System.out.println("Juego ganado.");
            }
        }.start();
    }

    public void restartPuntos() {
        puntos = 0;
    }

    public void addPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void clickAire() {
        resortera.clickAire();
    }

    public Level getNivel() {
        return nivel;
    }

    public boolean isPlaying() {
        return playing;
    }

    public World getWorld() {
        return world;
    }
}
