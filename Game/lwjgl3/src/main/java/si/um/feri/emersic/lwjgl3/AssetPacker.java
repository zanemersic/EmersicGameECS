package si.um.feri.emersic.lwjgl3;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {
    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 4096;
        settings.maxHeight = 4096;

        TexturePacker.process(settings, "lwjgl3/assets-raw", "assets/atlas", "gameplay");
    }
}
