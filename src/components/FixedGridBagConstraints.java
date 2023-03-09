package components;

import java.awt.*;

public class FixedGridBagConstraints extends GridBagConstraints {

    public FixedGridBagConstraints setConstraints(int gridX,
                                                  int gridY,
                                                  int gridWidth,
                                                  int gridHeight,
                                                  int fill) {
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
        this.fill = fill;
        return this;
    }

    public FixedGridBagConstraints setGridX(int gridX) {
        this.gridx = gridX;
        return this;
    }

    public FixedGridBagConstraints setGridY(int gridY) {
        this.gridy = gridY;
        return this;
    }

    public FixedGridBagConstraints setGridWidth(int width) {
        this.gridwidth = width;
        return this;
    }

    public FixedGridBagConstraints setGridHeight(int height) {
        this.gridheight = height;
        return this;
    }

    public FixedGridBagConstraints setGridFill(int fill) {
        this.fill = fill;
        return this;
    }
}
