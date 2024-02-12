package cellsociety.model;

public class FiniteGrid extends GenericGrid {

  public FiniteGrid(RuleSet rules, Cell[][] grid) {
    // takes in ruleset to best used to update the grid
    // grid is initially taken from the Rulset as some changes need to be made to the initial grid
    // for some implementations
    super(rules, grid);
  }
}