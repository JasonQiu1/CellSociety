package cellsociety.view;


import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import javafx.scene.layout.Pane;

/**
 * Responsible for drawing the given simulation onto the given pane as a grid. The grid can be of
 * any shape, as long as the coordinates are all set up the same way.
 *
 * @author Jason Qiu (jq48)
 */
class SimulationGridView extends SimulationView {

  private SquareGridView gridView;
  private StateColorGenerator colorGenerator;

  /**
   * Holds on to the parent to draw to.
   *
   * @param pane the root to add window objects to.
   */
  public SimulationGridView(Simulation simulation, Pane pane) {
    super(simulation, pane, "grid-drawer");
    double minDim = Math.min(pane.getPrefHeight(), pane.getPrefWidth());
    pane.setPrefSize(minDim, minDim);
  }

  /**
   * Draws the current simulation's grid onto the cell grid.
   */
  @Override
  public void update() {
    Grid grid = getSimulation().getGrid();
    // reinitialize the grid if it's a different size
    if (gridView == null
        || grid.getNumRows() != gridView.getNumRows()
        || grid.getNumCols() != gridView.getNumColumns()) {
      reinitializeGridView();
    }
    updateGridView();
  }

  // recreates the grid view with the same dimensions as the current grid
  private void reinitializeGridView() {
    colorGenerator = new StateColorGenerator(getSimulation().getNumStates());

    if (gridView != null) {
      getRoot().getChildren().remove(gridView.getRoot());
    }

    gridView = new SquareGridView(getRoot(), getSimulation().getGrid().getNumRows(),
        getSimulation().getGrid().getNumCols());
  }

  /**
   * Updates the colors of each cell in gridView to match the states in the simulation.
   */
  private void updateGridView() {
    Grid currentGrid = getSimulation().getGrid();
    for (int row = 0; row < currentGrid.getNumRows(); row++) {
      for (int col = 0; col < currentGrid.getNumCols(); col++) {
        gridView.setCellViewColor(row, col,
            colorGenerator.getColor(currentGrid.getCellState(row, col)));
      }
    }
  }
}
