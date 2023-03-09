package components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectionButtons  extends JPanel {

    public ArrayList<JToggleButton> getGeneratedToggleButtons() {
        return generatedToggleButtons;
    }

    private ArrayList<JToggleButton> generatedToggleButtons;

    public SelectionButtons(String name, String[] radioButtons, boolean isRadioButton)
    {
        generatedToggleButtons = new ArrayList<>();

        var buttonGroup = new ButtonGroup();
        this.setBorder(BorderFactory.createTitledBorder(name));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(100, 200));

        var localPanelConstraints = new GridBagConstraints();
        localPanelConstraints.gridx = 0;
        localPanelConstraints.gridy = 0;
        localPanelConstraints.fill = GridBagConstraints.BOTH;

        for (var i : radioButtons) {
            JToggleButton selectionButton;
            if (isRadioButton)
                selectionButton = new JRadioButton();
            else
                selectionButton = new JCheckBox();

            generatedToggleButtons.add(selectionButton);

            selectionButton.setText(i);
            this.add(selectionButton, localPanelConstraints);

            if (isRadioButton)
                buttonGroup.add(selectionButton);

            localPanelConstraints.gridy++;
        }
    }
}
