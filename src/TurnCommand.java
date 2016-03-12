/**
 * Created by 1 on 11.03.2016.
 */
public class TurnCommand implements RoverCommand {

    Turnable turnable;
    Direction direction;

    public void execute(){
        turnable.turnTo(direction);
    }

    public TurnCommand(Turnable turnable, Direction direction){
        this.turnable = turnable;
        this.direction = direction;
    }
}
