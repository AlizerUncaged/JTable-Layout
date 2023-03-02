package interactions;

import views.MainForm;
import data.Order;
import data.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteRowsButton implements ActionListener {

    private TableModel tableModel;
    private MainForm mainForm;

    public DeleteRowsButton(TableModel tableModel, MainForm mainForm) {
        this.tableModel = tableModel;
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
}
