package components;

import interactions.UserInteractionListener;

import javax.swing.*;
import java.awt.*;

public class ControlButtons extends JPanel {
    // Buttons and label in the control panel
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JLabel statusLabel;

    /**
     * Constructs a new ControlButtons object with the given UserInteractionListener.
     * The panel contains a Save button, a Delete button and a status label.
     * @param userInteractionListener The UserInteractionListener to handle user interaction events
     */
    public ControlButtons(UserInteractionListener userInteractionListener) {

        // Set the layout of the panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Create the Save button
        {
            saveButton = new JButton();
            this.add(saveButton);
            saveButton.addActionListener(userInteractionListener.getAddRowButtonListener());
        }

        // Create the Delete button
        {
            deleteButton = new JButton();
            deleteButton.addActionListener(userInteractionListener.getDeleteRowsButtonListener());
            this.add(deleteButton);
        }

        // Create the status label
        {
            statusLabel = new JLabel();
            statusLabel.setForeground(Color.CYAN);
            statusLabel.setText("");
            this.add(statusLabel);
        }

    }

    /**
     * Sets the text of the status label to the given status string.
     * @param status The status string to be set in the label
     */
    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    /**
     * Returns the Save button in the control panel.
     * @return The Save button in the control panel
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Returns the Delete button in the control panel.
     * @return The Delete button in the control panel
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Returns the status label in the control panel.
     * @return The status label in the control panel
     */
    public JLabel getStatusLabel() {
        return statusLabel;
    }

}
