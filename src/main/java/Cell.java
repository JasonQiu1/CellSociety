public class Cell {

  private int currentState;
  private boolean flag;

  public Cell(int currentState) {
    this.currentState = currentState;
  }

  public int getCurrentState() {
    return currentState;
  }

  public void setCurrentState(int currentState) {
    this.currentState = currentState;
  }

  public boolean getFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }
}
