import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JPanel;

/*	TODO List
 * 
 * 	Show something on death
 * 	Power-ups
 */

/*	Power-ups List
 * 
 * 	Increase sleep time
 * 	Decrease snake size
 */
public class CrazySnake extends JPanel implements KeyListener {
	public final static int UP_DIR = 0;
	public final static int RIGHT_DIR = 1;
	public final static int DOWN_DIR = 2;
	public final static int LEFT_DIR = 3;
	
	public final static int SNAKE_SCALE = 16;		//Size of snake and food
	public static final int FOOD_SPAWNRATE = 10;	//Higher means slower spawn
	
	private static boolean esc_clicked = false;
	
	private ColoredSnake snake;
	private int current_dir;
	private int next_dir;
	private int screen_color;
	
	private int score;
	private ArrayList<Food> food_list;
	private boolean dead;
	private int sleep_time;
	private boolean game_start;
	
	private int color_eaten;
	private int color_streak;
	
	private Color color_1;
	private Color color_2;
	private Color color_3;
	
	private int up_key;
	private int left_key;
	private int down_key;
	private int right_key;
	
	private int change_color_1_key;
	private int change_color_2_key;
	private int change_color_3_key;
	
	private int reset_key;
	
	private int highscore;
	
	public CrazySnake() {
		snake = new ColoredSnake();
		current_dir = UP_DIR;
		next_dir = UP_DIR;
		screen_color = -1;
		
		food_list = new ArrayList<Food>();
		for(int i = 0; i < 5; i++) {
			addFood();
		}
		
		score = 0;
		dead = false;
		sleep_time = 100;
		game_start = false;
		
		color_eaten = -1;
		color_streak = 0;
		
		color_1 = Color.RED.darker();
		color_2 = Color.GREEN.darker();
		color_3 = Color.BLUE.darker();
		
		up_key = KeyEvent.VK_W;
		left_key = KeyEvent.VK_A;
		down_key = KeyEvent.VK_S;
		right_key = KeyEvent.VK_D;
		
		change_color_1_key = KeyEvent.VK_J;
		change_color_2_key = KeyEvent.VK_K;
		change_color_3_key = KeyEvent.VK_L;
		
		reset_key = KeyEvent.VK_SPACE;
		
		try {
			highscore = getHighScore();
		} catch (IOException e) {
			System.out.println("getHighScore() failed");
		}
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void readFromOptions(OptionsMenu options) {
		color_1 = options.getColorOne();
		color_2 = options.getColorTwo();
		color_3 = options.getColorThree();
		
		up_key = options.getUpKey();
		left_key = options.getLeftKey();
		down_key = options.getDownKey();
		right_key = options.getRightKey();
		
		change_color_1_key = options.getChangeColorOneKey();
		change_color_2_key = options.getChangeColorTwoKey();
		change_color_3_key = options.getChangeColorThreeKey();
		
		reset_key = options.getResetKey();
	}
	
	public int getHighScore() throws IOException {
		FileReader highscoreReader = new FileReader("highscore.txt");
		String readString = "";
		char readChar = 0;
		while((readChar = (char) highscoreReader.read()) != -1 && readChar != '\n') {
			if(readChar >= '0' && readChar <= '9') {
				readString = readString + readChar;
			}
		}
		highscoreReader.close();
		return Integer.parseInt(readString);
	}
	
	public void saveHighScore() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("highscore.txt","UTF-8");
		writer.println(highscore);
		writer.close();
	}
	
	public boolean inFoodList(Food food) {
		for(int i = 0; i < food_list.size(); i++) {
			if(food_list.get(i).getLocation() == food.getLocation()) { 
				return true;
			}
		}
		return false;
	}
	
	public void addFood() {
		Food food = new Food();
		if(!inFoodList(food)) {
			food_list.add(food);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Paint Screen Color
		switch(screen_color) {
		case ColoredSnake.COLOR_1:
			g.setColor(color_1);
			break;
		case ColoredSnake.COLOR_2:
			g.setColor(color_2);
			break;
		case ColoredSnake.COLOR_3:
			g.setColor(color_3);
			break;
		default:
			g.setColor(Color.WHITE.darker());
			break;
		}
		g.fillRect(0, 0, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		
		//Paint Food
		int food_list_size = food_list.size();
		for(int i = 0; i < food_list_size; i++) {
			Food single_food = food_list.get(i);
			int single_food_x = (int) single_food.getLocation().getX();
			int single_food_y = (int) single_food.getLocation().getY();
			switch(single_food.getFoodColor()) {
			case ColoredSnake.COLOR_1:
				g.setColor(color_1);
				break;
			case ColoredSnake.COLOR_2:
				g.setColor(color_2);
				break;
			case ColoredSnake.COLOR_3:
				g.setColor(color_3);
				break;
			}
			g.fillRect(SNAKE_SCALE * single_food_x, SNAKE_SCALE * single_food_y, SNAKE_SCALE, SNAKE_SCALE);
		}
		
		//Paint Snake
		int snake_size = snake.getSnakeSize();
		g.setColor(Color.BLACK);
		Point snake_head = snake.getSnakeHead();
		g.fillRect(SNAKE_SCALE * (int)snake_head.getX(), SNAKE_SCALE * (int)snake_head.getY(), SNAKE_SCALE, SNAKE_SCALE);
		for(int i = 1; i < snake_size; i++) {
			Point snake_piece = snake.getSnakePiece(i);
			int snake_color = snake.getSnakeColorPiece(i);
			switch(snake_color) {
			case ColoredSnake.COLOR_1:
				g.setColor(color_1);
				break;
			case ColoredSnake.COLOR_2:
				g.setColor(color_2);
				break;
			case ColoredSnake.COLOR_3:
				g.setColor(color_3);
				break;
			}
			g.fillRect(SNAKE_SCALE * (int)snake_piece.getX(), SNAKE_SCALE * (int)snake_piece.getY(), SNAKE_SCALE, SNAKE_SCALE);
		}
		
		//Paint Score
		g.setColor(Color.WHITE);
		Font score_font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g.setFont(score_font);
		g.drawString("Score: " + score, 2, 17);
		
		//Paint Highscore
		g.drawString("Highscore: " + highscore , GameFrame.FRAME_WIDTH - 170, 17);
	}
	
	public int detectCollision() {
		int snake_size = snake.getSnakeSize();
		Point snake_head = snake.getSnakeHead();
		int head_x = (int) snake_head.getX();
		int head_y = (int) snake_head.getY();
		if(head_x > GameFrame.FRAME_WIDTH / CrazySnake.SNAKE_SCALE - 1 || head_x < 0 || head_y > GameFrame.FRAME_HEIGHT / CrazySnake.SNAKE_SCALE - 1 || head_y < 0) {
			return 0;
		}
		for(int i = 1; i < snake_size; i++) {
			if(snake_head.equals(snake.getSnakePiece(i))) {
				dead = true;
				return i;
			}
		}
		return -1;
	}
	
	public void moveSnake() {
		snake.moveSnake(next_dir);
		current_dir = next_dir;
	}
	
	public int detectFood() {
		Point snake_head = snake.getSnakeHead();
		for(int i = 0; i < food_list.size(); i++) {
			if(snake_head.equals(food_list.get(i).getLocation())) {
				if(screen_color != food_list.get(i).getFoodColor()) {
					dead = true;
					return -1;
				}
				return i;
			}
		}
		return -1;
	}
	
	public void run() {
		while(!esc_clicked) {
			repaint();
			while(!dead && game_start && !esc_clicked) {
				oneGameTick();
				repaint();
				try {
					Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(score > highscore) {
				highscore = score;
				try {
					saveHighScore();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		reset();
	}
	
	public void reset() {
		snake = new ColoredSnake();
		current_dir = UP_DIR;
		next_dir = UP_DIR;
		screen_color = -1;
		
		food_list = new ArrayList<Food>();
		for(int i = 0; i < 5; i++) {
			addFood();
		}
		
		score = 0;
		dead = false;
		game_start = false;
		sleep_time = 100;
		
		color_eaten = -1;
		color_streak = 0;
	}
	
	public void oneGameTick() {
		moveSnake();
		int isCollision = detectCollision();
		if(isCollision != -1) {
			dead = true;
		}
		else {
			int isFood = detectFood();
			if(isFood > -1) {
				Food eaten_food = food_list.get(isFood);
				if(eaten_food.getFoodColor() == color_eaten) {
					color_streak++;
					score += color_streak;
					snake.growSnake(color_eaten);
					sleep_time--;
				}
				else {
					color_streak = 1;
					score++;
					color_eaten = eaten_food.getFoodColor();
					snake.growSnake(color_eaten);
				}
				food_list.remove(isFood);
			}
			if((int)(Math.random() * FOOD_SPAWNRATE) == 0) {
				addFood();
			}
		}
	}
	
	public void printSnake() {
		for(int i = 0; i < snake.getSnakeSize(); i++) {
			System.out.print("X: " + snake.getSnakePiece(i).getX() + " Y: " + snake.getSnakePiece(i).getY() + "\t");
		}
		System.out.println();
	}
	
	public static boolean getEscClicked() {
		return esc_clicked;
	}
	
	public static void setEscClicked(boolean bool) {
		esc_clicked = bool;
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			esc_clicked = true;
		}
		game_start = true;
		if(key.getKeyCode() == up_key && current_dir != DOWN_DIR) {
			next_dir = UP_DIR;
		}
		else if(key.getKeyCode() == right_key && current_dir != LEFT_DIR) {
			next_dir = RIGHT_DIR;
		}
		else if(key.getKeyCode() == down_key && current_dir != UP_DIR) {
			next_dir = DOWN_DIR;
		}
		else if(key.getKeyCode() == left_key && current_dir != RIGHT_DIR) {
			next_dir = LEFT_DIR;
		}
		else if(key.getKeyCode() == change_color_1_key) {
			screen_color = ColoredSnake.COLOR_1;
		}
		else if(key.getKeyCode() == change_color_2_key) {
			screen_color = ColoredSnake.COLOR_2;
		}
		else if(key.getKeyCode() == change_color_3_key) {
			screen_color = ColoredSnake.COLOR_3;
		}
		else if(key.getKeyCode() == reset_key) {
			reset();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
