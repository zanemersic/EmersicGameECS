package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import si.um.feri.emersic.ecs.component.DimensionComponent;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;
import com.badlogic.ashley.core.Family;
import si.um.feri.emersic.ecs.component.PlayerComponent;
import si.um.feri.emersic.ecs.component.CollectibleComponent;

public class CollisionSystem extends EntitySystem {
    private final GameStateComponent gameState;
    private final Family playerFamily = Family.all(PlayerComponent.class, PositionComponent.class, DimensionComponent.class).get();
    private final Family acornFamily = Family.all(CollectibleComponent.class, PositionComponent.class, DimensionComponent.class).get();


    public CollisionSystem(GameStateComponent gameState) {
        super(20);
        this.gameState = gameState;
    }

    @Override
    public void update(float deltaTime) {
        if (gameState.gameOver) {
            return;
        }

        for (Entity player : getEngine().getEntitiesFor(playerFamily)) {
            for (Entity acorn : getEngine().getEntitiesFor(acornFamily)) {
                if (overlaps(player, acorn)) {
                    CollectibleComponent collectible =
                        acorn.getComponent(CollectibleComponent.class);

                    gameState.score += collectible.points;
                    getEngine().removeEntity(acorn);
                }
            }
        }
    }

    private boolean overlaps(Entity a, Entity b) {
        PositionComponent aPosition = a.getComponent(PositionComponent.class);
        DimensionComponent aSize = a.getComponent(DimensionComponent.class);

        PositionComponent bPosition = b.getComponent(PositionComponent.class);
        DimensionComponent bSize = b.getComponent(DimensionComponent.class);

        if (aPosition.x + aSize.width <= bPosition.x) {
            return false;
        }

        if (aPosition.x >= bPosition.x + bSize.width) {
            return false;
        }

        if (aPosition.y + aSize.height <= bPosition.y) {
            return false;
        }

        if (aPosition.y >= bPosition.y + bSize.height) {
            return false;
        }

        return true;
    }
}
