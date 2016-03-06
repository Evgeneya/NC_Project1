

/**
 * Created by 1 on 06.03.2016.
 */
public class Ground {

    private GroundCell[][] landscape;
    private int length;
    private int width;

    public Ground(int a, int b){
        length = a;
        width = b;
        landscape = new GroundCell[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                landscape[i][j] = new GroundCell();
                landscape[i][j].setState(CellState.FREE);
            }
        }
    }

    public GroundCell[][] getLandscape() {
        return landscape;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public void setLandscape(GroundCell[][] landscape) {
        this.landscape = landscape;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
