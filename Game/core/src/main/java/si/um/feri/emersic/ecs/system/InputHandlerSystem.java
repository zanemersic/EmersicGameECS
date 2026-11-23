package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.component.InputComponent;
import si.um.feri.emersic.ecs.component.PlayerComponent;
import si.um.feri.emersic.input.IInputHandler;
public class InputHandlerSystem extends EntitySystem implements IInputHandler {
    private final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
    private final Family playerFamily = Family.all(PlayerComponent.class, InputComponent.class).get();

    private final GameStateComponent gameState;

    public InputHandlerSystem(GameStateComponent gameState) {
        this.gameState = gameState;
        setProcessing(false);
    }

    @Override
    public void move(float direction) {
        if (gameState.gameOver) {
            return;
        }

        for (Entity player : getEngine().getEntitiesFor(playerFamily)) {
            InputComponent input = inputMapper.get(player);
            input.direction = direction;
        }
    }
}
