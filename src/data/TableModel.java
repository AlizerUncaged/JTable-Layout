package data;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    String[] columnNames = new String[]{
            "Name", "Phone", "Address", "Size", "Toppings", "Style"
    };

    ArrayList<Order> orders = new ArrayList<>();


    public void addRow(Order order) {
        orders.add(order);
        this.fireTableDataChanged();
        ///this.fireTableCellUpdated(orders.size() - 1, orders.size() - 1);
    }

    public void removeRow(int rowIndex){
        orders.remove(rowIndex);
        this.fireTableDataChanged();
    }

    public void removeRow(Order order){
        orders.remove(order);
        this.fireTableDataChanged();
    }

    /**
     * Returns data type of the column specified by its index.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Order getOrderAt(int rowIndex){
        return orders.get(rowIndex);
    }

    public void setOrderAt(Order order, int rowIndex)
    {
        orders.set(rowIndex, order);
        this.fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var selectedOrder = orders.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return selectedOrder.getName();
            case 1:
                return selectedOrder.getPhone();
            case 2:
                return selectedOrder.getAddress();
            case 3:
                return selectedOrder.getSize();
            case 4:
                return selectedOrder.getToppings();
            case 5:
                return selectedOrder.getStyle();
        }

        return null;
    }
}
/*

            String[] columnNames = new String[]{
                    "Name", "Phone", "Address", "Size", "Toppings", "Style"
            };

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }
 */