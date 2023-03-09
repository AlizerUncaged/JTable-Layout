package components;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a label and text field component that can be used in Swing user interfaces.
 */
public class LabelAndInput extends JPanel {

    private FlowLayout flowLayout;
    private JLabel label;
    private JTextField textField;
    private String name;

    /**
     * Gets the label component.
     *
     * @return the label component
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Gets the text field component.
     *
     * @return the text field component
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Constructs a new label and text field component with the provided name for the label.
     *
     * @param name the name for the label component
     */
    public LabelAndInput(String name) {
        this.name = name;

        // Create a new label and flow layout for the panel
        label = new JLabel();
        flowLayout = new FlowLayout();

        // Set the layout, alignment, and component orientation of the panel
        setLayout(flowLayout);
        setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Render the label and text field components
        renderLabel();
        renderField();
    }

    /**
     * Renders the text field component and adds it to the panel.
     */
    void renderField() {
        // Create a new text field and set its size
        textField = new JTextField();
        textField.setColumns(15);
        // Add the text field to the panel
        add(textField);
    }

    /**
     * Renders the label component and adds it to the panel.
     */
    void renderLabel() {
        // Set the text of the label to the provided name and set its size
        label.setText(name);
        label.setPreferredSize(new Dimension(75, 10));
        // Add the label to the panel
        add(label);
    }
}
