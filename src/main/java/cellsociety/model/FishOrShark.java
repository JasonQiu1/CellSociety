package cellsociety.model;

public class FishOrShark extends Cell {
  private int chrononsSurvived;
  private int energy;

  public FishOrShark(int currentState, int START_ENERGY) {
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
