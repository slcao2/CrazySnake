import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class ColoredSnake {
	public final static int RED_COLOR = 0;
	public final static int GREEN_COLOR = 1;
	public final static int BLUE_COLOR = 2;
	
	private ArrayList<Point> snake;
	private ArrayList<Integer> snake_color;
	private int snake_size;
	
	public ColoredSnake() {
		snake = new ArrayList<Point>();
		snake.add(new Point(GameFrame.FRAME_WIDTH / CrazySnake.SNAKE_SCALE / 2, GameFrame.FRAME_HEIGHT / CrazySnake.SNAKE_SCALE / 2));
		snake.add(new Point(GameFrame.FRAME_WIDTH / CrazySnake.SNAKE_SCALE / 2, GameFrame.FRAME_HEIGHT / CrazySnake.SNAKE_SCALE / 2 + 1));
		snake.add(new Point(GameFrame.FRAME_WIDTH / CrazySnake.SNAKE_SCALE / 2, GameFrame.FRAME_HEIGHT / CrazySnake.SNAKE_SCALE / 2 + 2));
		snake_size = 3;
		
		Random rand = new Random();
		snake_color = new ArrayList<Integer>();
		snake_color.add(rand.nextInt(3));
		snake_color.add(rand.nextInt(3));
		snake_color.add(rand.nextInt(3));
	}
	
	public void growSnake(int color) {
		snake.add(new Point(snake.get(snake_size-1)));
		snake_color.add(color);
		snake_size++;
	}
	
	public void moveSnake(int direction) {
		Point moved_piece = snake.remove(snake_size-1);
		int snake_head_x = (int)snake.get(0).getX();
		int snake_head_y = (int)snake.get(0).getY();
		
		switch(direction) {
		case CrazySnake.UP_DIR:
			moved_piece.setLocation(snake_head_x, snake_head_y-1);
			break;
		case CrazySnake.RIGHT_DIR:
			moved_piece.setLocation(snake_head_x+1, snake_head_y);
			break;
		case CrazySnake.DOWN_DIR:
			moved_piece.setLocation(snake_head_x, snake_head_y+1);
			break;
		case CrazySnake.LEFT_DIR:
			moved_piece.setLocation(snake_head_x-1, snake_head_y);
			break;
		}
		snake.add(0, moved_piece);
	}
	
	public ArrayList<Point> getFullSnake() {
		return snake;
	}
	
	public ArrayList<Integer> getFullSnakeColor() {
		return snake_color;
	}
	
	public Point getSnakePiece(int index) {
		return snake.get(index);
	}
	
	public Point getSnakeHead() {
		return snake.get(0);
	}
	
	public Integer getSnakeColorPiece(int index) {
		return snake_color.get(index);
	}
	
	public int getSnakeSize() {
		return snake_size;
	}
}
