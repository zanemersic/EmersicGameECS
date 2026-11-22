package si.um.feri.emersic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import si.um.feri.emersic.assets.AssetDescriptors;
import si.um.feri.emersic.screen.GameScreen;

public class EmersicGame extends Game {
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();

        assetManager.load(AssetDescriptors.GAMEPLAY_ATLAS);
        assetManager.finishLoading();

        setScreen(new GameScreen(assetManager));
    }

    @Override
    public void dispose() {
        super.dispose();

        if (getScreen() != null) {
            getScreen().dispose();
        }

        if (assetManager != null) {
            assetManager.dispose();
        }
    }
}
