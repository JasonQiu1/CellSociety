package cellsociety.view;


import cellsociety.model.Grid;
import com.sun.tools.jconsole.JConsoleContext;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 * Responsible for drawing the given grid onto the given pane.
 *
 * @author Jason Qiu (jq48)
 */
class GridDrawer extends UserInterfacePanel {

  GridView gridView;
  StateColorGenerator colorGenerator;
  int numStates;

  /**
   * Holds on to the parent to draw to.
   *
   * @param pane the root to add window objects to.
   */
  public GridDrawer(Pane pane) {
    super(pane, "grid-drawer");
    gridView = null;
    colorGenerator = null;
  }


  /**
   * Draws the current simulation's grid onto the cell grid.
   *
   * @param currentGrid the non-null grid to draw.
   */
  public void update(Grid currentGrid) {
    if (currentGrid == null) {
      return;
    }
    // reinitialize the grid if it's a different size
    if (gridView == null
        || currentGrid.getNumRows() != gridView.getNumRows()
        || currentGrid.getNumCols() != gridView.getNumColumns()) {
      initializeGridView(currentGrid.getNumRows(), currentGrid.getNumCols());
      setNumStates(currentGrid.getNumStates());
    }
    updateGridView(currentGrid);
  }

  /**
   * Creates a new StateColorGenerator given the number of states.
   *
   * @param numStates the new number of states.
   */
  public void setNumStates(int numStates) {
    this.numStates = numStates;
    colorGenerator = new StateColorGenerator(numStates);
  }

  /**
   * Set gridView to a new GridView and set its formatting, maintaining the getRoot() at the same
   * time.
   *
   * @param numRows the number of rows of the resulting GridView.
   * @param numCols the number of columns of the resulting GridView.
   */
  private void initializeGridView(int numRows, int numCols) {
    if (gridView != null) {
      getRoot().getChildren().remove(gridView.getGridPane());
    }
    gridView = new GridView(numRows, numCols);
    GridPane gridPane = gridView.getGridPane();
    gridPane.setPrefSize(getRoot().getPrefWidth(), getRoot().getPrefHeight());
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setGridLinesVisible(true);
    getRoot().getChildren().add(gridPane);
  }

  /**
   * Updates the colors of each cell in gridView to match the states in the model
   * cellsociety.model.Grid.
   *
   * @param currentGrid the model's cellsociety.model.Grid instance.
   */
  private void updateGridView(Grid currentGrid) {

    for (int row = 0; row < currentGrid.getNumRows(); row++) {
      for (int col = 0; col < currentGrid.getNumCols(); col++) {
//        System.out.println(currentGrid.getCellState(row, col));
        gridView.setCellViewColor(row, col,
            colorGenerator.getColor(currentGrid.getCellState(row, col)));
      }
    }
  }
}
