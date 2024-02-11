package cellsociety.view;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Polygon;

/**
 * Responsible for representing the view of a square grid.
 *
 * @author Jason Qiu
 */
class SquareGridView extends GridView {

  /**
   * Creates a new fixed array of SquareCellView instances parented to a GridPane instance.
   *
   * @param numRows    the number of rows of the grid view.
   * @param numColumns the number of columns of the grid view.
   */
  public SquareGridView(Pane root, int numRows, int numColumns) {
    super(root, numRows, numColumns, "square-grid-view");
  }

  @Override
  protected void initializeCellViewGrid() {
    GridPane gridPane = new GridPane();
    gridPane.setPrefSize(getRoot().getPrefWidth(), getRoot().getPrefHeight());
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setGridLinesVisible(true);

    CellView[][] cellViewGrid = getCellViewGrid();
    for (int row = 0; row < getNumRows(); row++) {
      for (int col = 0; col < getNumColumns(); col++) {
        cellViewGrid[row][col] = newSquareCellView();
        gridPane.add(cellViewGrid[row][col].getRoot(), row, col);
      }
    }

    // Column constraints:
    for (int x = 0; x < getNumColumns(); x++) {
      ColumnConstraints cc = new ColumnConstraints();
      cc.setPercentWidth(100.0 / getNumColumns());
      cc.setFillWidth(true);
      gridPane.getColumnConstraints().add(cc);
    }

    // row constraints:
    for (int y = 0; y < getNumRows(); y++) {
      RowConstraints rc = new RowConstraints();
      rc.setPercentHeight(100.0 / getNumRows());
      rc.setFillHeight(true);
      gridPane.getRowConstraints().add(rc);
    }

    getRoot().getChildren().add(gridPane);
  }

  // creates a square polygon
  private CellView newSquareCellView() {
    return new CellView(new Polygon(
        -1, -1,
        1, -1,
        1, 1,
        -1, 1));
  }
}

