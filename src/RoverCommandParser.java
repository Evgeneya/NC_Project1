import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 1 on 11.03.2016.
 */
public class RoverCommandParser {

    private Rover rover;
    private BufferedReader fileReader;
    private static final Pattern pattern = Pattern.compile("(Move)\\s(\\d+)\\s(\\d+)" +
                                                            "|(Turn)\\s(NORTH|SOUTH|EAST|WEST)" +
                                                            "|(Import)\\s([\\w\\d]+[.]txt)");

    public RoverCommandParser(Rover rover) {
        this.rover = rover;
    }

    public void setFileReader(BufferedReader fileReader){
        this.fileReader = fileReader;
    }


    /**
     * Read line and recognize it.
     *
     * @return      Class implemented RoverCommand interface if the command is available
     *              or null if the command isn't available or command is null.
     *
     */
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
        if (m.matches()){
            if (m.group(1) != null){                     //move command
                int x = Integer.parseInt(m.group(2));
                int y = Integer.parseInt(m.group(3));
                return new MoveCommand(rover, x, y);
            }
            else
                if (m.group(4) != null)                  //turn command
                    return new TurnCommand(rover, Direction.valueOf(m.group(5)));
                else{                                   //import command - executing commands from another file
                    ArrayList<RoverCommand> listCommand = new ArrayList<RoverCommand>();
                    try {
                        BufferedReader reader = fileReader;
                        fileReader = new BufferedReader(new FileReader(m.group(7)));
                        RoverCommand roverCommand;
                        while ((roverCommand = readNextCommand()) != null){
                            listCommand.add(roverCommand);
                        }
                        fileReader.close();
                        fileReader = reader;
                        return new LoggingCommand(new ImportCommand(listCommand));
                    } catch (IOException e){
                        e.printStackTrace();
                        return null;
                    }

                }
        }
        else{
            System.out.println("Error in command: " + command);
            return null;
        }
    }
}
