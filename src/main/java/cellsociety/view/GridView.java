package cellsociety.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

/**
 * Responsible for representing the view of a grid.
 *
 * @author Jason Qiu
 */
class GridView {

  GridPane gridPane;
  CellView[][] cellViewGrid;
  int numRows;
  int numColumns;

  /**
   * Creates a new fixed array of CellView instances parented to a GridPane instance.
   *
   * @param numRows    the number of rows of the grid view.
   * @param numColumns the number of columns of the grid view.
   */
  public GridView(int numRows, int numColumns) {
    this.numRows = numRows;
    this.numColumns = numColumns;

    initializeGrid(numRows, numColumns);
  }

  /**
   * Initializes the gridPane and cellViewGrid at the same time.
   *
   * @param numRows    the number of rows.
   * @param numColumns the number of columns.
   */
  private void initializeGrid(int numRows, int numColumns) {
    gridPane = new GridPane();
    cellViewGrid = new CellView[numRows][numColumns];
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numRows; col++) {
        cellViewGrid[row][col] = new CellView();
        gridPane.getChildren().add(cellViewGrid[row][col]);
        GridPane.setRowIndex(cellViewGrid[row][col], row);
        GridPane.setColumnIndex(cellViewGrid[row][col], col);
      }
    }
    gridPane.getStyleClass().add("grid-view");
  }

  public GridPane getGridPane() {
    return gridPane;
  }

  public int getNumRows() {
    return numRows;
  }

  public int getNumColumns() {
    return numColumns;
  }

  /**
   * Set the color of a cell view.
   *
   * @param row   the row of the cell view.
   * @param col   the column of the cell view.
   * @param color the new color to set the cell view to.
   */
  public void setCellViewColor(int row, int col, Paint color) {
    cellViewGrid[row][col].setColor(color);
  }
}

