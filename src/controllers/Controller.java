package controllers;

import data.TableModel;
import views.MainForm;
import views.UserInteractionListener;

public class Controller {
    private MainForm form;
    private TableModel tableModel;

    public Controller(MainForm mainForm,
                      TableModel tableModel,
                      UserInteractionListener userInteractionListener)
    {
        this.form = mainForm;
        this.form.setUserInteractionListener(userInteractionListener);
        this.tableModel = tableModel;

        userInteractionListener.setMainForm(this.form);
        userInteractionListener.setTableModel(this.tableModel);

    }

    public void start(){
        form.initializeComponents();
        form.getTable().setModel(tableModel);
        form.setVisible(true);
    }
}
