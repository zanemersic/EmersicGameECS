package si.um.feri.emersic.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;

public final class AssetDescriptors {
    public static final AssetDescriptor<TextureAtlas> GAMEPLAY_ATLAS =
        new AssetDescriptor<>(AssetPaths.GAMEPLAY_ATLAS, TextureAtlas.class);
    public static final AssetDescriptor<BitmapFont> FONT =
        new AssetDescriptor<>(AssetPaths.FONT, BitmapFont.class);
    public static final AssetDescriptor<ParticleEffect> ACORN_EFFECT =
        particleEffect(AssetPaths.ACORN_EFFECT);
    public static final AssetDescriptor<ParticleEffect> BOMB_EFFECT =
        particleEffect(AssetPaths.BOMB_EFFECT);

    public static final AssetDescriptor<Sound> COLLECT_SOUND =
        new AssetDescriptor<>(AssetPaths.COLLECT_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> HURT_SOUND =
        new AssetDescriptor<>(AssetPaths.HURT_SOUND, Sound.class);

    private static AssetDescriptor<ParticleEffect> particleEffect(String path) {
        ParticleEffectParameter parameters = new ParticleEffectParameter();
        parameters.atlasFile = AssetPaths.GAMEPLAY_ATLAS;

        return new AssetDescriptor<>(path, ParticleEffect.class, parameters);
    }



    private AssetDescriptors() {
    }
}
