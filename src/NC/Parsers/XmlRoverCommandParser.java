/**
 * Created by 1 on 28.03.2016.
 */

package NC.Parsers;
import NC.*;
import NC.Commands.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class XmlRoverCommandParser implements CommandParser {

    private Rover rover;
    private Document document;
    private NodeList list;
    private int currCommand;
    private int lengthList;

    public XmlRoverCommandParser(Rover rover) {
        this.rover = rover;
    }

    public void setDocument(Document document){
        this.document = document;
        list = document.getChildNodes().item(0).getChildNodes();
        currCommand = 0;
        lengthList = list.getLength();
    }

    public RoverCommand readNextCommand() {
        if (currCommand >= lengthList)
            return null;
        Node node = list.item(currCommand);
        while (node.getNodeType() != Node.ELEMENT_NODE) {
            if ((++currCommand) < lengthList)
                node = list.item(currCommand);
            else return null;
        }
        String element = node.getNodeName();
        NamedNodeMap attr = node.getAttributes();
        int numAttr = attr.getLength();
        switch (element) {
            case "Move": {
                if (numAttr == 2) {
                    int x = Integer.parseInt(attr.getNamedItem("x").getNodeValue());
                    int y = Integer.parseInt(attr.getNamedItem("y").getNodeValue());
                    currCommand++;
                    return new LoggingCommand(new MoveCommand(rover, x, y));
                } else {
                    System.out.println("Error in Move command. Error in number of attributes.");
                    return null;
                }
            }
            case "Turn": {
                if (numAttr == 1) {
                    currCommand++;
                    return new LoggingCommand(new TurnCommand(rover, Direction.valueOf(attr.getNamedItem("direction").getNodeValue())));
                } else {
                    System.out.println("Error in Turn command. Error in number of attributes.");
                    return null;
                }
            }
            case "Import": {
                if (numAttr == 1) {
                    String name = attr.getNamedItem("name").getNodeValue();
                    CommandParser parser = null;
                    BufferedReader reader = null;
                    ArrayList<RoverCommand> listCommand = new ArrayList<RoverCommand>();
                    try {
                        if ((name.substring(name.length() - 3)).equals("txt")) {   //text file
                            parser = new TextRoverCommandParser(rover);
                            reader = new BufferedReader(new FileReader(name));
                            ((TextRoverCommandParser) parser).setFileReader(reader);
                        } else {    //xml file
                            parser = new XmlRoverCommandParser(rover);
                            ((XmlRoverCommandParser) parser).setDocument(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(attr.getNamedItem("name").getNodeValue()));
                        }
                        RoverCommand roverCommand;
                        while ((roverCommand = parser.readNextCommand()) != null) {
                            listCommand.add(roverCommand);
                        }
                        currCommand++;
                        if (reader != null) reader.close();
                        return new LoggingCommand(new ImportCommand(listCommand));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            default:
                System.out.println("Unknown command: " + element);
                return null;
        }
    }
}
