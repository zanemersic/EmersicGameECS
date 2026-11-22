package si.um.feri.emersic.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class InputComponent implements Component, Pool.Poolable {
    public float direction;

    @Override
    public void reset() {
        direction = 0f;
    }
}
