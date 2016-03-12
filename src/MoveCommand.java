/**
 * Created by 1 on 11.03.2016.
 */
public class MoveCommand implements RoverCommand {

    Moveable moveable;
    int x, y;

    public void execute(){
        moveable.move(x, y);
    }

    public MoveCommand(Moveable moveable, int x, int y){
        this.moveable = moveable;
        this.x = x;
        this.y = y;
    }
}
