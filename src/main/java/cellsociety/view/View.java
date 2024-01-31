package cellsociety.view;

import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Responsible for drawing anything visible on the screen. Also checks and handles user input
 * (button clicks, text fields, keypresses).
 *
 * @author Jason Qiu (jq48)
 */
public class View {

  private static int STAGE_WIDTH = 750;
  private static int STAGE_HEIGHT = 500;
  private static Paint STAGE_COLOR = Color.WHITE;
  private static double GRID_TO_UI_RATIO = 0.8;
  private Stage mainScene;
  private UserInterfaceDrawer uiDrawer;
  private GridDrawer gridDrawer;

  /**
   * Splits the given Stage into two portions for the cell grid and the user interface. Also hooks
   * user key events and user interface buttons and text fields to their handlers in Controller.
   *
   * @param stage the stage to draw everything on.
   */
  public View(Stage stage) {
    Pane gridPane = new Pane();
    gridPane.setMaxSize(STAGE_WIDTH, STAGE_HEIGHT * GRID_TO_UI_RATIO);
    gridDrawer = new GridDrawer(gridPane);

    Pane uiPane = new Pane();
    uiPane.setMaxSize(STAGE_WIDTH, STAGE_HEIGHT * (1 - GRID_TO_UI_RATIO));
    uiDrawer = new UserInterfaceDrawer(uiPane);

    Group sceneRoot = new Group(uiPane, gridPane);
    Scene scene = new Scene(sceneRoot, STAGE_WIDTH, STAGE_HEIGHT, STAGE_COLOR);
    stage.setScene(scene);
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
