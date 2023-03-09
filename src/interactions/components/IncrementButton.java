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

    void handleAdd(Order order)
    {
        String validationResult = mainForm.getDataHandler().validateFields();
        if (validationResult != null) {
            JOptionPane.showMessageDialog(mainForm,
                    validationResult,
                    "Validation Error!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(order);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var order = mainForm.getDataHandler().getOrderFromFields();

        if (mainForm.isUpdateMode()) {
            handleUpdate(order);
        } else {
            handleAdd(order);
        }

        // Always clear.
        mainForm.getDataHandler().fillData(new Order());
    }
}
