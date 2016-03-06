/**
 * Created by 1 on 06.03.2016.
 */
public class GroundVisor {

    private Ground ground;

    public boolean hasObstacles(int a, int b){
        GroundCell[][] gCell = ground.getLandscape();
        try {
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    if ((gCell[i][j].x > ground.getLength()) || (gCell[i][j].y > ground.getWidth())) {
                        throw new GroundVisorException();
                    }
                }
            }
        }
        catch (GroundVisorException e){
            System.out.println("GroundVisorException!");
            return false;
        }

        return true;
    }

    public void setGround(Ground ground){
        this.ground = ground;
    }


}
