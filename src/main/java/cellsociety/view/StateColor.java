package cellsociety.view;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Responsible for determining the color of a state given the number of possible states.
 *
 * @author Jason Qiu
 */
public class StateColor {

  /**
   * Initializes a map of state to distinct color.
   * @param numStates the number of possible states.
   */
  public StateColor(int numStates) {

  }

  /**
   * Gets the fixed color for a given state.
   *
   * @param state the state number.
   * @return the color that corresponds to that state.
   */
  public Paint getColor(int state) {
    return Color.WHITE;
  }
}
