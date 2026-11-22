package si.um.feri.emersic.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class AssetDescriptors {
    public static final AssetDescriptor<TextureAtlas> GAMEPLAY_ATLAS =
        new AssetDescriptor<>(AssetPaths.GAMEPLAY_ATLAS, TextureAtlas.class);

    private AssetDescriptors() {
    }
}
