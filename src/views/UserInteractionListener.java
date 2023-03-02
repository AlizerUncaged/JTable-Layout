package views;

import data.TableModel;
import interactions.AddRowButton;
import interactions.DeleteRowsButton;

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


}
