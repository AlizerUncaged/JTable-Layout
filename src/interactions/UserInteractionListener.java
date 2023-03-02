package interactions;

import data.TableModel;
import interactions.components.IncrementButton;
import interactions.components.NegativeButton;
import views.MainForm;

public class UserInteractionListener {
    private TableModel tableModel;
    private MainForm mainForm;

    public IncrementButton getAddRowButtonListener() {
        return new IncrementButton(tableModel, mainForm);
    }

    public NegativeButton getDeleteRowsButtonListener() {
        return new NegativeButton(tableModel, mainForm);
    }

    public JTableMouseEvents getTableMouseHandler()
    {
        return new JTableMouseEvents(mainForm);
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }


}
