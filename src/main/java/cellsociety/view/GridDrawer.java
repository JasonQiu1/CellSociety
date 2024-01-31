package cellsociety.view;

import cellsociety.model.Grid;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Responsible for drawing the given grid onto the given pane.
 *
 * @author Jason Qiu (jq48)
 */
public class GridDrawer {

  int numStates;
  GridPane gridPane;
  StateColor colorGenerator;

  /**
   * Creates an instance of StateColor to get colors from when drawing later.
   *
   * @param root the root to add window objects to.
   */
  public GridDrawer(Pane root) {
    numStates = 0;

    gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setGridLinesVisible(true);
    gridPane.setPrefSize(root.getMaxWidth(), root.getMaxHeight());
  }

  /**
   * Draws the given grid onto the cell grid.
   *
   * @param grid the grid to draw.
   */
  public void draw(Grid grid) {
    return;
  }
}
