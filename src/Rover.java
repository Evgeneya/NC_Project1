/**
 * Created by 1 on 27.02.2016.
 */
public class Rover implements Turnable, Moveable{
    private Direction direction;
    private int x, y;

    @Override
    public void turnTo(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
