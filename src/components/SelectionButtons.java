package components;

import interactions.RadioButtonValidationListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectionButtons  extends JPanel {
    /**
     * An ArrayList of JToggleButtons that contains all of the buttons generated by the constructor.
     */
    private ArrayList<JToggleButton> generatedToggleButtons;

    @Override
    public String getName() {
        return name;
    }

    private String name;
    private int requiredChecked;

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    private ButtonGroup buttonGroup;


    public int checkedCount() {
        int count = 0;
        for (var i : generatedToggleButtons) {
            if (i.isSelected())
                count++;
        }
        return count;
    }

    private JLabel statusLabel;

    /**
     * Constructs a new SelectionButtons object with the specified name, radio buttons, and type.
     *
     * @param name          the name of the panel, which will be displayed as a titled border
     * @param radioButtons  an array of strings representing the text for each button
     * @param isRadioButton a boolean that determines whether the buttons will be radio buttons or check boxes
     */
    public SelectionButtons(String name, String[] radioButtons, boolean isRadioButton, int requiredChecked) {
        this.name = name;

        this.statusLabel = new JLabel();
        this.statusLabel.setForeground(new Color(231, 76, 60));

        this.requiredChecked = requiredChecked;
        generatedToggleButtons = new ArrayList<>();

        buttonGroup = new ButtonGroup();
        this.setBorder(BorderFactory.createTitledBorder(name));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(100, 200));

        var localPanelConstraints = new GridBagConstraints();
        localPanelConstraints.gridx = 0;
        localPanelConstraints.gridy = 0;
        localPanelConstraints.fill = GridBagConstraints.BOTH;

        // iterate through the array of radio button texts
        for (var i : radioButtons) {
            JToggleButton selectionButton;
            if (isRadioButton)
                selectionButton = new JRadioButton();
            else
                selectionButton = new JCheckBox();

            generatedToggleButtons.add(selectionButton);

            selectionButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            selectionButton.setText(i);

            this.add(selectionButton, localPanelConstraints);

            if (isRadioButton)
                buttonGroup.add(selectionButton);

            localPanelConstraints.gridy++;
        }

        // Add validator.
        var radioButtonValidation =
                new RadioButtonValidationListener(this, requiredChecked);


        this.statusLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(this.statusLabel);
    }

    public void setStatus(String message)
    {
        statusLabel.setText(message);
    }

    /**
     * Returns an ArrayList of all the generated JToggleButtons.
     *
     * @return an ArrayList of all the generated JToggleButtons
     */
    public ArrayList<JToggleButton> getGeneratedToggleButtons() {
        return generatedToggleButtons;
    }

    public int getRequiredChecked() {
        return requiredChecked;
    }
}
