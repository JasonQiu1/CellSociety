package cellsociety.view;

import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * Interface for any user interface panel that needs to make buttons or input fields.
 *
 * @author Jason Qiu
 */
interface UserInputable {

  // makeButton code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  // makes a button using either an image or a label
  default Button makeButton(String property, EventHandler<ActionEvent> handler) {
    // represent all supported image suffixes
    final String IMAGE_FILE_SUFFIXES = String.format(".*\\.(%s)",
        String.join("|", ImageIO.getReaderFileSuffixes()));
    Button result = new Button();
    String label = View.resources.getString(property);
    if (label.matches(IMAGE_FILE_SUFFIXES)) {
      result.setGraphic(new ImageView(new Image(
          Objects.requireNonNull(
              getClass().getResourceAsStream(View.DEFAULT_RESOURCE_FOLDER + label)))));
    } else {
      result.setText(label);
    }
    result.setOnAction(handler);
    return result;
  }

  // makeInputField code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  default TextField makeInputField(int width, EventHandler<ActionEvent> handler) {
    TextField result = new TextField();
    result.setPrefColumnCount(width);
    result.setOnAction(handler);
    return result;
  }
}
