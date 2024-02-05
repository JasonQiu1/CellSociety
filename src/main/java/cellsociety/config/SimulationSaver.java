package cellsociety.config;

import cellsociety.model.Grid;
import cellsociety.model.Simulation;
import java.io.File;
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

  public void saveSimulationState(Simulation simulation, String filePath) {
    System.out.println("\n\n\n llllllllll");
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // Root element
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("config");
      doc.appendChild(rootElement);

      // Add simulation details like Author, Description, Width, Height, etc.
//      addSimulationDetails(doc, rootElement, simulation);

      // Add current grid state
//      addGridState(doc, rootElement, simulation.getGrid());

      int[][] grid = new int[10][9];
      grid[0][1] = 7;
      grid[1][8] = 69;
      addGridStateTest(doc, rootElement, grid);

      // Add other parameters if needed
      addSimulationParameters(doc, rootElement, simulation);

      // Write the content into XML file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(filePath));

      transformer.transform(source, result);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

//  private void addSimulationDetails(Document doc, Element rootElement, Simulation simulation) {
//    // Add elements like Author, Description, etc. based on your requirements
//  }

//  private void addGridState(Document doc, Element rootElement, Grid grid) {
//    // Assuming you have a way to iterate over the grid and get cell states
//    Element gridElement = doc.createElement("InitialConfig");
//    for (int row = 0; row < grid.getHeight(); row++) {
//      for (int col = 0; col < grid.getWidth(); col++) {
//        Element cellElement = doc.createElement("Cell");
//        cellElement.setAttribute("col", String.valueOf(col));
//        cellElement.setAttribute("row", String.valueOf(row));
//        cellElement.setTextContent(String.valueOf(grid.getCellState(row, col))); // Assuming getCellState method exists
//        gridElement.appendChild(cellElement);
//      }
//    }
//    rootElement.appendChild(gridElement);
//  }

  private void addGridStateTest(Document doc, Element rootElement, int[][] grid) {
    // Assuming you have a way to iterate over the grid and get cell states
    Element gridElement = doc.createElement("InitialConfig");
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 9; col++) {
        Element cellElement = doc.createElement("Cell");
        cellElement.setAttribute("col", String.valueOf(col));
        cellElement.setAttribute("row", String.valueOf(row));
        cellElement.setTextContent(String.valueOf(grid[row][col])); // Assuming getCellState method exists
        gridElement.appendChild(cellElement);
      }
    }
    rootElement.appendChild(gridElement);
  }

  private void addSimulationParameters(Document doc, Element rootElement, Simulation simulation) {
    // Add simulation parameters like Distance, Probability, etc.
  }
}

