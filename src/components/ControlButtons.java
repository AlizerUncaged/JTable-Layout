package components;

import interactions.UserInteractionListener;

import javax.swing.*;
import java.awt.*;

public class ControlButtons extends JPanel {
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JLabel statusLabel;


    public ControlButtons(UserInteractionListener userInteractionListener) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        {
            saveButton = new JButton();
            this.add(saveButton);
            saveButton.addActionListener(userInteractionListener.getAddRowButtonListener());
        }

        {
            deleteButton = new JButton();
            deleteButton.addActionListener(userInteractionListener.getDeleteRowsButtonListener());
            this.add(deleteButton);
        }

        {
            statusLabel = new JLabel();
            statusLabel.setForeground(Color.CYAN);
            statusLabel.setText("");
            this.add(statusLabel);
        }

    }


    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
