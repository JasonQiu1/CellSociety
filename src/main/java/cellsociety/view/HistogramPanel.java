package cellsociety.view;

import java.util.Map;
import java.util.Map.Entry;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 * Responsible for displaying a histogram of data.
 *
 * @author Jason Qiu
 */
class HistogramPanel extends UserInterfacePanel {

  public static final int BAR_SPACING = 1;
  public static final int BAR_AXIS_SPACING = 1;
  GridPane histogram;

  public HistogramPanel(Pane root) {
    super(root, "histogram-panel");
    histogram = new GridPane();
    histogram.setPrefSize(root.getPrefWidth(), root.getPrefHeight());
    histogram.setAlignment(Pos.CENTER);
    getRoot().getChildren().add(histogram);
  }

  public void setXSeries(Map<Integer, Pair<Integer, Paint>> numOfEachState) {
    histogram.getChildren().clear();

    int xAxisLabelRow = 0;
    for (Pair<Integer, Paint> data : numOfEachState.values()) {
      if (data.getKey() > xAxisLabelRow) {
        xAxisLabelRow = data.getKey();
      }
    }
    xAxisLabelRow = 1;

    int currCol = 0;
    for (Entry<Integer, Pair<Integer, Paint>> entry : numOfEachState.entrySet()) {
      int barSize = entry.getValue().getKey();
      Paint barColor = entry.getValue().getValue();
      histogram.add(createBar(barSize, barColor), currCol, 0, 1, 1);
      histogram.add(new Text(Integer.toString(entry.getKey())), currCol, xAxisLabelRow, 1,
          1);

      currCol += BAR_SPACING;
    }

    Util.setEqualCellSize(histogram, 3, histogram.getColumnCount());
  }
  private Node createBar(int barSize, Paint color) {
    Label sizeLabel = new Label(Integer.toString(barSize));
    sizeLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
    AutoScalePane root = new AutoScalePane(0);
    root.addChildren(sizeLabel);
    return root;
  }
}
