package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.component.MovementComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;

public class MovementSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<MovementComponent> movementMapper = ComponentMapper.getFor(MovementComponent.class);
    private final GameStateComponent gameState;

    public MovementSystem(GameStateComponent gameState) {
        super(Family.all(PositionComponent.class, MovementComponent.class).get());
        this.gameState = gameState;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (gameState.gameOver) {
            return;
        }

        PositionComponent position = positionMapper.get(entity);
        MovementComponent movement = movementMapper.get(entity);

        position.x = position.x + movement.xSpeed * deltaTime;
        position.y = position.y + movement.ySpeed * deltaTime;
    }
}
