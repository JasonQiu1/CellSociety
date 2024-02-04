package cellsociety.model;

public class Cell {

  private int currentState;
  private int nextState;

  public Cell(int currentState) {
    this.currentState = currentState;
    nextState = currentState;
  }

  public int getCurrentState() {
    return currentState;
  }

  public void setCurrentState(int currentState) {
    this.currentState = currentState;
  }

  public int getNextState() {
    return nextState;
  }

  public void setNextState(int nextState) {
    this.nextState = nextState;
  }
}
