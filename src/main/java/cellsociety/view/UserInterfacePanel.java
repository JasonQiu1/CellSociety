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
   * Sets the root to the given pane and also adds a css class for configurable styling.
   *
   * @param pane the new root of the panel.
   */
  protected UserInterfacePanel(Pane pane, String cssClassName) {
    root = pane;
    root.getStyleClass().add(cssClassName);
  }

  protected Pane getRoot() {
    return root;
  }
}
