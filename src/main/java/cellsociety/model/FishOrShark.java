package cellsociety.model;

public class FishOrShark extends Cell {
  private int chrononsSurvived;

  private int energy;

  private final static int START_ENERGY = 10;
  public FishOrShark(int currentState) {
    super(currentState);
    chrononsSurvived = 0;
    energy = START_ENERGY;

  }

  public int getChrononsSurvived() {
    return chrononsSurvived;
  }

  public void setChrononsSurvived(int chrononsSurvived) {
    this.chrononsSurvived = chrononsSurvived;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }
}
