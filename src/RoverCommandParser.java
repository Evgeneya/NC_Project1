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

    public RoverCommandParser(Rover rover, BufferedReader reader) {
        this.rover = rover;
        this.fileReader = reader;
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
        String regex = "(M)\\s(\\d+)\\s(\\d+)|(T)\\s([NSEW])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(command);
        if (m.matches()){
            if (m.group(1) != null){                     //move command
                int x = Integer.parseInt(m.group(2));
                int y = Integer.parseInt(m.group(3));
                return new MoveCommand(rover, x, y);
            }
            else{                                        //turn command
                Direction direction;
                switch (m.group(5).charAt(0)){
                    case 'N':
                        direction = Direction.NORTH;
                        break;
                    case 'E':
                        direction = Direction.EAST;
                        break;
                    case 'W':
                        direction = Direction.WEST;
                        break;
                    case 'S':
                        direction = Direction.SOUTH;
                        break;
                    default:
                        System.out.println("Error in command!");
                        return null;
                }
                return new TurnCommand(rover, direction);
            }
        }
        else{
            System.out.println("Error in command!");
            return null;
        }
    }
}
