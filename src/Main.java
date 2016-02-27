/**
 * Created by 1 on 27.02.2016.
 */
public class Main {
    public static void main(String[] args){
        Rover rover = new Rover();
        rover.turnTo(Direction.EAST);
        rover.move(21, 43);
    }
}
