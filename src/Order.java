public class Order {
    private final String name;
    private final String phone;
    private final String address;
    private final String style;
    private final String[] toppings;
    private final String size;

    public Order(String name, String phone, String address, String style, String[] toppings, String size){

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.style = style;
        this.toppings = toppings;
        this.size = size;
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

    public String[] getToppings() {
        return toppings;
    }

    public String getSize() {
        return size;
    }
}
/*
                         nameField.getText(), phoneField.getText(), addressField.getText(),
                            style, String.join(", ", toppings), size
 */