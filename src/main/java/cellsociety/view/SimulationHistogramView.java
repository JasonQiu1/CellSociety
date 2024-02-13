package cellsociety.view;

import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Responsible for displaying simulation state as a histogram of current cell states on the grid.
 *
 * @author Jason Qiu
 */
class SimulationHistogramView extends SimulationView {

  HistogramPanel histogram;

  public SimulationHistogramView(Simulation simulation, Pane pane) {
    super(simulation, pane, "simulation-histogram-view");
    // TODO: temporary statenames until I can get the actual names from Simulation.getConfigInfo()
    Map<Integer, String> stateNames = new HashMap<>();
    Map<String, Color> stateColors = new HashMap<>();
    for (int i = 0; i < simulation.getNumStates(); i++) {
      stateNames.put(i, Integer.toString(i));
      stateColors.put(Integer.toString(i), getColorGenerator().getColor(i));
    }
    histogram = new HistogramPanel(getRoot(), stateNames, stateColors);
  }

  /**
   * Update the number of each state at the current time.
   */
  @Override
  public void update() {

    Grid grid = getSimulation().getGrid();

    int[] numOfEachState = new int[getSimulation().getNumStates()];
    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumCols(); col++) {
        numOfEachState[grid.getCellState(row, col)] += 1;
      }
    }

    // TODO: use state names instead of state number when added to Simulation.getConfig()
    Map<String, Number> data = new HashMap<>();
    for (int i = 0; i < getSimulation().getNumStates(); i++) {
      data.put(Integer.toString(i), numOfEachState[i]);
    }

    histogram.setData(data);
  }
}
