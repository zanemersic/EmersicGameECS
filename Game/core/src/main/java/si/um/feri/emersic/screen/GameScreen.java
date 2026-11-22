package si.um.feri.emersic.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.system.MovementSystem;

public class GameScreen extends ScreenAdapter {
    private final PooledEngine engine = new PooledEngine();
    private final GameStateComponent gameState;

    public GameScreen() {
        Entity stateEntity = engine.createEntity();
        gameState = engine.createComponent(GameStateComponent.class);

        stateEntity.add(gameState);
        engine.addEntity(stateEntity);

        engine.addSystem(new MovementSystem(gameState));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        engine.update(delta);
    }

    @Override
    public void dispose() {
        engine.removeAllEntities();
    }
}
