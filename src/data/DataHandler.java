package data;

import com.formdev.flatlaf.util.StringUtils;
import views.MainForm;

import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static utilities.ToggleUtility.*;
import static utilities.ToggleUtility.setToggleButtonStates;

public class DataHandler {

    public Map<String, ArrayList<JToggleButton>> getToggleButtonsGroup() {
        return toggleButtonsGroup;
    }

    public Map<String, String[]> getPizzaData() {
        return pizzaData;
    }

    private Map<String, ArrayList<JToggleButton>>
            toggleButtonsGroup;

    public Map<String, ButtonGroup> getToggleButtonGroup() {
        return toggleButtonGroup;
    }

    private Map<String, ButtonGroup>
            toggleButtonGroup;


    private Map<String, String[]> pizzaData;

    private MainForm form;

    public DataHandler() {
        toggleButtonsGroup = new Hashtable<>();
        toggleButtonGroup = new HashMap<>();

        pizzaData = new HashMap<>();
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

    public boolean validateFields() {
        boolean isErrorable = true;

        if (!form.getNameField().validateInput()){
            isErrorable =  false;
        }

        if (!form.getPhoneField().validateInput()){
            isErrorable =  false;
        }

        if (!form.getAddressField().validateInput()){
            isErrorable =  false;
        }

        for (var i : form.getSelectionButtons()) {
            if (i.checkedCount() < i.getRequiredChecked())
            {
                i.setStatus("Select one!");
                isErrorable = false;
            }
        }


        return isErrorable;
    }

    public void fillData(Order order) {
        this.form.getNameField().setText(order.getName());
        this.form.getAddressField().setText(order.getAddress());
        this.form.getPhoneField().setText(order.getPhone());

        if (!order.getToggleButtonsState().isEmpty())
            setToggleButtonStates(order.getToggleButtonsState(), toggleButtonsGroup);
        else {
            // Generate empty toggles.
            setToggleButtonStates(toggleButtonsGroup, false);

            for(var i : toggleButtonGroup.values())
                i.clearSelection();
        }
    }


    public void setMainForm(MainForm form) {
        this.form = form;
    }

    public Order getOrderFromFields() {

        return new Order(
                this.form.getNameField().getActualText(),
                this.form.getPhoneField().getActualText(),
                this.form.getAddressField().getActualText(),
                getActiveToggle("Style", toggleButtonsGroup, pizzaData),
                getActiveToggle("Toppings", toggleButtonsGroup, pizzaData),
                getActiveToggle("Size", toggleButtonsGroup, pizzaData),
                getToggleButtonStates(toggleButtonsGroup));
    }
}
