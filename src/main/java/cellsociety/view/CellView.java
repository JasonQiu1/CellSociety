package cellsociety.view;

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
class CellView extends Region {

  public static Paint DEFAULT_COLOR = Color.WHITE;

  public CellView() {
    super();
    setColor(DEFAULT_COLOR);
  }

  public void setColor(Paint color) {
    this.setBackground(new Background(
        new BackgroundFill(color, null, null)
    ));
  }
}
