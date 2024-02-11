package cellsociety.view;

import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * Responsible for auto-resizing contents of the pane to fit the pane.
 *
 * @author <a
 * href="https://stackoverflow.com/questions/49732048/autoscalepane-in-javafx-with-layoutchildren">...</a>
 */
class AutoScalePane extends Region {

  private final Group content = new Group();

  public AutoScalePane(double margin) {
    content.setManaged(false); // avoid constraining the size by content
    getChildren().add(content);
  }

  /**
   * Adds nodes to the AutoScalePane
   *
   * @param children nodes
   */
  public void addChildren(Node... children) {
    content.getChildren().addAll(children);
    requestLayout();
  }

  /**
   * Automatically resizes children to match the pane and insets.
   */
  @Override
  protected void layoutChildren() {
    final Bounds groupBounds = content.getBoundsInLocal();

    final double paneWidth = getWidth();
    final double paneHeight = getHeight();
    final double insetTop = getInsets().getTop();
    final double insetRight = getInsets().getRight();
    final double insetLeft = getInsets().getLeft();
    final double insertBottom = getInsets().getBottom();

    final double contentWidth = (paneWidth - insetLeft - insetRight);
    final double contentHeight = (paneHeight - insetTop - insertBottom);

    // zoom
    double factorX = contentWidth / groupBounds.getWidth();
    double factorY = contentHeight / groupBounds.getHeight();
    double factor = Math.min(factorX, factorY);
    content.setScaleX(factor);
    content.setScaleY(factor);

    layoutInArea(content, insetLeft, insetTop, contentWidth, contentHeight,
        getBaselineOffset(), HPos.CENTER, VPos.CENTER);
  }

}