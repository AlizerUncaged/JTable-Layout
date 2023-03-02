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
    }

    void handleAdd(Order order)
    {
        String validationResult = mainForm.validateFields();
        if (validationResult != null) {
            JOptionPane.showMessageDialog(mainForm,
                    validationResult,
                    "Validation Error!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(order);

        mainForm.fillData(new Order());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var order = mainForm.getOrderFromFields();
        if (mainForm.isUpdateMode()) {
            handleUpdate(order);
        } else {
            handleAdd(order);
        }
        // mainForm.setStatus("Added order from " + order.getName() + "!");
    }
}
