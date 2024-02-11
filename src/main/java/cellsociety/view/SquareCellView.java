package cellsociety.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Responsible for representing the view of a cell.
 *
 * @author Jason Qiu
 */
class SquareCellView extends Region {

  public static Paint DEFAULT_COLOR = Color.WHITE;

  public SquareCellView() {
    super();
    setColor(DEFAULT_COLOR);
    this.getStyleClass().add("cell-view");
    // the label is never used but for some reason
    // GridPanes do not display if they only contain layouts
    this.getChildren().add(new Label());
  }

  public void setColor(Paint color) {
    this.setBackground(new Background(
        new BackgroundFill(color, null, null)
    ));
  }
}
