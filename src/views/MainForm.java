package views;

import com.formdev.flatlaf.util.StringUtils;
import components.ControlButtons;
import components.ControlledJTable;
import components.FixedGridBagConstraints;
import components.SelectionButtons;
import data.Order;
import data.TableModel;
import interactions.UserInteractionListener;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static utilities.ToggleUtility.*;

public class MainForm extends JFrame {

    public Map<String, ArrayList<JToggleButton>>
            toggleButtonsGroup = new Hashtable<>();

    private Container container;

    private FixedGridBagConstraints constraints;

    private UserInteractionListener userInteractionListener;

    private JTextField nameField;

    private JTextField phoneField;

    private JTextField addressField;

    private ControlledJTable table;

    private boolean isUpdateModeEnabled = false;

    private ControlButtons controlButtons;
    public MainForm() {
        constraints = new FixedGridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;

        container = this.getContentPane();
        container.setLayout(new GridBagLayout());

        pizzaData.put("Size", new String[]{
                "Small", "Medium", "Large"
        });

        pizzaData.put("Style", new String[]{
                "Thin", "Thick"
        });

        pizzaData.put("Toppings", new String[]{
                "Pepperoni", "Mushroom", "Anchovies"
        });
    }

    public ControlledJTable getTable() {

        return table;
    }


    public void initializeComponents(ControlledJTable table) {
        this.drawJTable(table);
        this.drawUi();
        this.drawSelections();
        this.addControlButtons();
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Untitled.png   ")).getImage());
        this.setTitle("Pizza");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public ControlButtons getControlButtons() {
        return controlButtons;
    }

    void drawJTable(ControlledJTable realTable) {
        var size = new Dimension(700, 100);
        table = realTable;

        // table.setPreferredSize(size);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(size);

        constraints = constraints.setGridX(0)
                .setGridY(0)
                .setGridHeight(GridBagConstraints.REMAINDER)
                .setGridFill(GridBagConstraints.BOTH);

        container.add(scrollPane, constraints);

        constraints = constraints.setGridHeight(1);
    }

    public Order getOrderFromFields() {
        return new Order(
                nameField.getText(),
                phoneField.getText(),
                addressField.getText(),
                getActiveToggle("Style", toggleButtonsGroup, pizzaData),
                getActiveToggle("Toppings", toggleButtonsGroup, pizzaData),
                getActiveToggle("Size", toggleButtonsGroup, pizzaData),
                getToggleButtonStates(toggleButtonsGroup));
    }

    public String validateFields() {

        if (StringUtils.isEmpty(nameField.getText())) {
            return "Please input a name!";
        }

        if (StringUtils.isEmpty(addressField.getText())) {
            return "Please input an address!";
        }

        if (StringUtils.isEmpty(phoneField.getText())) {
            return "Please input your phone number!";
        }

        return null;
    }

    public void fillData(Order order) {
        nameField.setText(order.getName());
        addressField.setText(order.getAddress());
        phoneField.setText(order.getPhone());

        if (!order.getToggleButtonsState().isEmpty())
            setToggleButtonStates(order.getToggleButtonsState(), toggleButtonsGroup);
        else {
            // Generate empty toggles.
            var toggles = setToggleButtonStates(toggleButtonsGroup, false);
            setToggleButtonStates(toggles, toggleButtonsGroup);
        }
    }
    public void setUpdateMode(boolean updateMode, int targetRow) {
        isUpdateModeEnabled = updateMode;
        var controlledTable = (ControlledJTable) table;
        controlledTable.setSelectable(!updateMode);

        if (isUpdateModeEnabled) {
            controlButtons.getSaveButton().setText("Update");
            controlButtons.getDeleteButton().setText("Cancel");

            fillData(((TableModel) table.getModel()).getOrderAt(targetRow));

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

    private Map<String, String[]> pizzaData =
            new HashMap<>();

    void drawSelections() {
        constraints = constraints.setGridWidth(1)
                .setGridY(1)
                .setGridY(1);

        addSelection("Size", pizzaData.get("Size"), true);

        addSelection("Style", pizzaData.get("Style"), true);

        addSelection("Toppings", pizzaData.get("Toppings"), false);
    }


    void addSelection(String name, String[] radioButtons, boolean isRadioButton) {
        var selectionButtons = new SelectionButtons(name, radioButtons, isRadioButton);
        container.add(selectionButtons, constraints);
        toggleButtonsGroup.put(name, selectionButtons.getGeneratedToggleButtons());
        constraints.gridx++;
    }

    public JTextField createField(String name, JPanel panel) {
        var flowLayout = new FlowLayout();
        var localHorizontalPanel = new JPanel();
        localHorizontalPanel.setLayout(flowLayout);
        localHorizontalPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        localHorizontalPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        constraints.fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel();
        label.setText(name);
        label.setPreferredSize(new Dimension(75, 10));
        localHorizontalPanel.add(label, constraints);
        JTextField textField = new JTextField();
        textField.setColumns(15);
        localHorizontalPanel.add(textField, constraints);
        panel.add(localHorizontalPanel);

        return textField;
    }

    public void setUserInteractionListener(UserInteractionListener userInteractionListener) {
        this.userInteractionListener = userInteractionListener;
        this.table.setUserInteractionsListener(this.userInteractionListener);
    }
}
