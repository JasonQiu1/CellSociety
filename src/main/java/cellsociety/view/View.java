package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.Simulation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Responsible for drawing anything visible on the screen. Delegates user input handling to a
 * Controller instance.
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
  private Controller controller;

  /**
   * Splits the given Stage into two portions for the cell grid and the user interface. Sets up the
   * drawers for the cell grid, ui, and controller.
   *
   * @param stage the stage to draw everything on.
   */
  public View(Stage stage) {
    controller = new Controller();

    Pane gridPane = new Pane();
    gridPane.setMaxSize(STAGE_WIDTH, STAGE_HEIGHT * GRID_TO_UI_RATIO);
    gridDrawer = new GridDrawer(gridPane);

    Pane uiPane = new Pane();
    uiPane.setMaxSize(STAGE_WIDTH, STAGE_HEIGHT * (1 - GRID_TO_UI_RATIO));
    uiDrawer = new UserInterfaceDrawer(uiPane, controller);

    Group sceneRoot = new Group(uiPane, gridPane);
    Scene scene = new Scene(sceneRoot, STAGE_WIDTH, STAGE_HEIGHT, STAGE_COLOR);
    stage.setScene(scene);
    stage.setTitle("Cell Society");
    stage.show();
  }

  /**
   * Draws the current simulation's grid and user interface.
   */
  public void draw() {
    gridDrawer.draw(currentSimulation.getGrid());
    uiDrawer.draw();
  }

  /**
   * Update's the grid reference that gridDrawer draws from. Also points the controller's user input
   * handling functions to the correct simulation.
   * TODO: refactor later so that main just passes Grid into draw for the GridDrawer
   * TODO: make main create the controller instead and pass it into View constructor to hook
   *    Removes the need for this function in View, will be responsibility of main
   *
   * @param simulation the new simulation.
   */
  public void setSimulation(Simulation simulation) {

    if (simulation == null) {
      return;
    }
    gridDrawer.setNumStates(simulation.getNumStates());
    controller.setSimulation(simulation);
  }


}
