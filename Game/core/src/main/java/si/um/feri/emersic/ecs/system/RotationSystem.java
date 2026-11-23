package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.component.RotationComponent;
public class RotationSystem extends IteratingSystem {
    private final GameStateComponent gameState;

    public RotationSystem(GameStateComponent gameState) {
        super(Family.all(RotationComponent.class).get(), 5);
        this.gameState = gameState;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (gameState.gameOver) {
            return;
        }

        RotationComponent rotation = entity.getComponent(RotationComponent.class);
        rotation.angle += rotation.angularSpeed * deltaTime;
    }
}
