package components;

import com.formdev.flatlaf.util.StringUtils;
import interactions.KeyValidationListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Represents a label and text field component that can be used in Swing user interfaces.
 */
public class LabelAndInput extends JPanel {

    private FlowLayout flowLayout;

    private BoxLayout verticalFlow;
    private JLabel label, statusLabel;
    private JPanel innerPanel;
    private JFormattedTextField textField;
    private String name;
    private MaskFormatter formatter;

    public String getValidationErrorText() {
        return validationErrorText;
    }

    private String validationErrorText;

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
    public JFormattedTextField getTextField() {
        return textField;
    }

    public String getActualText() {

        try {
            textField.commitEdit();
        } catch (ParseException e) {
            return null;
        }

        return textField.getFormatter() == null ? textField.getText() : textField.getValue().toString();
    }
    public void setText(String t) {
        textField.setText(t);
    }

    /**
     * Constructs a new label and text field component with the provided name for the label.
     *
     * @param name the name for the label component
     */
    public LabelAndInput(String name, MaskFormatter formatter, String validationErrorText) {
        this.name = name;
        this.formatter = formatter;
        this.validationErrorText = validationErrorText;

        // Create a new label and flow layout for the panel
        innerPanel = new JPanel();
        innerPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        add(innerPanel);

        label = new JLabel();
        statusLabel = new JLabel("", SwingConstants.LEFT);
        flowLayout = new FlowLayout();
        verticalFlow = new BoxLayout(this, BoxLayout.Y_AXIS);

        innerPanel.setLayout(flowLayout);

        // Set the layout, alignment, and component orientation of the panel
        setLayout(verticalFlow);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setAlignmentX(JPanel.LEFT_ALIGNMENT);

        // Render the label and text field components
        renderLabel();
        renderField();
        renderStatusLabel();
    }

    public MaskFormatter getFormatter() {
        return formatter;
    }

    public boolean validateInput() {
        if (StringUtils.isEmpty(getActualText())) {
            setStatus(validationErrorText);
            return false;
        }
        setStatus(" ");
        return true;
    }

    /**
     * Renders the text field component and adds it to the panel.
     */
    void renderField() {
        // Create a new text field and set its size
        textField = new JFormattedTextField(formatter);
        textField.addKeyListener(new KeyValidationListener(this));
        textField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        textField.setColumns(15);
        // Add the text field to the panel
        innerPanel.add(textField);
    }

    /**
     * Renders the label component and adds it to the panel.
     */
    void renderLabel() {
        // Set the text of the label to the provided name and set its size
        label.setText(name);
        label.setPreferredSize(new Dimension(75, 10));
        // Add the label to the panel
        innerPanel.add(label);
    }

    public void setStatus(String t){
        statusLabel.setText(t);
    }

    /**
     * Renders the label component and adds it to the panel.
     */
    void renderStatusLabel() {
        // Set the text of the label to the provided name and set its size
        statusLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        statusLabel.setBorder(new CompoundBorder(this.getBorder(), new EmptyBorder(0,20,0,0)));
        statusLabel.setForeground(new Color(231, 76, 60));
        statusLabel.setText(" ");
        // Add the label to the panel
        this.add(statusLabel);
    }
}
