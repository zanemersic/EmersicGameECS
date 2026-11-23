package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.emersic.assets.AssetDescriptors;
import si.um.feri.emersic.config.GameConfig;
import si.um.feri.emersic.ecs.component.GameStateComponent;

public class TextSystem extends EntitySystem {
    private final SpriteBatch batch;
    private final Viewport viewport;
    private final BitmapFont font;
    private final GameStateComponent gameState;

    public TextSystem(SpriteBatch batch, Viewport viewport, AssetManager assetManager, GameStateComponent gameState) {
        super(110);
        this.batch = batch;
        this.viewport = viewport;
        this.gameState = gameState;
        this.font = assetManager.get(AssetDescriptors.FONT);
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        font.draw(batch, "Score: " + gameState.score, 15f, GameConfig.WORLD_HEIGHT - 15f);
        batch.end();
    }

}
