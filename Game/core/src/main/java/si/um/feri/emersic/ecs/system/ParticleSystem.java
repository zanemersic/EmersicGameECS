package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.emersic.ecs.component.ParticleComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;

public class ParticleSystem extends IteratingSystem {
    private final SpriteBatch batch;
    private final Viewport viewport;

    public ParticleSystem(SpriteBatch batch, Viewport viewport) {
        super(Family.all(PositionComponent.class, ParticleComponent.class).get(), 105);

        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        ParticleComponent particle = entity.getComponent(ParticleComponent.class);

        particle.effect.setPosition(position.x, position.y);
        particle.effect.update(deltaTime);
        particle.effect.draw(batch);

        if (particle.effect.isComplete()) {
            getEngine().removeEntity(entity);
        }
    }
}
