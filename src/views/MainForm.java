package views;

import com.formdev.flatlaf.util.StringUtils;
import data.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MainForm extends JFrame
{
    private Container container;

    private GridBagConstraints constraints;

    private UserInteractionListener userInteractionListener;

    private JFrame currentFrame = this;

    public MainForm() {
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;

        container = this.getContentPane();
        container.setLayout(new GridBagLayout());
    }

    public JTable getTable()
    {
        return table;
    }



    public void initializeComponents()
    {
        this.drawJTable();
        this.drawUi();
        this.drawSelections();
        this.addControlButtons();

        try {
            this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Untitled.png   ")).getImage());
        } catch (Exception ex) {
        }

        this.setTitle("Pizza");
        this.setResizable(false);
        this.pack();
    }

    private JTable table;


    void drawJTable() {
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 100));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.VERTICAL;

        container.add(scrollPane, constraints);

        constraints.gridheight = 1;
    }


    public Order getOrderFromFields() {
        return new Order(
                nameField.getText(),
                phoneField.getText(),
                addressField.getText(),
                style, toppings.toArray(new String[0]),
                size);
    }
    public String validateFields()
    {

        if (StringUtils.isEmpty(nameField.getText()))
        {
            return "Please input a name!";
        }

        if (StringUtils.isEmpty(addressField.getText()))
        {
            return "Please input an address!";
        }

        if (StringUtils.isEmpty(phoneField.getText()))
        {
            return "Please input your phone number!";
        }

        return null;
    }

    void addControlButtons() {
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = GridBagConstraints.HORIZONTAL;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 1;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        {
            var button = new JButton();
            button.setText("Save");
            panel.add(button);
            button.addActionListener(userInteractionListener.getAddRowButtonListener());
        }
        {
            var button = new JButton();
            button.setText("Delete");
            button.addActionListener(userInteractionListener.getDeleteRowsButtonListener());
            panel.add(button);
        }
        {
            statusLabel = new JLabel();
            statusLabel.setForeground(Color.CYAN);
            statusLabel.setText("Status");
            panel.add(statusLabel);
        }


        container.add(panel, constraints);

    }

    private JTextField nameField;

    private JTextField phoneField;

    private JLabel statusLabel;

    private JTextField addressField;

    private ArrayList<String> toppings = new ArrayList<>();
    private String size;
    private String style;

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

    void drawSelections() {
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;

        addSelection("Size", new String[]{
                "Small", "Medium", "Large"
        }, true);

        addSelection("Style", new String[]{
                "Thin", "Thick"
        }, true);

        addSelection("Toppings", new String[]{
                "Pepperoni", "Mushroom", "Anchovies"
        }, false);
    }

    void addSelection(String name, String[] radioButtons, boolean isRadioButton) {

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


            selectionButton.addActionListener(new ActionListener() {
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
            });

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
