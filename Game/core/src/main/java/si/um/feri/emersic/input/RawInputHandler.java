package si.um.feri.emersic.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
public class RawInputHandler extends InputAdapter {
    private final IInputHandler listener;

    private boolean aPressed;
    private boolean dPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public RawInputHandler(IInputHandler listener) {
        this.listener = listener;
    }

    @Override
    public boolean keyDown(int keycode) {
        return updateKey(keycode, true);
    }

    @Override
    public boolean keyUp(int keycode) {
        return updateKey(keycode, false);
    }

    private boolean updateKey(int keycode, boolean pressed) {
        switch (keycode) {
            case Input.Keys.A:
                aPressed = pressed;
                break;
            case Input.Keys.D:
                dPressed = pressed;
                break;
            case Input.Keys.LEFT:
                leftPressed = pressed;
                break;
            case Input.Keys.RIGHT:
                rightPressed = pressed;
                break;
            default:
                return false;
        }

        boolean left = aPressed || leftPressed;
        boolean right = dPressed || rightPressed;

        float direction = 0f;

        if (left && !right) {
            direction = -1f;
        } else if (right && !left) {
            direction = 1f;
        }

        listener.move(direction);
        return true;
    }

    public void reset() {
        aPressed = false;
        dPressed = false;
        leftPressed = false;
        rightPressed = false;

        listener.move(0f);
    }
}
