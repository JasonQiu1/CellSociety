package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.Simulation;
import java.util.Map;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javax.imageio.ImageIO;

/**
 * Utility class to make ui components that don't contain any state nor belong to any specific
 * class.
 *
 * @author Jason Qiu
 */
class Util {

  // makeButton code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  // makes a button using either an image or a label
  public static Button makeButton(String property, EventHandler<ActionEvent> handler) {
    // represent all supported image suffixes
    final String imageFileSuffixes = String.format(".*\\.(%s)",
        String.join("|", ImageIO.getReaderFileSuffixes()));

    Button result = new Button();
    String label = View.getResources().getString(property);
    if (label.matches(imageFileSuffixes)) {
      result.setGraphic(new ImageView(new Image(
          Objects.requireNonNull(
              Util.class.getClassLoader()
                  .getResourceAsStream(View.DEFAULT_RESOURCE_FOLDER + label)))));
    } else {
      result.setText(label);
    }
    result.setOnAction(handler);
    return result;
  }

  /**
   * Create load config components, including input field and load button
   *
   * @param inputFieldWidth the width of the input field for the config file path.
   */
  public static Pane makeLoadConfigurationPanel(int inputFieldWidth) {
    Pane root = new Pane();
    root.getStyleClass().add("load-configuration-panel");

    TextField configPathInput = new TextField();
    configPathInput.setPrefColumnCount(inputFieldWidth);
    Button load = makeButton("loadConfigurationButton",
        event -> Controller.handleLoadConfigurationFileButtonPress(configPathInput.getText()));

    GridPane layout = new GridPane();
    // reserve columns 0 and 1 for configuration info
    layout.addColumn(2, configPathInput, load);

    root.getChildren().add(layout);
    return root;
  }

  /**
   * Creates a scrollable information panel that displays the config info for a simulation.
   *
   * @param simulation the simulation to display info for.
   * @return the root of the info panel.
   */
  public static Pane makeSimulationInfoPanel(Simulation simulation) {
    Label simulationInfoText = new Label();
    simulationInfoText.setWrapText(true);
    StringBuilder simulationInfo = new StringBuilder("Configuration info:");
    for (Map.Entry<String, String> entry : simulation.getConfigInfo().entrySet()) {
      simulationInfo.append("\n");
      simulationInfo.append(entry.getKey());
      simulationInfo.append(": ");
      simulationInfo.append(entry.getValue());
    }
    simulationInfoText.setText(simulationInfo.toString());
    ScrollPane simulationInfoPane = new ScrollPane(simulationInfoText);
    simulationInfoPane.getStyleClass().add("simulation-info-pane");
    simulationInfoPane.setFitToWidth(true);
    simulationInfoPane.setFitToHeight(true);

    return new Pane(simulationInfoPane);
  }

  /**
   * Sets the col/row constraints of a gridpane so that all cells are equally spaced.
   * @param gridPane the GridPane to space out.
   * @param numRows the number of rows.
   * @param numColumns the number of columns.
   */
  public static void setEqualCellSize(GridPane gridPane, int numRows, int numColumns) {
    // Column constraints:
    for (int x = 0; x < numColumns; x++) {
      ColumnConstraints cc = new ColumnConstraints();
      cc.setPercentWidth(100.0 / numColumns);
      cc.setFillWidth(true);
      gridPane.getColumnConstraints().add(cc);
    }

    // row constraints:
    for (int y = 0; y < numRows; y++) {
      RowConstraints rc = new RowConstraints();
      rc.setPercentHeight(100.0 / numRows);
      rc.setFillHeight(true);
      gridPane.getRowConstraints().add(rc);
    }
  }
}
