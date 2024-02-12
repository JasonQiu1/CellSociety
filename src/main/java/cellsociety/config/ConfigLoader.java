package cellsociety.config;

import cellsociety.model.Cell;
import cellsociety.model.FallingSandWater;
import cellsociety.model.ForagingAnts;
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

  public static final String FILE_PATH = "./data/";
  //    private Document configDocument;
  public Simulation simulation;
  public Cell[][] grid;
  public RuleSet ruleSet;
  public String fileName;
  public Map<String, String> parameters;


  public ConfigLoader(String fileName) {
//      throws ParserConfigurationException, IOException, SAXException {
    try {
      this.fileName = fileName;
      Document doc = readXmlFile(FILE_PATH + fileName);
      parseSimulationDetails(doc);
      this.grid = buildGrid(doc);
      this.ruleSet = buildRuleSet(doc);
      simulation = new Simulation(ruleSet, grid);
      simulation.setConfigInfo(parameters);
    } catch (Exception e) {
      throw new RuntimeException("something went wrong with loading the config file\n" + e.getMessage());
    }

  }

  private Document readXmlFile(String fileName)
      throws ParserConfigurationException, IOException, SAXException {
    File xmlFile = new File(fileName);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
    return docBuilder.parse(xmlFile);
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
      }
    }
    return gridInitializer.getGrid();
  }

  private String getTextValue(Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    } else {
      throw new RuntimeException(tagName +
          " was not provided or null in the xml config file selected");
    }
  }

//  private void parseSimulationDetails(Document doc) {
//    Element root = doc.getDocumentElement();
//    Map<String, String> configInfo = new HashMap<>();
//
//    String author = getTextValue(root, "Author");
//    configInfo.put("Author", author);
//
//    String description = getTextValue(root, "Description");
//    configInfo.put("Description", description);
//
//    String Colors = getTextValue(root, "Colors");
//    configInfo.put("Colors", Colors);
//
//    simulation.setConfigInfo(configInfo);
//  }

  private void parseSimulationDetails(Document doc) {
    Element root = doc.getDocumentElement();
    Map<String, String> configInfo = new HashMap<>();

    NodeList nodeList = root.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        String tagName = element.getTagName();
        if (!tagName.equals("InitialConfig")) {
          if (tagName.equals("Parameters")) {
            NodeList parameterNodes = element.getElementsByTagName("*");
            for (int j = 0; j < parameterNodes.getLength(); j++) {
              Element parameterElement = (Element) parameterNodes.item(j);
              String parameterName = parameterElement.getTagName();
              String parameterValue = parameterElement.getTextContent();
              configInfo.put(parameterName, parameterValue);
            }
          } else {
            String textValue = getTextValue(root, tagName);
            configInfo.put(tagName, textValue);
          }
        }
      }
    }
    this.parameters = new HashMap<>(configInfo);

  }



  private RuleSet buildRuleSet(Document doc) {
    Element root = doc.getDocumentElement();
    String simulationType = getTextValue(root, "SimulationType");
//    System.out.println(simulationType);

    // Initialize a map to hold parameter names and values
//    parameters = new HashMap<>();
//    NodeList parameterList = root.getElementsByTagName("Parameters").item(0).getChildNodes();
//    for (int i = 0; i < parameterList.getLength(); i++) {
//      if (parameterList.item(i).getNodeType() == Node.ELEMENT_NODE) {
//        Element parameter = (Element) parameterList.item(i);
//        String name = parameter.getTagName();
//        String value = parameter.getTextContent().trim();
//        parameters.put(name, value);
//      }
//    }
    return buildGameRuleSet(simulationType);
  }

  private RuleSet buildGameRuleSet(String simulationType){
    RuleSet ruleSet = null;
//    Map<String, String> parameters = simulation.getConfigInfo();

    // Determine the type of simulation and instantiate the appropriate RuleSet
    switch (simulationType) {
      case "GameOfLife":
        ruleSet = new GameOfLife();
        break;
      case "SpreadingOfFire":
        ruleSet = new SpreadingOfFire(Double.valueOf(parameters.get("probabilityIgnite")) , Double.valueOf(parameters.get("probabilityTree")));
        break;
      case "Percolation":
        ruleSet = new Percolation();
        break;
      case "Segregation":
        ruleSet = new Segregation(Double.valueOf(parameters.get("segregationFactor")));
        break;
      case "WaterWorld":
        ruleSet = new WaterWorld();
        break;
      case "FallingSandWater":
        ruleSet = new FallingSandWater();
        break;
      case "ForagingAnts":
        ruleSet = new ForagingAnts();
        break;
      default:
        System.err.println("Unsupported simulation type: " + simulationType);
        break;
    }

    if (ruleSet == null) {
      throw new RuntimeException(
          "No valid RuleSet could be created for the given simulation type: " + simulationType);
    }

    return ruleSet;
  }


}




