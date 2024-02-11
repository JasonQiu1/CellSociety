package cellsociety.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * Abstraction for differently shaped grids that follow the same coordinate system.
 *
 * @author Jason Qiu
 */
abstract class GridView extends UserInterfacePanel {

  private final CellView[][] cellViewGrid;
  private final int numRows;
  private final int numColumns;

  /**
   * Creates a new fixed array of SquareCellView instances parented to a GridPane instance.
   *
   * @param numRows    the number of rows of the grid view.
   * @param numColumns the number of columns of the grid view.
   */
  public GridView(Pane root, int numRows, int numColumns, String className) {
    super(root, className);
    this.numRows = numRows;
    this.numColumns = numColumns;

    cellViewGrid = new CellView[numRows][numColumns];
    initializeCellViewGrid();
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

  protected CellView[][] getCellViewGrid() {
    return cellViewGrid;
  }

  /**
   * Initializes the gridPane and cellViewGrid at the same time using the number of rows and
   * columns.
   */
  protected abstract void initializeCellViewGrid();
}
