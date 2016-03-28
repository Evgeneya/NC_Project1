package NC;
import NC.Commands.RoverCommand;
import NC.Parsers.CommandParser;
import NC.Parsers.TextRoverCommandParser;
import NC.Parsers.XmlRoverCommandParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 1 on 27.02.2016.
 */
public class Rover implements Turnable, Moveable, ProgramFileAware{

    private Direction direction;
    private int x;
    private int y;
    private GroundVisor visor = new GroundVisor();
    private TextRoverCommandParser textParser = new TextRoverCommandParser(this);
    private XmlRoverCommandParser xmlParser = new XmlRoverCommandParser(this);
    private BufferedReader fileReader;
    private Document document;


    public GroundVisor getVisor(){
        return visor;
    }

    @Override
    public void turnTo(Direction direction) {
        this.direction = direction;
        System.out.println("I turn to " + direction);
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("I move to x = " + x + ", y = " + y);
    }

    @Override
    public void executeProgramFile(String name){
        CommandParser parser = null;
        BufferedReader reader = null;
        try {
            if ((name.substring(name.length() - 3)).equals("txt")) {
                reader = new BufferedReader(new FileReader(name));
                this.fileReader = reader;
                parser = textParser;
                ((TextRoverCommandParser) parser).setFileReader(fileReader);
            }
            else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
                builder = factory.newDocumentBuilder();
                document = builder.parse(name);
                parser = xmlParser;
                ((XmlRoverCommandParser)parser).setDocument(document);
            }
            RoverCommand roverCommand;
            while ((roverCommand = parser.readNextCommand()) != null){
                roverCommand.execute();
            }
            if (reader != null) reader.close();
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
