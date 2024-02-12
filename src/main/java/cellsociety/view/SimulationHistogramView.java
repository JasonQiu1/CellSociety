package cellsociety.view;

import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

/**
 * Responsible for displaying simulation state as a histogram of current cell states on the grid.
 *
 * @author Jason Qiu
 */
class SimulationHistogramView extends SimulationView {

  HistogramPanel histogram;

  public SimulationHistogramView(Simulation simulation, Pane pane) {
    super(simulation, pane, "simulation-histogram-view");
    histogram = new HistogramPanel(getRoot());
  }

  /**
   * Update the number of each state at the current time.
   */
  @Override
  public void update() {
    Map<Integer, Pair<Integer, Paint>> numOfEachState = new HashMap<>();
    Grid grid = getSimulation().getGrid();
    ;
    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumCols(); col++) {
        numOfEachState.compute(grid.getCellState(row, col),
            (k, v) -> (v == null) ? new Pair<>(0, getColorGenerator().getColor(k))
                : new Pair<>(v.getKey() + 1, getColorGenerator().getColor(k)));
      }
    }
    histogram.setXSeries(numOfEachState);
  }
}
