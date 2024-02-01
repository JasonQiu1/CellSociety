package cellsociety.config;

import cellsociety.model.Simulation;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ConfigLoader {

  //    private Document configDocument;
  public Simulation simulation;
  public Grid grid;
  //    public Rule rule;
  public String fileName;


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
  public ConfigLoader(String fileName) {
    this.fileName = fileName;
    try {
      Document doc = readXmlFile(fileName);
      System.out.println(doc);
//            this.grid = buildGrid(doc);
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


  private void parseSimulationDetails() {
    Element simulationTypeElement = (Element) configDocument.getElementsByTagName("Simulation Type")
        .item(0);
    String simulationType = simulationTypeElement.getTextContent();

    Element authorElement = (Element) configDocument.getElementsByTagName("Author").item(0);
    String author = authorElement.getTextContent();

    Element descriptionElement = (Element) configDocument.getElementsByTagName("Description")
        .item(0);
    String description = descriptionElement.getTextContent();

    // Use these details as needed, perhaps storing them or passing them to the Simulation constructor
  }

  //    private void buildGrid() {
//        int width = Integer.parseInt(configDocument.getElementsByTagName("Width").item(0).getTextContent());
//        int height = Integer.parseInt(configDocument.getElementsByTagName("Height").item(0).getTextContent());
//
//        grid = new Grid(width, height); // Assuming Grid constructor takes width and height
//
//        NodeList cells = configDocument.getElementsByTagName("Cell");
//        for (int i = 0; i < cells.getLength(); i++) {
//            Element cell = (Element) cells.item(i);
//            int row = Integer.parseInt(cell.getAttribute("row"));
//            int col = Integer.parseInt(cell.getAttribute("col"));
//            String state = cell.getTextContent();
//
//            grid.setCell(row, col, state); // Assuming Grid has a method to set the state of a cell
//        }
//    }
  private Grid buildGrid(Document doc) {
    Element root = doc.getDocumentElement();
    int width = Integer.parseInt(getTextValue(root, "Width"));
    int height = Integer.parseInt(getTextValue(root, "Height"));
    Grid grid = new Grid(width, height); // Assuming Grid class has this constructor

    NodeList cellList = doc.getElementsByTagName("Cell");
    for (int i = 0; i < cellList.getLength(); i++) {
      Node node = cellList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element cell = (Element) node;
        int row = Integer.parseInt(cell.getAttribute("row"));
        int col = Integer.parseInt(cell.getAttribute("col"));
        String status = cell.getTextContent();
        // set cell status in the grid here based on your Grid class implementation
      }
    }
    return grid;
  }

  private void buildRuleSet() {
    // This would depend on how your RuleSet and Rules are defined
    ruleSet = new RuleSet();
    // Assume that RuleSet can be constructed and then rules added to it
  }

  private void trackParameters() {
    double probability = Double.parseDouble(
        configDocument.getElementsByTagName("probability").item(0).getTextContent());
    int distance = Integer.parseInt(
        configDocument.getElementsByTagName("distance").item(0).getTextContent());

    // Use these parameters as needed, perhaps storing them or passing them to the Simulation or RuleSet
  }
}




