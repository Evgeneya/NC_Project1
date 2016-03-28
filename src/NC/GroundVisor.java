package NC;
/**
 * Created by 1 on 06.03.2016.
 */
public class GroundVisor {

    private Ground ground;

    public boolean hasObstacles(int x, int y) throws GroundVisorException{
        if (x < 0 || y < 0)
            throw new GroundVisorException();
        if ((x > ground.getLength()) || (y > ground.getWidth())) {
                        throw new GroundVisorException();
        }

        return ground.getLandscape()[x][y].getState() == CellState.OCCUPIED;
    }

    public void setGround(Ground ground){

        this.ground = ground;
    }


}
