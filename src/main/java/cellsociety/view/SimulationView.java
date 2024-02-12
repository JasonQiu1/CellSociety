package cellsociety.view;

import cellsociety.model.Simulation;
import javafx.scene.layout.Pane;

/**
 * Responsible for displaying a simulation graphically in some way onto a pane.
 *
 * @author Jason Qiu
 */
abstract class SimulationView extends UserInterfacePanel {

  private final Simulation simulation;

  private final StateColorGenerator colorGenerator;

  /**
   * Sets the root to the given pane and also adds a css class for configurable styling.
   *
   * @param pane         the new root of the panel.
   * @param cssClassName the name of the css class for the panel
   */
  protected SimulationView(Simulation simulation, Pane pane, String cssClassName) {
    super(pane, cssClassName);
    this.simulation = simulation;
    colorGenerator = new StateColorGenerator(getSimulation().getNumStates());
  }

  /**
   * Update the view based on the current stored simulation's state.
   */
  public abstract void update();

  protected Simulation getSimulation() {
    return simulation;
  }

  protected StateColorGenerator getColorGenerator() {
    return colorGenerator;
  }
}
