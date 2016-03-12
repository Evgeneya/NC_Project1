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
    private RoverCommandParser programParser;

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
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        programParser = new RoverCommandParser(this, fileReader);

        while (true){
            RoverCommand roverCommand = programParser.readNextCommand();
            if (roverCommand == null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            roverCommand.execute();
        }



    }

    public GroundVisor getVisor(){
        return visor;
    }
}
