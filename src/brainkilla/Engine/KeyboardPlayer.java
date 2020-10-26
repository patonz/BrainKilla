package brainkilla.Engine;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lorenzo Gigli*
 */
public class KeyboardPlayer extends Player implements KeyEventDispatcher {
    
    private final int speed = 5;
    private KeyboardFocusManager keyboardFocusManager;
    
    public KeyboardPlayer(ActiveEntity e, GameLevel gameLevel) {

        super(e, gameLevel);

        keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        this.addKeyEvents();
    }
    
    public void addKeyEvents() {
    
        keyboardFocusManager.addKeyEventDispatcher(this);
    }
    
    public void removeKeyEvents() {
        
        keyboardFocusManager.removeKeyEventDispatcher(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        
        switch (e.getID()) {

            case KeyEvent.KEY_PRESSED: {
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_UP:
                        this.move(0, speed * -1);
                        break;

                    case KeyEvent.VK_DOWN:
                        this.move(0, speed);
                        break;

                    case KeyEvent.VK_LEFT:
                        this.move(speed * -1, 0);
                        break;

                    case KeyEvent.VK_RIGHT:
                        this.move(speed, 0);
                        break;

                    case KeyEvent.VK_SPACE:
                        this.shoot();
                        break;
                }
            }
            break;

            case KeyEvent.KEY_RELEASED:
                this.stop();
                break;
        }

        return true;
    }
}
