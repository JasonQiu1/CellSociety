package cellsociety.view;

import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import javafx.stage.Stage;

/**
 * Responsible for drawing anything visible on the screen. Also checks and handles user input
 * (button clicks, text fields, keypresses).
 *
 * @author Jason Qiu (jq48)
 */
public class View {

  /**
   * Splits the given Stage into two portions for the cell grid and the user interface. Also hooks
   * user key events and user interface buttons and text fields to their handlers in Controller.
   *
   * @param stage the stage to draw everything on.
   */
  public View(Stage stage) {
  }

  /**
   * Draws the cell grid and user interface given a cell grid.
   *
   * @param grid the grid to draw onto the cell grid portion.
   */
  public void draw(Grid grid) {
    return;
  }

  /**
   * Checks for user input and handles it.
   *
   * @param simulation the current simulation to send messages to if needed.
   */
  public void handleUserInput(Simulation simulation) {

  }
}
