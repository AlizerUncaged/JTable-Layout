package interactions.components;

import views.MainForm;
import data.Order;
import data.TableModel;

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
    }

    void handleNormalMode() {
        // handle normal mode since this is the Delete button now
        var selectedRow = mainForm.getTable().getSelectedRows();

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
