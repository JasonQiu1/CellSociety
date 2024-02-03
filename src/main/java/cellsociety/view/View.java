package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.Simulation;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Responsible for drawing anything visible on the screen. Delegates user input handling to a
 * Controller instance.
 *
 * @author Jason Qiu (jq48)
 */
public class View {

  // resource code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static int STAGE_WIDTH = 750;
  private static int STAGE_HEIGHT = 500;
  private static Paint STAGE_COLOR = Color.WHITE;
  private static double GRID_TO_UI_RATIO = 0.8;
  private Stage stage;
  private ResourceBundle resources;
  private UserInterfaceDrawer uiDrawer;
  private GridDrawer gridDrawer;
  private Controller controller;
  private Simulation currentSimulation;

  /**
   * Splits the given Stage into two portions for the cell grid and the user interface. Sets up the
   * drawers for the cell grid, ui, and controller.
   *
   * @param stage the stage to draw everything on.
   */
  public View(Stage stage, String resourcesFileName) {
    // resource bundle loading line from nanobrowser lab:
    // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
    resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + resourcesFileName);
    this.stage = stage;
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
    if (currentSimulation != null) {
      gridDrawer.draw(currentSimulation.getGrid());
    }
    uiDrawer.draw();
  }

  /**
   * Update's the grid reference that gridDrawer draws from. Also points the controller's user input
   * handling functions to the correct simulation.
   * TODO: refactor later so that main just passes cellsociety.model.Grid into draw for the GridDrawer
   * TODO: make main create the controller instead and pass it into View constructor to hook
   *    Removes the need for this function in View, will be responsibility of main
   *
   * @param simulation the new simulation.
   */
  public void setSimulation(Simulation simulation) {
    if (simulation == null) {
      return;
    }
    currentSimulation = simulation;
    gridDrawer.setNumStates(simulation.getNumStates());
    controller.setSimulation(simulation);
  }

  // makeButton code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  // makes a button using either an image or a label
  private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    // represent all supported image suffixes
    final String IMAGE_FILE_SUFFIXES = String.format(".*\\.(%s)",
        String.join("|", ImageIO.getReaderFileSuffixes()));
    Button result = new Button();
    String label = resources.getString(property);
    if (label.matches(IMAGE_FILE_SUFFIXES)) {
      result.setGraphic(new ImageView(new Image(
          Objects.requireNonNull(
              getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label)))));
    } else {
      result.setText(label);
    }
    result.setOnAction(handler);
    return result;
  }

  // makeInputField code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  private TextField makeInputField(int width, EventHandler<ActionEvent> handler) {
    TextField result = new TextField();
    result.setPrefColumnCount(width);
    result.setOnAction(handler);
    return result;
  }

}
