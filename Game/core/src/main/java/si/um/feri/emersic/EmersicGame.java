package si.um.feri.emersic;

import com.badlogic.gdx.Game;
import si.um.feri.emersic.screen.GameScreen;

public class EmersicGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();

        if (getScreen() != null) {
            getScreen().dispose();
        }
    }
}
