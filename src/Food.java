import java.awt.Point;
import java.util.Random;


public class Food {
	public final static int RED_FOOD = 0;
	public final static int GREEN_FOOD = 1;
	public final static int BLUE_FOOD = 2;
	
	private int food_color;
	private Point location;
	
	public Food() {
		Random rand = new Random();
		food_color = rand.nextInt(3);
		
		location = new Point();
		location.setLocation(rand.nextInt(GameFrame.FRAME_WIDTH / CrazySnake.SNAKE_SCALE - 1), rand.nextInt(GameFrame.FRAME_HEIGHT / CrazySnake.SNAKE_SCALE - 1));
	}
	
	public int getFoodColor() {
		return food_color;
	}
	
	public Point getLocation() {
		return location;
	}
	
}
