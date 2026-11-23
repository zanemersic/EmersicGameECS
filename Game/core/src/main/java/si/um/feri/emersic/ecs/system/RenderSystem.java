package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.emersic.ecs.component.DimensionComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;
import si.um.feri.emersic.ecs.component.RotationComponent;
import si.um.feri.emersic.ecs.component.TextureComponent;
import si.um.feri.emersic.ecs.component.ZOrderComponent;


public class RenderSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<DimensionComponent> dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);
    private final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
    private final ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);

    private final SpriteBatch batch;
    private final Viewport viewport;
    private int currentLayer;

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(Family.all(PositionComponent.class, DimensionComponent.class, TextureComponent.class, ZOrderComponent.class).get(), 100);

        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        for (int layer = 0; layer <= 2; layer++) {
            currentLayer = layer;
            super.update(deltaTime);
        }

        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        DimensionComponent dimension = dimensionMapper.get(entity);
        TextureComponent texture = textureMapper.get(entity);
        RotationComponent rotation = rotationMapper.get(entity);
        ZOrderComponent zOrder = entity.getComponent(ZOrderComponent.class);

        if (zOrder.z != currentLayer) {
            return;
        }

        float angle;

        if (rotation != null) {
            angle = rotation.angle;
        } else {
            angle = 0f;
        }

        batch.draw(texture.region, position.x, position.y, dimension.width / 2f, dimension.height / 2f, dimension.width, dimension.height, 1f, 1f, angle);
    }
}
