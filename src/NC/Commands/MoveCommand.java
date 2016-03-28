package NC.Commands;
import NC.*;
/**
 * Created by 1 on 11.03.2016.
 */
public class MoveCommand implements RoverCommand {

    private Moveable moveable;
    private int x, y;

    public void execute(){
        moveable.move(x, y);
    }

    public MoveCommand(Moveable moveable, int x, int y){
        this.moveable = moveable;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "Move command";
    }
}
