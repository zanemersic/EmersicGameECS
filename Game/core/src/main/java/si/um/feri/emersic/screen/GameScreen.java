package si.um.feri.emersic.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.system.MovementSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.emersic.assets.AssetDescriptors;
import si.um.feri.emersic.assets.RegionNames;
import si.um.feri.emersic.config.GameConfig;
import si.um.feri.emersic.ecs.component.DimensionComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;
import si.um.feri.emersic.ecs.component.TextureComponent;
import si.um.feri.emersic.ecs.system.RenderSystem;

public class GameScreen extends ScreenAdapter {
    private final PooledEngine engine = new PooledEngine();
    private final GameStateComponent gameState;
    private final AssetManager assetManager;
    private final SpriteBatch batch = new SpriteBatch();
    private final Viewport viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

    public GameScreen(AssetManager assetManager) {
        this.assetManager = assetManager;

        Entity stateEntity = engine.createEntity();
        gameState = engine.createComponent(GameStateComponent.class);

        stateEntity.add(gameState);
        engine.addEntity(stateEntity);

        engine.addSystem(new MovementSystem(gameState));
        engine.addSystem(new RenderSystem(batch, viewport));
        createSquirell();
    }

    private void createSquirell() {
        TextureAtlas atlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATLAS);

        Entity squirrel = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        dimension.width = 64f;
        dimension.height = 64f;

        position.x = (GameConfig.WORLD_WIDTH - dimension.width) / 2f;
        position.y = 20f;

        texture.region = atlas.findRegion(RegionNames.SQUIRREL);

        squirrel.add(position);
        squirrel.add(dimension);
        squirrel.add(texture);

        engine.addEntity(squirrel);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        engine.removeAllEntities();
        batch.dispose();
    }
}
