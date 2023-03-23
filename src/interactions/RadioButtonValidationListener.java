package interactions;

import components.SelectionButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RadioButtonValidationListener {
    private SelectionButtons selectionButtons;
    private int checkCount;

    public RadioButtonValidationListener(SelectionButtons selectionButtons, int checkCount) {
        this.selectionButtons = selectionButtons;
        this.checkCount = checkCount;

        // Automatic validation.
        for(var i: this.selectionButtons.getGeneratedToggleButtons())
        {
            i.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  validateInput();
                }
            });
        }
    }

    public boolean validateInput()
    {
        int currentChecks = 0;
        for(var i: this.selectionButtons.getGeneratedToggleButtons())
        {
            if (i.isSelected())
                currentChecks++;
        }
        if (currentChecks >= checkCount)
        {
            selectionButtons.setStatus(" ");
            return true;
        }

        selectionButtons.setStatus("Select at least " + checkCount);
        return false;
    }
}
