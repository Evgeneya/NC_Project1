/**
 * Created by 1 on 06.03.2016.
 */
public class GroundCell {

    private CellState state;
    int x;
    int y;

    public void setState(CellState state){
        this.state = state;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CellState getState() {
        return state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
