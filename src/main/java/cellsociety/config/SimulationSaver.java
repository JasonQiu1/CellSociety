package cellsociety.config;

import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SimulationSaver {

  public static String FILE_SAVE_PATH = "./data/saves/";


  public static void saveSimulationState(Simulation simulation, String filePath) {
    System.out.println("\n\n\n starting to save");
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // Root element
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("config");
      doc.appendChild(rootElement);

      // Add simulation details like Author, Description, Width, Height, etc.
      addSimulationDetails(doc, rootElement, simulation);

      // Add current grid state
      addGridState(doc, rootElement, simulation.getGrid());

//      int[][] grid = new int[10][9];
//      grid[0][1] = 7;
//      grid[1][8] = 69;
//      addGridStateTest(doc, rootElement, grid);

      HashMap<String, String> params = new HashMap<>();
      params.put("probability", "0.9");

      // Add other parameters if needed
      addSimulationParameters(doc, rootElement, params);

      addSimulationType(doc, rootElement, simulation.getSimulationType());

      // Write the content into XML file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(FILE_SAVE_PATH + filePath));

      transformer.transform(source, result);
      System.out.println("\n\n\n the save file should have been saved");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void addSimulationDetails(Document doc,
      Element rootElement, Simulation simulation) {
    Map<String, String> configInfo = simulation.getConfigInfo();

    for (Map.Entry<String, String> entry : configInfo.entrySet()) {
      Element detailElement = doc.createElement(entry.getKey());
      detailElement.setTextContent(entry.getValue());
      rootElement.appendChild(detailElement);
    }
  }

  private static void addGridState(Document doc, Element rootElement, Grid grid) {
    Element widthElement = doc.createElement("Width");
    widthElement.setTextContent(String.valueOf(grid.getNumCols()));
    rootElement.appendChild(widthElement);

    Element heightElement = doc.createElement("Height");
    heightElement.setTextContent(String.valueOf(grid.getNumRows()));
// The length of the grid array gives the height
    rootElement.appendChild(heightElement);
    // Assuming you have a way to iterate over the grid and get cell states
    Element gridElement = doc.createElement("InitialConfig");
    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumCols(); col++) {
        Element cellElement = doc.createElement("Cell");
        cellElement.setAttribute("col", String.valueOf(col));
        cellElement.setAttribute("row", String.valueOf(row));
        cellElement.setTextContent(String.valueOf(grid.getCellState(row, col)));
// Assuming getCellState method exists
        gridElement.appendChild(cellElement);
      }
    }
    rootElement.appendChild(gridElement);
  }

//  private static void addGridStateTest(Document doc, Element rootElement, int[][] grid) {
//    // First, add the Width and Height elements based on the grid dimensions
//    Element widthElement = doc.createElement("Width");
//    widthElement.setTextContent(
//        String.valueOf(grid[0].length)); // Assuming grid[0].length gives the width
//    rootElement.appendChild(widthElement);
//
//    Element heightElement = doc.createElement("Height");
//    heightElement.setTextContent(
//        String.valueOf(grid.length)); // The length of the grid array gives the height
//    rootElement.appendChild(heightElement);
//
//    // Then, add the InitialConfig element with all the Cell elements
//    Element initialConfigElement = doc.createElement("InitialConfig");
//    for (int row = 0; row < grid.length; row++) {
//      for (int col = 0; col < grid[row].length; col++) {
//        Element cellElement = doc.createElement("Cell");
//        cellElement.setAttribute("col", String.valueOf(col));
//        cellElement.setAttribute("row", String.valueOf(row));
//        cellElement.setTextContent(String.valueOf(grid[row][col]));
//        initialConfigElement.appendChild(cellElement);
//      }
//    }
//    rootElement.appendChild(initialConfigElement);
//  }


  private static void addSimulationParameters(Document doc, Element rootElement,
      Map<String, String> parameters) {
    Element parametersElement = doc.createElement("Parameters");
    for (Map.Entry<String, String> entry : parameters.entrySet()) {
      Element parameterElement = doc.createElement(entry.getKey());
      parameterElement.setTextContent(entry.getValue());
      parametersElement.appendChild(parameterElement);
    }
    rootElement.appendChild(parametersElement);
  }

  public static void addSimulationType(Document doc, Element rootElement, String simulationType) {
    Element simulationTypeElement = doc.createElement("SimulationType");
    simulationTypeElement.setTextContent(simulationType);
    rootElement.appendChild(simulationTypeElement);
  }
}

