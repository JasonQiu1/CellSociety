package cellsociety.view;

import javafx.scene.layout.Pane;

/**
 * Abstracts any drawable user interface.
 *
 * @author Jason Qiu
 */
abstract class UserInterfacePanel {

  // the root javafx pane to add ui components to
  private Pane root;

  /**
   * Sets the root to the give pane.
   *
   * @param pane the new root of the panel.
   */
  protected UserInterfacePanel(Pane pane) {
    root = pane;
  }

  protected Pane getRoot() {
    return root;
  }
}
