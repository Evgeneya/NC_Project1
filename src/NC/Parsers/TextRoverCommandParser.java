package NC.Parsers;
import NC.Commands.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import NC.*;
/**
 * Created by 1 on 11.03.2016.
 */
public class TextRoverCommandParser implements CommandParser{

    private Rover rover;
    private BufferedReader fileReader;
    private static final Pattern pattern = Pattern.compile("(Move)\\s(\\d+)\\s(\\d+)" +
                                                            "|(Turn)\\s(NORTH|SOUTH|EAST|WEST)" +
                                                            "|(Import)\\s([\\w\\d]+[.](txt)|[\\w\\d]+[.](xml))");

    public TextRoverCommandParser(Rover rover) {
        this.rover = rover;
    }



    public void setFileReader(BufferedReader fileReader){
        this.fileReader = fileReader;
    }


    public RoverCommand readNextCommand() {
        String command;
        try {
            command = fileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (command == null)
            return null;
        Matcher m = pattern.matcher(command);
        if (m.matches()) {
            if (m.group(1) != null) {                     //move command
                int x = Integer.parseInt(m.group(2));
                int y = Integer.parseInt(m.group(3));
                return new LoggingCommand(new MoveCommand(rover, x, y));
            } else if (m.group(4) != null)                  //turn command
                return new LoggingCommand(new TurnCommand(rover, Direction.valueOf(m.group(5))));
            else {                                   //import command - executing commands from another file
                CommandParser parser = null;
                BufferedReader reader = null;
                try{
                    if (m.group(8) != null) {      //text file
                        parser = new TextRoverCommandParser(rover);
                        reader = new BufferedReader(new FileReader(m.group(7)));
                        ((TextRoverCommandParser) parser).setFileReader(reader);
                    }
                    else {       //xml file
                        parser = new XmlRoverCommandParser(rover);
                        ((XmlRoverCommandParser)parser).setDocument(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(m.group(7)));

                    }
                    ArrayList<RoverCommand> listCommand = new ArrayList<RoverCommand>();
                    RoverCommand roverCommand;
                    while ((roverCommand = parser.readNextCommand()) != null) {
                        listCommand.add(roverCommand);
                    }
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
                return null;
            }
        } else {
            System.out.println("Error in command: " + command);
            return null;
        }
    }
}
