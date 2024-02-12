package cellsociety;

import cellsociety.config.ConfigLoader;
import cellsociety.config.SimulationSaver;
import cellsociety.model.Simulation;
import cellsociety.view.View;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Game extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final String VIEW_RESOURCE_BUNDLE_NAME = "view";
  public static View view;
  public static List<Simulation> simulations;
  public static ConfigLoader configLoader;
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  private static void step(double elapsedTime) {
    try {
      for (Simulation simulation : simulations) {
        simulation.update(elapsedTime);
      }
      view.update();
    } catch (RuntimeException e) {
      View.showError(e.getMessage());
    }
  }

  public static boolean loadNewSimulation(String configFileName) {
    try {
      // Load a new simulation from a configuration file
      configLoader = new ConfigLoader(configFileName + ".xml");
      Simulation simulation = configLoader.simulation;
      view.addSimulation(simulation);
      simulations.add(simulation);
      return updateGridFromConfig(configFileName);
    } catch (RuntimeException e) {
      View.showError(e.getMessage());
      return false;
    }
  }

  private static boolean updateGridFromConfig(String configFileName) {
    return false;
  }

  public static void removeSimulation(Simulation simulation) {
    simulations.remove(simulation);
  }

  //    public static boolean reloadGridFromConfig() {
//        // Load grid configuration from file and update the grid
////        simulation set grid = to whatever the config says it was
//        return configLoader.loadCurrentConfigWithoutParams();
//    }
//
  public static void saveSimulationToConfig(Simulation simulation, String configFileName) {
    // Save current simulation state to a configuration file
    SimulationSaver.saveSimulationState(simulation, configFileName);
  }

  @Override
  public void start(Stage stage) {
    view = new View(stage, VIEW_RESOURCE_BUNDLE_NAME);
    simulations = new ArrayList<Simulation>();
//    configLoader = new ConfigLoader("example.xml");
    //    this will need to be done by the ui but for testing it's here
//    simulation = new Simulation();
//    simulation.unpause();
//    view.setSimulation(simulation);
//    simulation.setAuthor("prince 2");
//    simulation.setDescription("i am saving this file");
//    SimulationSaver saver = new SimulationSaver();
//    saver.saveSimulationState(simulation, "simulation_state.xml");
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }
//

}
