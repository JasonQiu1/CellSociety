package cellsociety.model;

public class Ants {
  private boolean hasFoodItem;

  private int directionFacing;
  // 0 left
  // 1 up
  // 2 right
  // 3 left

  public Ants() {
    hasFoodItem = Math.random()<0.5;
    directionFacing = 1;
  }

  public boolean isHasFoodItem() {
    return hasFoodItem;
  }

  public void setHasFoodItem(boolean hasFoodItem) {
    this.hasFoodItem = hasFoodItem;
  }

  public int getDirectionFacing() {
    return directionFacing;
  }

  public void setDirectionFacing(int directionFacing) {
    this.directionFacing = directionFacing;
  }
}
