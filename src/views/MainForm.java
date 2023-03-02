package views;

import com.formdev.flatlaf.util.StringUtils;
import data.Order;
import data.TableModel;
import interactions.UserInteractionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

import static utilities.ToggleUtility.*;

public class MainForm extends JFrame {

    private Container container;

    private GridBagConstraints constraints;

    private UserInteractionListener userInteractionListener;

    private JFrame currentFrame = this;

    private JTextField nameField;

    private JTextField phoneField;

    private JLabel statusLabel;

    private JTextField addressField;

    private ArrayList<String> toppings = new ArrayList<>();

    private String size;

    private String style;
    public MainForm() {
        constraints = new GridBagConstraints();
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

    public JTable getTable() {
        return table;
    }


    public void initializeComponents(JTable table) {
        this.drawJTable(table);
        this.drawUi();
        this.drawSelections();
        this.addControlButtons();

        try {
            this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Untitled.png   ")).getImage());
        } catch (Exception ex) {
        }

        this.setTitle("Pizza");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private JTable table;


    void drawJTable(JTable realTable) {
        table = realTable;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.addMouseListener(userInteractionListener.getTableMouseHandler());
        var size = new Dimension(700, 100);

        // table.setPreferredSize(size);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(size);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;

        container.add(scrollPane, constraints);

        constraints.gridheight = 1;
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
            setToggleButtonStates( order.getToggleButtonsState(), toggleButtonsGroup);

    }
    public void setUpdateMode(boolean updateMode, int targetRow) {
        isUpdateModeEnabled = updateMode;
        var controlledTable = (ControlledJTable) table;
        controlledTable.setSelectable(!updateMode);
        if (isUpdateModeEnabled) {
            this.setStatus("Copied row " + targetRow + "!");
            saveButton.setText("Update");
            deleteButton.setText("Delete");

            fillData(((TableModel) table.getModel()).getOrderAt(targetRow));
        }
        else {
            this.setStatus("");
            saveButton.setText("Save");
            deleteButton.setText("Delete");
        }
    }

    private boolean isUpdateModeEnabled = false;

    public boolean isUpdateMode() {
        return isUpdateModeEnabled;
    }
    private JButton saveButton;

    private JButton deleteButton;

    void addControlButtons() {
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        {
            saveButton = new JButton();
            panel.add(saveButton);
            saveButton.addActionListener(userInteractionListener.getAddRowButtonListener());
        }
        {
            deleteButton = new JButton();
            deleteButton.addActionListener(userInteractionListener.getDeleteRowsButtonListener());
            panel.add(deleteButton);
        }
        {
            statusLabel = new JLabel();
            statusLabel.setForeground(Color.CYAN);
            statusLabel.setText("");
            panel.add(statusLabel);
        }

        container.add(panel, constraints);

        setUpdateMode(false, 0);
    }


    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    void drawUi() {

        constraints.gridx = 1;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nameField = createField("Name:", panel);
        phoneField = createField("Phone:", panel);
        addressField = createField("Address:", panel);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        container.add(panel, constraints);


    }

    private Map<String, String[]> pizzaData =
            new HashMap<>();

    void drawSelections() {
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;


        addSelection("Size", pizzaData.get("Size"), true);

        addSelection("Style", pizzaData.get("Style"), true);

        addSelection("Toppings", pizzaData.get("Toppings"), false);
    }

    public Map<String, ArrayList<JToggleButton>>
            toggleButtonsGroup = new Hashtable<>();

    void addSelection(String name, String[] radioButtons, boolean isRadioButton) {
        ArrayList<JToggleButton> toggles = new ArrayList<>();
        var buttonGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(100, 200));

        container.add(panel, constraints);

        var localPanelConstraints = new GridBagConstraints();
        localPanelConstraints.gridx = 0;
        localPanelConstraints.gridy = 0;
        localPanelConstraints
                .fill = GridBagConstraints.BOTH;
        for (var i : radioButtons) {
            JToggleButton selectionButton;
            if (isRadioButton)
                selectionButton = new JRadioButton();
            else
                selectionButton = new JCheckBox();

            toggles.add(selectionButton);

          /*  selectionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (name) {
                        case "Toppings":
                            if (selectionButton.isSelected()) {
                                toppings.add(selectionButton.getText());
                            } else {
                                toppings.removeAll(Collections.singleton(selectionButton.getText()));
                            }
                            break;
                        case "Size":
                            if (selectionButton.isSelected()) {
                                size = selectionButton.getText();
                            }
                            break;
                        case "Style":
                            if (selectionButton.isSelected()) {
                                style = selectionButton.getText();
                            }
                            break;
                    }
                }
            });*/

            selectionButton.setText(i);
            panel.add(selectionButton, localPanelConstraints);

            if (isRadioButton)
                buttonGroup.add(selectionButton);

            selectionButton.setSelected(true);

            switch (name) {
                case "Toppings":
                    toppings.add(selectionButton.getText());
                    break;
                case "Size":
                    size = selectionButton.getText();
                    break;
                case "Style":
                    style = selectionButton.getText();
                    break;
            }

            localPanelConstraints.gridy++;
        }

        toggleButtonsGroup.put(name, toggles);

        constraints.gridx++;
    }

    public JTextField createField(String name, JPanel panel) {
        // Make area for JTable.
        // constraints.gridx = 1;
        // constraints.fill = GridBagConstraints.VERTICAL;
        var flowLayout = new FlowLayout();
        var localHorizontalPanel = new JPanel();
        localHorizontalPanel.setLayout(flowLayout);
        localHorizontalPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        localHorizontalPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        constraints
                .fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel();
        label.setText(name);
        label.setPreferredSize(new Dimension(75, 10));
        localHorizontalPanel.add(label, constraints);

        // constraints.gridx++;

        JTextField textField = new JTextField();
        textField.setColumns(15);
        localHorizontalPanel.add(textField, constraints);
        panel.add(localHorizontalPanel);
        return textField;
    }


    public void setUserInteractionListener(UserInteractionListener userInteractionListener) {
        this.userInteractionListener = userInteractionListener;
    }
}
