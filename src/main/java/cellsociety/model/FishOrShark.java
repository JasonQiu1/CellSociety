package cellsociety.model;

public class FishOrShark extends Cell {
  private int chrononsSurvived;

  private int energy;

  private int START_ENERGY = 10;
  public FishOrShark(int currentState, int START_ENERGY) {
    super(currentState);
    chrononsSurvived = 0;
    this.START_ENERGY = START_ENERGY;
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
