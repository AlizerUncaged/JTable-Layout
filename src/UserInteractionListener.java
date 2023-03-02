import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInteractionListener {
    private TableModel tableModel;
    private MainForm mainForm;

    public AddRowButton getAddRowButtonListener() {
        return new AddRowButton(tableModel, mainForm);
    }
    public DeleteRowsButton getDeleteRowsButtonListener() {
        return new DeleteRowsButton(tableModel, mainForm);
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public class DeleteRowsButton implements  ActionListener{

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

    public class AddRowButton implements ActionListener {

        private TableModel tableModel;
        private MainForm mainForm;

        public AddRowButton(TableModel tableModel, MainForm mainForm) {
            this.tableModel = tableModel;
            this.mainForm = mainForm;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                tableModel.addRow(mainForm.getOrderFromFields());
        }
    }
}
