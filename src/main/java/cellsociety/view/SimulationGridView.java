package cellsociety.view;


import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Responsible for drawing the given simulation onto the given pane as a grid. The grid can be of
 * any shape, as long as the coordinates are all set up the same way.
 *
 * @author Jason Qiu (jq48)
 */
class SimulationGridView extends SimulationView {

  private GridView gridView;
  private StateColorGenerator colorGenerator;

  /**
   * Holds on to the parent to draw to.
   *
   * @param pane the root to add window objects to.
   */
  public SimulationGridView(Simulation simulation, Pane pane) {
    super(simulation, pane, "grid-drawer");
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
    if (gridView != null) {
      getRoot().getChildren().remove(gridView.getGridPane());
    }

    gridView = new GridView(getSimulation().getGrid().getNumRows(),
        getSimulation().getGrid().getNumCols());
    GridPane gridPane = gridView.getGridPane();
    gridPane.setPrefSize(getRoot().getPrefWidth(), getRoot().getPrefHeight());
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setGridLinesVisible(true);
    getRoot().getChildren().add(gridPane);

    colorGenerator = new StateColorGenerator(getSimulation().getNumStates());
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
