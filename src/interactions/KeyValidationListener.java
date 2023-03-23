package interactions;

import components.LabelAndInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

    public class KeyValidationListener implements KeyListener {

    private LabelAndInput labelAndInput;

    public KeyValidationListener(LabelAndInput labelAndInput)
    {
        this.labelAndInput = labelAndInput;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // labelAndInput.validateInput();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // labelAndInput.validateInput();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        labelAndInput.validateInput();
    }
}
