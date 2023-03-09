package controllers;

import data.DataHandler;
import data.TableModel;
import components.ControlledJTable;
import views.MainForm;
import interactions.UserInteractionListener;

/**
 * This class represents a Controller that coordinates the interaction between the MainForm,
 * ControlledJTable, DataHandler, UserInteractionListener, etc.
 * The Controller sets up the necessary connections between these components and provides a
 * method to start the application.
 */
public class Controller {

    // Components used by the Controller
    private MainForm form;
    private ControlledJTable controlledJTable;
    private DataHandler dataHandler;
    private TableModel tableModel;

    /**
     * Constructs a new Controller object with the given MainForm, TableModel, UserInteractionListener, ControlledJTable, and DataHandler.
     * The Controller sets up the connections between these components.
     */
    public Controller(MainForm mainForm,
                      TableModel tableModel,
                      UserInteractionListener userInteractionListener,
                      ControlledJTable controlledJTable,
                      DataHandler dataHandler)
    {
        this.form = mainForm;
        this.controlledJTable = controlledJTable;
        this.dataHandler = dataHandler;
        this.tableModel = tableModel;

        // Set up connections between components
        dataHandler.setMainForm(this.form);
        mainForm.setDataHandler(this.dataHandler);
        mainForm.setUserInteractionListener(userInteractionListener);
        userInteractionListener.setMainForm(this.form);
        userInteractionListener.setTableModel(this.tableModel);
    }

    /**
     * Starts the application by initializing the components, setting the table model, and making the main form visible.
     */
    public void start(){
        form.initializeComponents(controlledJTable);
        form.getTable().setModel(tableModel);
        form.setVisible(true);
    }

}
