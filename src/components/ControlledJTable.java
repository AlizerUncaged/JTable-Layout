package components;

import data.TableModel;
import interactions.UserInteractionListener;

import javax.swing.*;

public class ControlledJTable extends JTable {
    private boolean _canSelect = true;

    public ControlledJTable()
    {
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public void setUserInteractionsListener(UserInteractionListener userInteractionListener)
    {
        this.addMouseListener(userInteractionListener.getTableMouseHandler());
    }

    public void setSelectable(boolean selectable)
    {
        _canSelect = selectable;
    }

    public boolean canSelect()
    {
        return _canSelect;
    }

    public TableModel getTableModel() {
        return (TableModel) this.getTableModel();
    }

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
    {
        if (!_canSelect)
            return;

        super.changeSelection(rowIndex, columnIndex, toggle, extend);
    }
}
