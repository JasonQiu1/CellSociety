package cellsociety.config;

import cellsociety.model.Cell;
import cellsociety.model.GameOfLife;
import cellsociety.model.InitializeGrid;
import cellsociety.model.Percolation;
import cellsociety.model.RuleSet;
import cellsociety.model.Segregation;
import cellsociety.model.Simulation;
import cellsociety.model.SpreadingOfFire;
import cellsociety.model.WaterWorld;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigLoader {

  //    private Document configDocument;
  public Simulation simulation;
  public Cell[][] grid;
  public RuleSet ruleSet;
  public String fileName;
  public String FILE_PATH = "./data/";
  public Map<String, String> parameters;


  public ConfigLoader(String fileName) {
    this.fileName = fileName;
    try {
      Document doc = readXmlFile(FILE_PATH + fileName);
//      System.out.println(doc);
      this.grid = buildGrid(doc);
      this.ruleSet = buildRuleSet(doc);

//      System.out.println(grid);

//      buildRuleSet(doc);
      simulation = new Simulation(ruleSet, grid);
      parseSimulationDetails(doc);
//            // rule object can be initialized here with buildRuleset method
//            trackParameters(doc);
//            this.simulation = buildSimulation();
    } catch (Exception e) {
      e.printStackTrace();
      // Handle exceptions or errors accordingly
    }
  }

  private Document readXmlFile(String fileName)
      throws ParserConfigurationException, IOException, SAXException {
    File xmlFile = new File(fileName);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    return dBuilder.parse(xmlFile);
  }

  private Cell[][] buildGrid(Document doc) {
    Element root = doc.getDocumentElement();
    int width = Integer.parseInt(getTextValue(root, "Width"));
    int height = Integer.parseInt(getTextValue(root, "Height"));
    InitializeGrid gridInitializer = new InitializeGrid(width, height);


    NodeList cellList = doc.getElementsByTagName("Cell");
    for (int i = 0; i < cellList.getLength(); i++) {
      Node node = cellList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element cell = (Element) node;
        int row = Integer.parseInt(cell.getAttribute("row"));
        int col = Integer.parseInt(cell.getAttribute("col"));
        int val = Integer.parseInt(cell.getTextContent().trim());
        gridInitializer.setCellState(row, col, val);
//        System.out.println("row");
//        System.out.println(row);
//        System.out.println(col);
//        System.out.println(val);
        // set cell status in the grid here based on your cellsociety.model.Grid class implementation
      }
    }
//    return grid;
    return gridInitializer.getGrid();
  }

  private String getTextValue(Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    } else {
      // FIXME: empty string or exception? In some cases it may be an error to not find any text
      return "";
    }
  }

  private void parseSimulationDetails(Document doc) {
    Element root = doc.getDocumentElement();
    String author = getTextValue(root, "Author");
    String description = getTextValue(root, "Description");
//    System.out.println(author);
//    System.out.println(description);

    // Assuming Simulation has setters for author and description
    simulation.setAuthor(author);
    simulation.setDescription(description);
  }

  //  private RuleSet buildRuleSet(Document doc) {
  private RuleSet buildRuleSet(Document doc) {
    Element root = doc.getDocumentElement();
    String simulationType = getTextValue(root, "SimulationType");
    System.out.println(simulationType);

    // Initialize a map to hold parameter names and values
    parameters = new HashMap<>();
    NodeList parameterList = root.getElementsByTagName("Parameters").item(0).getChildNodes();
    for (int i = 0; i < parameterList.getLength(); i++) {
      if (parameterList.item(i).getNodeType() == Node.ELEMENT_NODE) {
        Element parameter = (Element) parameterList.item(i);
        String name = parameter.getTagName();
        String value = parameter.getTextContent().trim();
        parameters.put(name, value);
      }
    }
    System.out.println(parameters);

//    ruleSet = new GameOfLife(this.grid);
//    FiniteGrid f1 = new FiniteGrid(ruleSet);
//    f1.update();
    return buildGameRuleSet(simulationType, this.grid, parameters);
  }

  private RuleSet buildGameRuleSet(String simulationType, Cell[][] grid,
      Map<String, String> parameters) {
    RuleSet ruleSet = null;

    // Determine the type of simulation and instantiate the appropriate RuleSet
    switch (simulationType) {
      case "GameOfLife":
        ruleSet = new GameOfLife(grid);
        break;
      case "SpreadingOfFire":
        ruleSet = new SpreadingOfFire(grid);
        break;
      case "Percolation":
        ruleSet = new Percolation(grid);
        break;
      case "Segregation":
        ruleSet = new Segregation(grid);
        break;
      case "WaterWorld":
        ruleSet = new WaterWorld(grid);
        break;
      default:
        System.err.println("Unsupported simulation type: " + simulationType);
        break;
    }

    if (ruleSet == null) {
      throw new IllegalArgumentException(
          "No valid RuleSet could be created for the given simulation type: " + simulationType);
    }

    return ruleSet;
  }


}

//    public ConfigLoader(String fileName) {
//        this.fileName = fileName;
//        loadConfigFile();
//        parseSimulationDetails();
//        buildGrid();
//        buildRuleSet();
//        trackParameters();
//        buildSimulation();
//    }
//
//    private void loadConfigFile() {
//        try {
//            File configFile = new File(fileName);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            configDocument = dBuilder.parse(configFile);
//            configDocument.getDocumentElement().normalize();
//        } catch (Exception e) {
//            e.printStackTrace(); // Replace with more robust error handling
//        }
//    }

//  private void parseSimulationDetails() {
//    Element simulationTypeElement = (Element) configDocument.getElementsByTagName("Simulation Type")
//        .item(0);
//    String simulationType = simulationTypeElement.getTextContent();
//
//    Element authorElement = (Element) configDocument.getElementsByTagName("Author").item(0);
//    String author = authorElement.getTextContent();
//
//    Element descriptionElement = (Element) configDocument.getElementsByTagName("Description")
//        .item(0);
//    String description = descriptionElement.getTextContent();
//
//    // Use these details as needed, perhaps storing them or passing them to the Simulation constructor
//  }
//
//    private void buildGrid() {
//        int width = Integer.parseInt(configDocument.getElementsByTagName("Width").item(0).getTextContent());
//        int height = Integer.parseInt(configDocument.getElementsByTagName("Height").item(0).getTextContent());
//
//        grid = new cellsociety.model.Grid(width, height); // Assuming cellsociety.model.Grid constructor takes width and height
//
//        NodeList cells = configDocument.getElementsByTagName("Cell");
//        for (int i = 0; i < cells.getLength(); i++) {
//            Element cell = (Element) cells.item(i);
//            int row = Integer.parseInt(cell.getAttribute("row"));
//            int col = Integer.parseInt(cell.getAttribute("col"));
//            String state = cell.getTextContent();
//
//            grid.setCell(row, col, state); // Assuming cellsociety.model.Grid has a method to set the state of a cell
//        }
//    }



