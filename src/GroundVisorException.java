/**
 * Created by 1 on 06.03.2016.
 */
public class GroundVisorException extends RuntimeException {

    private static String message = "Ground out of bounds.";

    public GroundVisorException(){
        super(message);
    }

}
