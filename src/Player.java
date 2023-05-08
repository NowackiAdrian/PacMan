import java.awt.Point;

public class Player {
    private Point position;

    public Player(int x, int y) {
        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

//    public void move(int dx, int dy) {
//        Point newPosition = new Point(position.x + dx, position.y + dy);
//        if (GameBoard.isMovable(newPosition)) {
//            position = newPosition;
//        }
    }

