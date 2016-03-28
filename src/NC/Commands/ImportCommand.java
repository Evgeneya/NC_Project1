package NC.Commands;
import java.util.ArrayList;

/**
 * Created by 1 on 19.03.2016.
 */
public class ImportCommand implements RoverCommand {

    private ArrayList<RoverCommand> listCommand;

    public ImportCommand(ArrayList<RoverCommand> listCommand){
        this.listCommand = listCommand;
    }

    @Override
    public void execute() {
        for (int i = 0; i < listCommand.size(); i++) {
            listCommand.get(i).execute();
        }
    }

    @Override
    public String toString(){
        return "Import command";
    }
}
