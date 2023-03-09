package data;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final String name;
    private final String phone;
    private final String address;
    private final String style;
    private final String toppings;
    private final String size;
    private Map<String, ArrayList<Boolean>> toggleButtonsState;

    public Order()
    {
        this("", "", "", "", "", "", new HashMap<>());
    }

    public Order(String name,
                 String phone,
                 String address,
                 String style,
                 String toppings,
                 String size,
                 Map<String, ArrayList<Boolean>> toggleButtonsState){

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.style = style;
        this.toppings = toppings;
        this.size = size;
        this.toggleButtonsState = toggleButtonsState;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getStyle() {
        return style;
    }

    public String getToppings() {
        return toppings;
    }

    public String getSize() {
        return size;
    }

    public Map<String, ArrayList<Boolean>> getToggleButtonsState() {
        return toggleButtonsState;
    }
}
/*
                         nameField.getText(), phoneField.getText(), addressField.getText(),
                            style, String.join(", ", toppings), size
 */