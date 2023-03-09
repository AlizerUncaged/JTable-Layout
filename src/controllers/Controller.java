package controllers;

import data.TableModel;
import components.ControlledJTable;
import views.MainForm;
import interactions.UserInteractionListener;

public class Controller {
    private MainForm form;
    private ControlledJTable controlledJTable;
    private TableModel tableModel;

    public Controller(MainForm mainForm,
                      TableModel tableModel,
                      UserInteractionListener userInteractionListener,
                      ControlledJTable controlledJTable)
    {
        this.form = mainForm;
        this.controlledJTable = controlledJTable;
        this.form.setUserInteractionListener(userInteractionListener);
        this.tableModel = tableModel;

        userInteractionListener.setMainForm(this.form);
        userInteractionListener.setTableModel(this.tableModel);

    }

    public void start(){
        form.initializeComponents(controlledJTable);
        form.getTable().setModel(tableModel);
        form.setVisible(true);
    }
}
