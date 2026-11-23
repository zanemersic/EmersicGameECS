package si.um.feri.emersic.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import si.um.feri.emersic.ecs.component.GameStateComponent;
import si.um.feri.emersic.ecs.system.AcornSpawnSystem;
import si.um.feri.emersic.ecs.system.BombSpawnSystem;
import si.um.feri.emersic.ecs.system.CleanupSystem;
import si.um.feri.emersic.ecs.system.CollisionSystem;
import si.um.feri.emersic.ecs.system.EntityFactorySystem;
import si.um.feri.emersic.ecs.system.InputHandlerSystem;
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
import si.um.feri.emersic.ecs.system.PlayerBoundsSystem;
import si.um.feri.emersic.ecs.system.PlayerControlSystem;
import si.um.feri.emersic.ecs.system.RenderSystem;
import com.badlogic.gdx.Gdx;

import si.um.feri.emersic.ecs.system.RotationSystem;
import si.um.feri.emersic.ecs.system.TextSystem;
import si.um.feri.emersic.input.RawInputHandler;

public class GameScreen extends ScreenAdapter {
    private final PooledEngine engine = new PooledEngine();
    private final GameStateComponent gameState;
    private final SpriteBatch batch = new SpriteBatch();
    private final Viewport viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
    private final RawInputHandler rawInputHandler;

    public GameScreen(AssetManager assetManager) {

        Entity stateEntity = engine.createEntity();
        gameState = engine.createComponent(GameStateComponent.class);

        stateEntity.add(gameState);
        engine.addEntity(stateEntity);

        EntityFactorySystem factory = new EntityFactorySystem(engine, assetManager);
        InputHandlerSystem inputHandler = new InputHandlerSystem(gameState);

        engine.addSystem(factory);
        engine.addSystem(new PlayerControlSystem(gameState));
        engine.addSystem(inputHandler);
        engine.addSystem(new MovementSystem(gameState));
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new PlayerBoundsSystem());
        engine.addSystem(new CleanupSystem());
        engine.addSystem(new AcornSpawnSystem(factory, gameState));
        engine.addSystem(new CollisionSystem(gameState));
        engine.addSystem(new TextSystem(batch, viewport, assetManager, gameState));
        engine.addSystem(new RotationSystem(gameState));
        engine.addSystem(new BombSpawnSystem(factory, gameState));

        factory.createBackground();
        factory.createSquirell();
        rawInputHandler = new RawInputHandler(inputHandler);
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
    public void show() {
        Gdx.input.setInputProcessor(rawInputHandler);
    }

    @Override
    public void hide() {
        rawInputHandler.reset();

        if (Gdx.input.getInputProcessor() == rawInputHandler) {
            Gdx.input.setInputProcessor(null);
        }
    }

    @Override
    public void dispose() {
        engine.removeAllEntities();
        batch.dispose();
    }
}
