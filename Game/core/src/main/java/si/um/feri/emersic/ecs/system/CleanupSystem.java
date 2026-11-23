package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import si.um.feri.emersic.ecs.component.CollectibleComponent;
import si.um.feri.emersic.ecs.component.DimensionComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;
import si.um.feri.emersic.ecs.component.DamageComponent;

public class CleanupSystem extends IteratingSystem {
    public CleanupSystem() {
        super(Family.all(PositionComponent.class, DimensionComponent.class).one(CollectibleComponent.class, DamageComponent.class).get(), 30);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        DimensionComponent dimension = entity.getComponent(DimensionComponent.class);

        if (position.y + dimension.height < 0f) {
            getEngine().removeEntity(entity);
        }
    }
}
