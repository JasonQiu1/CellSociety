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

  /**
   * Update the view based on the current stored simulation's state.
   */
  public abstract void update();

  /**
   * Sets the root to the given pane and also adds a css class for configurable styling.
   *
   * @param pane         the new root of the panel.
   * @param cssClassName the name of the css class for the panel
   */
  protected SimulationView(Simulation simulation, Pane pane, String cssClassName) {
    super(pane, cssClassName);
    this.simulation = simulation;
  }

  protected Simulation getSimulation() {
    return simulation;
  }
}
