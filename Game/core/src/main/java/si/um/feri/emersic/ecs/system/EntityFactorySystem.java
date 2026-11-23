package si.um.feri.emersic.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import si.um.feri.emersic.assets.AssetDescriptors;
import si.um.feri.emersic.assets.RegionNames;
import si.um.feri.emersic.config.GameConfig;
import si.um.feri.emersic.ecs.component.CollectibleComponent;
import si.um.feri.emersic.ecs.component.DimensionComponent;
import si.um.feri.emersic.ecs.component.InputComponent;
import si.um.feri.emersic.ecs.component.MovementComponent;
import si.um.feri.emersic.ecs.component.PlayerComponent;
import si.um.feri.emersic.ecs.component.PositionComponent;
import si.um.feri.emersic.ecs.component.RotationComponent;
import si.um.feri.emersic.ecs.component.TextureComponent;
import si.um.feri.emersic.ecs.component.ZOrderComponent;
import si.um.feri.emersic.ecs.component.DamageComponent;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import si.um.feri.emersic.ecs.component.ParticleComponent;
import com.badlogic.gdx.audio.Sound;
import si.um.feri.emersic.ecs.component.SoundComponent;

public class EntityFactorySystem extends EntitySystem {
    private final PooledEngine engine;
    private final TextureAtlas atlas;
    private final ParticleEffect acornEffect;
    private final ParticleEffect bombEffect;
    private final Sound collectSound;
    private final Sound hurtSound;

    public EntityFactorySystem(PooledEngine engine, AssetManager assetManager) {
        this.engine = engine;
        this.atlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATLAS);
        this.acornEffect = assetManager.get(AssetDescriptors.ACORN_EFFECT);
        this.bombEffect = assetManager.get(AssetDescriptors.BOMB_EFFECT);
        this.collectSound = assetManager.get(AssetDescriptors.COLLECT_SOUND);
        this.hurtSound = assetManager.get(AssetDescriptors.HURT_SOUND);

        setProcessing(false);
    }

    public void createSquirell() {
        Entity squirrel = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        InputComponent input = engine.createComponent(InputComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);

        player.health = GameConfig.PLAYER_START_HEALTH;
        zOrder.z = 2;

        dimension.width = 64f;
        dimension.height = 64f;

        position.x = (GameConfig.WORLD_WIDTH - dimension.width) / 2f;
        position.y = 20f;

        texture.region = atlas.findRegion(RegionNames.SQUIRREL);

        squirrel.add(position);
        squirrel.add(dimension);
        squirrel.add(texture);
        squirrel.add(movement);
        squirrel.add(input);
        squirrel.add(player);
        squirrel.add(zOrder);

        engine.addEntity(squirrel);
    }

    public void createBackground() {
        Entity background = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);

        position.x = 0f;
        position.y = 0f;

        dimension.width = GameConfig.WORLD_WIDTH;
        dimension.height = GameConfig.WORLD_HEIGHT;

        texture.region = atlas.findRegion(RegionNames.BACKGROUND);
        zOrder.z = 0;

        background.add(position);
        background.add(dimension);
        background.add(texture);
        background.add(zOrder);

        engine.addEntity(background);
    }

    public void createAcorn(float x, float y) {
        Entity acorn = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        CollectibleComponent collectible = engine.createComponent(CollectibleComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);

        position.x = x;
        position.y = y;

        dimension.width = 32f;
        dimension.height = 32f;

        texture.region = atlas.findRegion(RegionNames.ACORN);
        movement.ySpeed = -120f;
        collectible.points = 1;
        zOrder.z = 1;

        acorn.add(position);
        acorn.add(dimension);
        acorn.add(texture);
        acorn.add(movement);
        acorn.add(collectible);
        acorn.add(zOrder);

        engine.addEntity(acorn);
    }

    public void createBomb(float x, float y) {
        Entity bomb = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        DamageComponent damage = engine.createComponent(DamageComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        RotationComponent rotation = engine.createComponent(RotationComponent.class);

        position.x = x;
        position.y = y;

        dimension.width = 40f;
        dimension.height = 40f;

        texture.region = atlas.findRegion(RegionNames.BOMB);
        movement.ySpeed = -160f; // BOMB SPEED
        damage.damage = 25; // BOMB DAMAGE
        zOrder.z = 1;
        rotation.angularSpeed = 180f; // ROTATION SPEED

        bomb.add(position);
        bomb.add(dimension);
        bomb.add(texture);
        bomb.add(movement);
        bomb.add(damage);
        bomb.add(zOrder);
        bomb.add(rotation);

        engine.addEntity(bomb);
    }

    public void createAcornEffect(float x, float y) {
        Entity entity = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);

        ParticleComponent particle = engine.createComponent(ParticleComponent.class);

        position.x = x;
        position.y = y;

        particle.effect = new ParticleEffect(acornEffect);
        particle.effect.start();

        entity.add(position);
        entity.add(particle);

        engine.addEntity(entity);
    }

    public void createBombEffect(float x, float y) {
        Entity entity = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);

        ParticleComponent particle = engine.createComponent(ParticleComponent.class);

        position.x = x;
        position.y = y;

        particle.effect = new ParticleEffect(bombEffect);
        particle.effect.start();

        entity.add(position);
        entity.add(particle);

        engine.addEntity(entity);
    }

    public void createCollectSound() {
        Entity entity = engine.createEntity();

        SoundComponent soundData = engine.createComponent(SoundComponent.class);

        soundData.sound = collectSound;

        entity.add(soundData);
        engine.addEntity(entity);
    }

    public void createHurtSound() {
        Entity entity = engine.createEntity();

        SoundComponent soundData = engine.createComponent(SoundComponent.class);

        soundData.sound = hurtSound;

        entity.add(soundData);
        engine.addEntity(entity);
    }
}
