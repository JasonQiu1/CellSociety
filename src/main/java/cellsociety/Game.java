package cellsociety;

import cellsociety.config.ConfigLoader;
import cellsociety.model.Simulation;
import cellsociety.view.View;
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
  public static Simulation simulation;
  public static ConfigLoader configLoader;
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  private static void step(double elapsedTime) {
    // Update simulation and UI based on elapsed time
    if (simulation != null) {
//      System.out.println("bbbb\n\n\n\nccc");
      simulation.update(elapsedTime);
    }
    view.setSimulation(simulation);
    view.update();
  }

  public static boolean loadNewSimulation(String configFileName) {
    // Load a new simulation from a configuration file
    configLoader = new ConfigLoader(configFileName);
    simulation = configLoader.simulation;
    view.setSimulation(simulation);
    return updateGridFromConfig(configFileName);
  }

  private static boolean updateGridFromConfig(String configFileName) {
    return false;
  }

  @Override
  public void start(Stage stage) {
    view = new View(stage, VIEW_RESOURCE_BUNDLE_NAME);
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

//    public static boolean reloadGridFromConfig() {
//        // Load grid configuration from file and update the grid
////        simulation set grid = to whatever the config says it was
//        return configLoader.loadCurrentConfigWithoutParams();
//    }
//
//    public static boolean saveSimulationToConfig(String configFileName, Map<String,String> Metadata) {
//        // Save current simulation state to a configuration file
//        return configLoader.saveConfig(configFileName, simulation);
//    }
//

}
