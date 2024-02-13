package cellsociety.view;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Responsible for determining the color of a state given the number of possible states.
 *
 * @author Jason Qiu
 */
class StateColorGenerator {

  private final Color[] colors;

  /**
   * Initializes a map of state to distinct color.
   *
   * @param numStates the number of possible states.
   */
  public StateColorGenerator(int numStates) {
    colors = new Color[numStates];
    double hueStep = 360.0 / (numStates + 1);
    for (int i = 0; i < numStates; i++) {
      colors[i] = Color.hsb(hueStep * i, 1, 1);
    }
  }

  /**
   * Gets the fixed color for a given state.
   *
   * @param state the state number.
   * @return the color that corresponds to that state.
   */
  public Color getColor(int state) {
    return colors[state];
  }

  public Color[] getColors() {
    return colors;
  }
}
