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
  public static View view;
  public static Simulation simulation;
  public static ConfigLoader configLoader;
  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    view = new View(stage);
    configLoader = new ConfigLoader("example.xml");
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private static void step(double elapsedTime) {
    // Update simulation and UI based on elapsed time
    if (simulation != null) {
      simulation.update(elapsedTime);
    }
    view.setSimulation(simulation);
    view.draw();
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
