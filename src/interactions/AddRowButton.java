package interactions;

import data.TableModel;
import views.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRowButton implements ActionListener {

    private TableModel tableModel;
    private views.MainForm mainForm;

    public AddRowButton(TableModel tableModel, MainForm mainForm) {
        this.tableModel = tableModel;
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tableModel.addRow(mainForm.getOrderFromFields());
    }
}
