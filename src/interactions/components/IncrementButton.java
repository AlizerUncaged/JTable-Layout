package interactions.components;

import data.Order;
import data.TableModel;
import views.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncrementButton implements ActionListener {

    private TableModel tableModel;

    private views.MainForm mainForm;

    public IncrementButton(TableModel tableModel, MainForm mainForm) {
        this.tableModel = tableModel;
        this.mainForm = mainForm;
    }

    void handleUpdate(Order order)
    {
        tableModel.setOrderAt(order, mainForm.getTable().getSelectedRow());
        // Disable update mode.
        mainForm.setUpdateMode(false, 0);
    }

    boolean handleAdd(Order order) {
        var validationResult = mainForm.getDataHandler().validateFields();
        if (!validationResult) {
       /*     JOptionPane.showMessageDialog(mainForm,
                    validationResult,
                    "Validation Error!",
                    JOptionPane.ERROR_MESSAGE);

        */
            return false;
        }

        tableModel.addRow(order);
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainForm.clearValidationErrors();

        var order = mainForm.getDataHandler()
                .getOrderFromFields();

        if (mainForm.isUpdateMode()) {
            handleUpdate(order);
        } else {
            if (!handleAdd(order)) {
                return;
            }
        }

        mainForm.getDataHandler().fillData(new Order());
    }
}
