package si.um.feri.emersic.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class GameStateComponent implements Component, Pool.Poolable {
    public int score;
    public boolean gameOver;

    @Override
    public void reset() {
        score = 0;
        gameOver = false;
    }
}
