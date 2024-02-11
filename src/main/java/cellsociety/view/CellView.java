package cellsociety.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * Responsible for representing the view of a cell.
 *
 * @author Jason Qiu
 */
class CellView {

  public static final double MARGIN = 1;
  private final AutoScalePane resizingPane;
  private final Polygon cellShape;

  /**
   * Wraps the shape of the cell with a resizing pane.
   *
   * @param cellShape the visual shape of the cell.
   */
  public CellView(Polygon cellShape) {
    this.cellShape = cellShape;
    resizingPane = new AutoScalePane(MARGIN);
    resizingPane.setPadding(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
    resizingPane.addChildren(cellShape);
    resizingPane.getStyleClass().add("cell-view");
  }

  public Region getRoot() {
    return resizingPane;
  }

  public void setColor(Paint color) {
    cellShape.setFill(color);
  }
}
