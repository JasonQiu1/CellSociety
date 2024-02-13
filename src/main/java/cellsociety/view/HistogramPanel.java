package cellsociety.view;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Responsible for displaying a histogram of data.
 *
 * @author Jason Qiu
 */
class HistogramPanel extends UserInterfacePanel {

  BarChart<String, Number> histogram;
  Map<String, Color> stateColors;

  public HistogramPanel(Pane root, Map<Integer, String> stateNames,
      Map<String, Color> stateColors) {
    super(root, "histogram-panel");
    this.stateColors = stateColors;

    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("cell state");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("population");

    histogram = new BarChart<>(xAxis, yAxis);
    getRoot().getChildren().add(histogram);
  }

  public void setData(Map<String, Number> numOfEachState) {
    getRoot().getChildren().clear();
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("cell state");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("population");

    histogram = new BarChart<>(xAxis, yAxis);

    for (Map.Entry<String, Number> datum : numOfEachState.entrySet()) {
      XYChart.Series<String, Number> series = new XYChart.Series<>();
      series.getData().add(new XYChart.Data<>(datum.getKey(), datum.getValue()));
      histogram.getData().add(series);
    }

    getRoot().getChildren().add(histogram);
    //setBarColors();
  }

  private void setBarColors() {
    for (int state = 0; state < stateColors.size(); state++) {
      for (Node n : histogram.lookupAll(
          ".data" + Integer.toString(state) + ".chart-bar")) {
        Color stateColor = stateColors.get(Integer.toString(state));
        n.setStyle("-fx-bar-fill: rgb(" + stateColor.getRed() + "," + stateColor.getGreen() + ","
            + stateColor.getBlue() + ");");
      }
    }
  }
}
