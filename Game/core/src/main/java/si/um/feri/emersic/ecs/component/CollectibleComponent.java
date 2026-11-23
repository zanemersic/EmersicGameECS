package si.um.feri.emersic.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CollectibleComponent implements Component, Pool.Poolable {
    public int points;

    @Override
    public void reset() {
        points = 0;
    }
}
