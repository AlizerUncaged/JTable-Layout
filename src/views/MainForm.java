package views;

import com.formdev.flatlaf.util.StringUtils;
import components.*;
import data.DataHandler;
import data.Order;
import data.TableModel;
import interactions.UserInteractionListener;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static utilities.ToggleUtility.*;

public class MainForm extends JFrame {


    private Container container;

    private FixedGridBagConstraints constraints;

    private UserInteractionListener userInteractionListener;

    private JTextField nameField;

    private JTextField phoneField;

    private JTextField addressField;

    private ControlledJTable table;

    private boolean isUpdateModeEnabled = false;

    private ControlButtons controlButtons;

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    private DataHandler dataHandler;

    public MainForm() {
        constraints = new FixedGridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;

        container = this.getContentPane();
        container.setLayout(new GridBagLayout());
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public ControlledJTable getTable() {
        return table;
    }

    public void initializeComponents(ControlledJTable table) {
        this.drawJTable(table);
        this.drawUi();
        this.drawSelections();
        this.addControlButtons();
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Untitled.png")).getImage());
        this.setTitle("Pizza");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public ControlButtons getControlButtons() {
        return controlButtons;
    }

    void drawJTable(ControlledJTable realTable) {
        table = realTable;

        var size = new Dimension(700, 100);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(size);

        constraints = constraints.setGridX(0)
                .setGridY(0)
                .setGridHeight(GridBagConstraints.REMAINDER)
                .setGridFill(GridBagConstraints.BOTH);

        container.add(scrollPane, constraints);

        constraints = constraints.setGridHeight(1);

        table.setUserInteractionsListener(this.userInteractionListener);
    }

    public void setUpdateMode(boolean updateMode, int targetRow) {
        isUpdateModeEnabled = updateMode;
        table.setSelectable(!updateMode);

        if (isUpdateModeEnabled) {
            controlButtons.getSaveButton().setText("Update");
            controlButtons.getDeleteButton().setText("Cancel");

            this.dataHandler.fillData(table.getTableModel().getOrderAt(targetRow));

            controlButtons.setStatus("Editing row " + targetRow + "!");
        }
        else {
            controlButtons.getSaveButton().setText("Save");
            controlButtons.getDeleteButton().setText("Delete");
            controlButtons.setStatus("");
        }
    }

    public boolean isUpdateMode() {
        return isUpdateModeEnabled;
    }

    void addControlButtons() {
        constraints = constraints.setConstraints(1,2,3,1,GridBagConstraints.HORIZONTAL);
        controlButtons = new ControlButtons(userInteractionListener);
        container.add(controlButtons, constraints);
        setUpdateMode(false, 0);
    }


    void drawUi() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nameField = createField("Name:", panel);
        phoneField = createField("Phone:", panel);
        addressField = createField("Address:", panel);

        constraints = constraints.setGridX(1)
                .setGridWidth(GridBagConstraints.REMAINDER);

        container.add(panel, constraints);
    }

    void drawSelections() {
        constraints = constraints.setGridWidth(1)
                .setGridFill(GridBagConstraints.BOTH)
                .setGridY(1)
                .setGridY(1);

        addSelection("Size", getDataHandler().getPizzaData().get("Size"), true);
        addSelection("Style", getDataHandler().getPizzaData().get("Style"), true);
        addSelection("Toppings", getDataHandler().getPizzaData().get("Toppings"), false);
    }


    void addSelection(String name, String[] radioButtons, boolean isRadioButton) {
        var selectionButtons = new SelectionButtons(name, radioButtons, isRadioButton);
        container.add(selectionButtons, constraints);

        this.getDataHandler().getToggleButtonsGroup().put(name, selectionButtons.getGeneratedToggleButtons());
        this.getDataHandler().getToggleButtonGroup().put(name, selectionButtons.getButtonGroup());
        constraints.gridx++;
    }

    public JTextField createField(String name, JPanel panel) {
        var labelAndInput = new LabelAndInput(name);
        panel.add(labelAndInput);
        return labelAndInput.getTextField();
    }

    public void setUserInteractionListener(UserInteractionListener userInteractionListener) {
        this.userInteractionListener = userInteractionListener;
    }

    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
