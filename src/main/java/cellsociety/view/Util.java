package cellsociety.view;

import cellsociety.controller.Controller;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;

/**
 * Utility class to make ui components that don't contain any state nor belong to any specific
 * class.
 *
 * @author Jason Qiu
 */
public class Util {

  // makeButton code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  // makes a button using either an image or a label
  public static Button makeButton(String property, EventHandler<ActionEvent> handler) {
    // represent all supported image suffixes
    final String imageFileSuffixes = String.format(".*\\.(%s)",
        String.join("|", ImageIO.getReaderFileSuffixes()));

    Button result = new Button();
    String label = View.resources.getString(property);
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
}
