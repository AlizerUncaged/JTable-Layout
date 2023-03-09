package components;

import java.awt.*;

/**
 * This class extends GridBagConstraints and provides methods to set
 * grid constraints for components in a GridBagLayout.
 */
public class FixedGridBagConstraints extends GridBagConstraints {

    /**
     * Sets all the constraints for a component and returns the instance of FixedGridBagConstraints.
     * @param gridX the component's grid x position
     * @param gridY the component's grid y position
     * @param gridWidth the number of columns that the component occupies
     * @param gridHeight the number of rows that the component occupies
     * @param fill how the component is resized within its cell
     * @return the instance of FixedGridBagConstraints
     */
    public FixedGridBagConstraints setConstraints(int gridX, int gridY, int gridWidth, int gridHeight, int fill) {
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
        this.fill = fill;
        return this;
    }

    /**
     * Sets the component's grid x position and returns the instance of FixedGridBagConstraints.
     * @param gridX the component's grid x position
     * @return the instance of FixedGridBagConstraints
     */
    public FixedGridBagConstraints setGridX(int gridX) {
        this.gridx = gridX;
        return this;
    }

    /**
     * Sets the component's grid y position and returns the instance of FixedGridBagConstraints.
     * @param gridY the component's grid y position
     * @return the instance of FixedGridBagConstraints
     */
    public FixedGridBagConstraints setGridY(int gridY) {
        this.gridy = gridY;
        return this;
    }

    /**
     * Sets the number of columns that the component occupies and returns the instance of FixedGridBagConstraints.
     * @param width the number of columns that the component occupies
     * @return the instance of FixedGridBagConstraints
     */
    public FixedGridBagConstraints setGridWidth(int width) {
        this.gridwidth = width;
        return this;
    }

    /**
     * Sets the number of rows that the component occupies and returns the instance of FixedGridBagConstraints.
     * @param height the number of rows that the component occupies
     * @return the instance of FixedGridBagConstraints
     */
    public FixedGridBagConstraints setGridHeight(int height) {
        this.gridheight = height;
        return this;
    }

    /**
     * Sets how the component is resized within its cell and returns the instance of FixedGridBagConstraints.
     * @param fill how the component is resized within its cell
     * @return the instance of FixedGridBagConstraints
     */
    public FixedGridBagConstraints setGridFill(int fill) {
        this.fill = fill;
        return this;
    }
}
