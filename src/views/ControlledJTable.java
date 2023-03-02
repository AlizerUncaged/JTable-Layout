package views;

import javax.swing.*;

public class ControlledJTable extends JTable {
    private boolean _canSelect = true;
    public void setSelectable(boolean selectable)
    {
        _canSelect = selectable;
    }

    public boolean canSelect()
    {
        return _canSelect;
    }

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
    {
        if (!_canSelect)
            return;

        super.changeSelection(rowIndex, columnIndex, toggle, extend);
    }
}
