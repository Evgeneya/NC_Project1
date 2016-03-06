public class Main {

    public static void main(String[] args) {
        Rover r = new Rover();
        r.getVisor().setGround(new Ground(10, 10));
        r.move(19, 9);

    }
}
