package cellsociety.view;

import javafx.scene.paint.Color;

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
   * Maps each state to the given rgb colors
   *
   * @param rgbColors the rgb values to use for each state.
   */
  public StateColorGenerator(int[][] rgbColors) {
    colors = new Color[rgbColors.length];
    for (int i = 0; i < rgbColors.length; i++) {
      colors[i] = Color.rgb(rgbColors[i][0], rgbColors[i][1], rgbColors[i][2]);
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
}
