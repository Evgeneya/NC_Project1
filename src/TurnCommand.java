/**
 * Created by 1 on 11.03.2016.
 */
public class TurnCommand implements RoverCommand {

    private Turnable turnable;
    private Direction direction;

    public void execute(){
        turnable.turnTo(direction);
    }

    public TurnCommand(Turnable turnable, Direction direction){
        this.turnable = turnable;
        this.direction = direction;
    }
}
