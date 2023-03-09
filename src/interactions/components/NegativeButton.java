package interactions.components;

import views.MainForm;
import data.Order;
import data.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NegativeButton implements ActionListener {

    private TableModel tableModel;

    private MainForm mainForm;

    public NegativeButton(TableModel tableModel, MainForm mainForm) {
        this.tableModel = tableModel;
        this.mainForm = mainForm;
    }

    void handleUpdateMode() {
        // disable update mode because this is the Cancel button now
        mainForm.setUpdateMode(false, 0);
        mainForm.fillData(new Order());
        mainForm.getControlButtons().setStatus("");
    }

    void handleNormalMode() {
        // handle normal mode since this is the Delete button now

        var selectedRow = mainForm.getTable().getSelectedRows();

        int res = JOptionPane.showConfirmDialog(mainForm, "Delete " + selectedRow.length + " rows?", "Confirm Delete Rows",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (res != JOptionPane.OK_OPTION)
        {
            return;
        }

        if (selectedRow.length == 0)
            tableModel.removeRow(0);
        else {
            ArrayList<Order> selectedOrders = new ArrayList<>();
            for (Integer i : selectedRow) {
                selectedOrders.add(tableModel.getOrderAt(i));
            }
            for (var i : selectedOrders) {
                tableModel.removeRow(i);
            }
        }

        mainForm.getControlButtons().setStatus("Removed " + selectedRow.length + " row" + (selectedRow.length == 1 ? "" : "s") + "!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainForm.isUpdateMode()) {
            handleUpdateMode();
        } else {
            handleNormalMode();
        }
    }
}
