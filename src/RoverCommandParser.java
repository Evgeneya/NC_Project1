import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 1 on 11.03.2016.
 */
public class RoverCommandParser {

    private Rover rover;
    private BufferedReader fileReader;
    private static final Pattern pattern = Pattern.compile("(Move)\\s(\\d+)\\s(\\d+)|(Turn)\\s(NORTH|SOUTH|EAST|WEST)");

    public RoverCommandParser(Rover rover) {
        this.rover = rover;
        this.fileReader = rover.getBufferedReader();
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
            else{                                        //turn command
                return new TurnCommand(rover, Direction.valueOf(m.group(5)));
            }
        }
        else{
            System.out.println("Error in command: " + command);
            return null;
        }
    }
}
