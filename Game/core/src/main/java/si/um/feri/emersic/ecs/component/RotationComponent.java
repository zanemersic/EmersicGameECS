package si.um.feri.emersic.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RotationComponent implements Component, Pool.Poolable {
    public float angle;
    public float angularSpeed;

    @Override
    public void reset() {
        angle = 0f;
        angularSpeed = 0f;
    }
}
