package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.MathUtils;
import si.um.feri.emersic.config.GameConfig;
import si.um.feri.emersic.ecs.component.GameStateComponent;

public class AcornSpawnSystem extends EntitySystem {
    private final EntityFactorySystem factory;
    private final GameStateComponent gameState;
    private float timer = 0f;

    public AcornSpawnSystem(EntityFactorySystem factory, GameStateComponent gameState) {
        super(-20);
        this.factory = factory;
        this.gameState = gameState;
    }

    @Override
    public void update(float deltaTime) {
        if (gameState.gameOver) {
            return;
        }

        timer += deltaTime;

        if (timer >= 1f) {
            timer = 0f;

            float x = MathUtils.random(0f, GameConfig.WORLD_WIDTH - 32f);

            factory.createAcorn(x, GameConfig.WORLD_HEIGHT);
        }
    }
}
