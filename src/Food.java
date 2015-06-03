import java.awt.Point;
import java.util.Random;


public class Food {
	
	private int food_color;		//Color based on ColoredSnake colors
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
