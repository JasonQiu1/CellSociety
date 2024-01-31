package cellsociety;

import cellsociety.config.ConfigLoader;
import cellsociety.model.Simulation;
import cellsociety.view.View;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.util.Duration;


public class Game extends Application {

    public static View view;
    public static Simulation simulation;
    public static ConfigLoader configLoader;
    private Stage stage;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Initialize UI and components here
        view = new View(stage);
//        simulation = new Simulation();
//        this configloader shouldn't it return me the simulation
//        what else do I need the config loader for?
//        it will return a simulation make it into a instance variable
        configLoader = new ConfigLoader();


//        code from breakout that might be useful
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
//
//        // Configure and show the primary stage
//        stage.show();
//
//        // Start the game loop
//        gameLoop();
    }


//    @Override
    private static void step(double elapsedTime) {
        // Update simulation and UI based on elapsed time
        if (simulation != null) {
            simulation.update(elapsedTime);
        }
        view.setSimulation(simulation);
        view.draw();
    }

//    public static boolean reloadGridFromConfig() {
//        // Load grid configuration from file and update the grid
////        simulation set grid = to whatever the config says it was
//        return configLoader.loadCurrentConfigWithoutParams();
//    }
//
//    public static boolean saveSimulationToConfig(String configFileName) {
//        // Save current simulation state to a configuration file
//        return configLoader.saveConfig(configFileName, simulation);
//    }
//
//    public static boolean loadNewSimulation(String configFileName) {
//        // Load a new simulation from a configuration file
//        view.setSimulation(simulation);
//        return updateGridFromConfig(configFileName);
//    }
}
