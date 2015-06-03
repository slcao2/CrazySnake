import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JPanel;

/*	Options for the options menu
 * 
 * 	Change 3 colors
 * 	Change controls
 *  Reset highscore
 */
public class OptionsMenu extends JPanel {

	public static final Color DEFAULT_COLOR_1 = Color.RED.darker();
	public static final Color DEFAULT_COLOR_2 = Color.GREEN.darker();
	public static final Color DEFAULT_COLOR_3 = Color.BLUE.darker();
	
	public static final int DEFAULT_UP_KEY = KeyEvent.VK_W;
	public static final int DEFAULT_LEFT_KEY = KeyEvent.VK_A;
	public static final int DEFAULT_DOWN_KEY = KeyEvent.VK_S;
	public static final int DEFAULT_RIGHT_KEY = KeyEvent.VK_D;
	
	public static final int DEFAULT_CHANGE_COLOR_1_KEY = KeyEvent.VK_J;
	public static final int DEFAULT_CHANGE_COLOR_2_KEY = KeyEvent.VK_K;
	public static final int DEFAULT_CHANGE_COLOR_3_KEY = KeyEvent.VK_L;
	
	public static final int DEFAULT_RESET_KEY = KeyEvent.VK_SPACE;
	
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
	
	private ColorPickerButton color_1_button;
	private ColorPickerButton color_2_button;
	private ColorPickerButton color_3_button;
	
	
	
	public OptionsMenu() {
		restoreDefault();
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}
	
	public void restoreDefault() {
		color_1 = DEFAULT_COLOR_1;
		color_2 = DEFAULT_COLOR_2;
		color_3 = DEFAULT_COLOR_3;
		
		up_key = DEFAULT_UP_KEY;
		left_key = DEFAULT_LEFT_KEY;
		down_key = DEFAULT_DOWN_KEY;
		right_key = DEFAULT_RIGHT_KEY;
		
		change_color_1_key = DEFAULT_CHANGE_COLOR_1_KEY;
		change_color_2_key = DEFAULT_CHANGE_COLOR_2_KEY;
		change_color_3_key = DEFAULT_CHANGE_COLOR_3_KEY;
		
		reset_key = KeyEvent.VK_SPACE;
	}
	
	public void readFromFile() throws IOException {
		FileReader optionsReader = new FileReader("options.txt");
		String readString = "";
		for(int i = 0; i < 11; i++) {
			char readChar = 0;
			while((readChar = (char) optionsReader.read()) != -1 && readChar != '\n') {
				if((readChar >= '0' && readChar <= '9') || readChar == '-') {
					readString = readString + readChar;
				}
			}
			switch(i) {
			case 0:
				color_1 = Color.decode(readString);
				break;
			case 1:
				color_2 = Color.decode(readString);
				break;
			case 2:
				color_3 = Color.decode(readString);
				break;
			case 3:
				up_key = Integer.parseInt(readString);
				break;
			case 4:
				left_key = Integer.parseInt(readString);
				break;
			case 5:
				down_key = Integer.parseInt(readString);
				break;
			case 6:
				right_key = Integer.parseInt(readString);
				break;
			case 7:
				change_color_1_key = Integer.parseInt(readString);
				break;
			case 8:
				change_color_2_key = Integer.parseInt(readString);
				break;
			case 9:
				change_color_3_key = Integer.parseInt(readString);
				break;
			case 10:
				reset_key = Integer.parseInt(readString);
				break;
			}
			readString = "";
		}
		optionsReader.close();
	}
	
	public void writeToFile() {
		try {
			PrintWriter writer = new PrintWriter("options.txt","UTF-8");
			
			writer.println(color_1.getRGB());
			writer.println(color_2.getRGB());
			writer.println(color_3.getRGB());
			
			writer.println(up_key);
			writer.println(left_key);
			writer.println(down_key);
			writer.println(right_key);
			
			writer.println(change_color_1_key);
			writer.println(change_color_2_key);
			writer.println(change_color_3_key);
			
			writer.println(reset_key);
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Color getColorOne() {
		return color_1;
	}
	
	public Color getColorTwo() {
		return color_2;
	}
	
	public Color getColorThree() {
		return color_3;
	}
	
	public int getUpKey() {
		return up_key;
	}
	
	public int getLeftKey() {
		return left_key;
	}
	
	public int getDownKey() {
		return down_key;
	}
	
	public int getRightKey() {
		return right_key;
	}
	
	public int getChangeColorOneKey() {
		return change_color_1_key;
	}
	
	public int getChangeColorTwoKey() {
		return change_color_2_key;
	}
	
	public int getChangeColorThreeKey() {
		return change_color_3_key;
	}
	
	public int getResetKey() {
		return reset_key;
	}
}
