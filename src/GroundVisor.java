/**
 * Created by 1 on 06.03.2016.
 */
public class GroundVisor {

    private Ground ground;

    public boolean hasObstacles(int length, int width){
        GroundCell[][] gCell = ground.getLandscape();
        try {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    if ((gCell[i][j].x > ground.getLength()) || (gCell[i][j].y > ground.getWidth())) {
                        throw new GroundVisorException();
                    }
                }
            }
        }
        catch (GroundVisorException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void setGround(Ground ground){
        this.ground = ground;
    }


}
