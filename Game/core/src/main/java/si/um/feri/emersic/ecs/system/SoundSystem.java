package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import si.um.feri.emersic.ecs.component.SoundComponent;

public class SoundSystem extends IteratingSystem {
    public SoundSystem() {
        super(Family.all(SoundComponent.class).get(), 40);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SoundComponent soundData = entity.getComponent(SoundComponent.class);

        soundData.sound.play();
        getEngine().removeEntity(entity);
    }
}
