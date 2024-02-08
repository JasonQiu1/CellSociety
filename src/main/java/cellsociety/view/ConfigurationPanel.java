package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Responsible for - showing the information of the currently loaded configuration file -
 * implementing the load, save (and edit save) buttons
 *
 * @author Jason Qiu
 */
class ConfigurationPanel extends UserInterfacePanel implements UserInputable {

  private static final String PROPERTY_SUFFIX = "ConfigurationPanel";
  private final TextField configPathInput;
  private String currentConfigPath;

  /**
   * Create all config components, including the config info panel, input field, and buttons
   *
   * @param pane       the root to draw to.
   * @param controller the controller to hook handlers.
   */
  public ConfigurationPanel(Pane pane, Controller controller) {
    super(pane, "configuration-panel");

    configPathInput = makeInputField(10, event -> updateCurrentConfigPath());
    // TODO: handle invalid user input to avoid crashes
    Button load = makeButton("load" + PROPERTY_SUFFIX,
        event -> controller.handleLoadConfigurationFileButtonPress(currentConfigPath));
    // TODO: create edit save popout window and functionality, pass metadata map as second argument
    Button save = makeButton("save" + PROPERTY_SUFFIX,
        event -> controller.handleSaveConfigurationFileButtonPress(currentConfigPath,
            null));

    // TODO: create config information box

    GridPane layout = new GridPane();
    // reserve columns 0 and 1 for configuration info
    layout.addColumn(2, configPathInput, load, save);
    getRoot().getChildren().add(layout);
  }

  public void update() {
    // TODO: update any components if needed
  }

  // TODO: sanitize input to allow only possibly-valid paths
  private void updateCurrentConfigPath() {
    currentConfigPath = configPathInput.getText();
  }
}
