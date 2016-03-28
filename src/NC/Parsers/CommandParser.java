package NC.Parsers;
import NC.Commands.RoverCommand;

/**
 * Created by 1 on 29.03.2016.
 */
public interface CommandParser {
    public RoverCommand readNextCommand();
}
