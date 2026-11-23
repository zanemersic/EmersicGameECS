package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import si.um.feri.emersic.config.GameConfig;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.component.InputComponent;
import si.um.feri.emersic.ecs.component.MovementComponent;

public class PlayerControlSystem extends IteratingSystem {
    private final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
    private final ComponentMapper<MovementComponent> movementMapper = ComponentMapper.getFor(MovementComponent.class);
    private final GameStateComponent gameState;
    public PlayerControlSystem(GameStateComponent gameState) {
        super(Family.all(InputComponent.class, MovementComponent.class).get(), -10);

        this.gameState = gameState;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (gameState.gameOver) {
            return;
        }

        InputComponent input = inputMapper.get(entity);
        MovementComponent movement = movementMapper.get(entity);

        movement.xSpeed = input.direction * GameConfig.PLAYER_SPEED;
    }
}
