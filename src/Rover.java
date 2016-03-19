import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by 1 on 27.02.2016.
 */
public class Rover implements Turnable, Moveable, ProgramFileAware{

    private Direction direction;
    private int x;
    private int y;
    private GroundVisor visor = new GroundVisor();
    private RoverCommandParser programParser = new RoverCommandParser(this);
    private BufferedReader fileReader;


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
        try {
            fileReader = new BufferedReader(new FileReader(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        programParser.setFileReader(fileReader);
        RoverCommand roverCommand;
        while ((roverCommand = programParser.readNextCommand()) != null){
            roverCommand.execute();
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
