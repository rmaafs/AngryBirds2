package me.luisorlando.Listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.luisorlando.entity.Entity;
import me.luisorlando.entity.birds.Bird;

public class BirdClickListener extends ClickListener {

    private Bird bird;
    private boolean esperarSuelte = false;

    public BirdClickListener(Bird b) {
        this.bird = b;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (!esperarSuelte) {
            esperarSuelte = true;
            ((Entity) bird).setFollowMouse(true);
        }
        return true;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (esperarSuelte) {
            esperarSuelte = false;
            ((Entity) bird).lanzar();
        }
    }
}
