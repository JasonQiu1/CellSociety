package cellsociety.model;

public class FiniteGrid extends GenericGrid {

  private Cell[][] grid;
  private RuleSet rules;

  public FiniteGrid(RuleSet rules) {
    // takes in ruleset to best used to update the grid
    // grid is initially taken from the Rulset as some changes need to be made to the initial grid
    // for some implementations
    super(rules);
  }
}