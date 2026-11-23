package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

import si.um.feri.emersic.config.GameConfig;
import si.um.feri.emersic.ecs.component.DimensionComponent;
import si.um.feri.emersic.ecs.component.PlayerComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;

public class PlayerBoundsSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<DimensionComponent> dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);

    public PlayerBoundsSystem() {
        super(Family.all(PlayerComponent.class, PositionComponent.class, DimensionComponent.class).get(), 10);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        DimensionComponent dimension = dimensionMapper.get(entity);

        position.x = MathUtils.clamp(position.x, 0f, GameConfig.WORLD_WIDTH - dimension.width);
    }
}
