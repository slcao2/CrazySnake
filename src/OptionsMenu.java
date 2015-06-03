import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionsMenu extends JPanel implements KeyListener, PropertyChangeListener {

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
	
	public static final int TEXT_BUTTON_NUM = 7;
	public static boolean esc_clicked = false;
	
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
	
	private int last_clicked_id;
	private boolean clicked_active;
	
	private ColorPickerButton color_1_button;
	private ColorPickerButton color_2_button;
	private ColorPickerButton color_3_button;
	
	private KeyPickerButton up_button;
	private KeyPickerButton left_button;
	private KeyPickerButton down_button;
	private KeyPickerButton right_button;
	
	private KeyPickerButton change_color_1_button;
	private KeyPickerButton change_color_2_button;
	private KeyPickerButton change_color_3_button;
	
	private MenuButton ok;
	private MenuButton cancel;
	private MenuButton reset;
	private MenuButton restore;
	
	public OptionsMenu() {
		restoreDefault();
		
		color_1_button = new ColorPickerButton(color_1, 110, 460);
		color_2_button = new ColorPickerButton(color_2, 190, 460);
		color_3_button = new ColorPickerButton(color_3, 270, 460);
		
		up_button = new KeyPickerButton(((char)up_key) + "", 370, 210);
		left_button = new KeyPickerButton(((char)left_key) + "", 300, 280);
		down_button = new KeyPickerButton(((char)down_key) + "", 370, 280);
		right_button = new KeyPickerButton(((char)right_key) + "", 440, 280);
		
		change_color_1_button = new KeyPickerButton(((char)change_color_1_key) + "", 480, 460);
		change_color_2_button = new KeyPickerButton(((char)change_color_2_key) + "", 550, 460);
		change_color_3_button = new KeyPickerButton(((char)change_color_3_key) + "", 620, 460);
		
		last_clicked_id = -1;
		clicked_active = false;
		
		color_1_button.addPropertyChangeListener(this);
		color_2_button.addPropertyChangeListener(this);
		color_3_button.addPropertyChangeListener(this);
		
		up_button.addPropertyChangeListener(this);
		left_button.addPropertyChangeListener(this);
		down_button.addPropertyChangeListener(this);
		right_button.addPropertyChangeListener(this);
		
		change_color_1_button.addPropertyChangeListener(this);
		change_color_2_button.addPropertyChangeListener(this);
		change_color_3_button.addPropertyChangeListener(this);
		
		ok = new MenuButton("OK", 540, 660, 30);
		cancel = new MenuButton("Cancel", 520, 720, 30);
		reset = new MenuButton("Reset Highscore", 105, 660, 30);
		restore = new MenuButton("Restore Default", 110, 720, 30);
		
		ok.addPropertyChangeListener(this);
		cancel.addPropertyChangeListener(this);
		reset.addPropertyChangeListener(this);
		restore.addPropertyChangeListener(this);
		
		add(color_1_button);
		add(color_2_button);
		add(color_3_button);
		
		add(up_button);
		add(left_button);
		add(down_button);
		add(right_button);
		
		add(change_color_1_button);
		add(change_color_2_button);
		add(change_color_3_button);
		
		add(ok);
		add(cancel);
		add(reset);
		add(restore);
		
		setLayout(null);
		addPropertyChangeListener(this);
		addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Paint Background
		g.setColor(Color.WHITE.darker());
		g.fillRect(0, 0, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		
		//Paint Titles
		Font options_font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setColor(Color.BLACK);
		g.setFont(options_font);
		g.drawString("Options", 100, 100);
		
		Color explain_color = Color.WHITE;
		Font button_explain_font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g.setFont(button_explain_font);
		g.setColor(explain_color);
		FontMetrics explain_metrics = g.getFontMetrics();
		
		g.drawString("Set Movement Keys", GameFrame.FRAME_WIDTH / 2 - explain_metrics.stringWidth("Set Movement Keys") / 2, 200);
		g.drawString("Set Snake Color", GameFrame.FRAME_WIDTH / 4 - explain_metrics.stringWidth("Set Snake Color") / 3, 450);
		g.drawString("Set Screen Keys", 3 * GameFrame.FRAME_WIDTH / 4 - 2 * explain_metrics.stringWidth("Set Screen Keys") / 3 + 10, 450);
		
		//Paint Bottom Buttons
		ok.paintComponent(g);
		cancel.paintComponent(g);
		reset.paintComponent(g);
		restore.paintComponent(g);
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
	
	public void manageButtons() {
		color_1_button.setIcon(new ColorIcon(color_1, 60, 60));
		color_1_button.setColor(color_1);
		
		color_2_button.setIcon(new ColorIcon(color_2, 60, 60));
		color_2_button.setColor(color_2);
		
		color_3_button.setIcon(new ColorIcon(color_3, 60, 60));
		color_3_button.setColor(color_3);
		
		up_button.setKeyText(((char)up_key) + "");
		left_button.setKeyText(((char)left_key) + "");
		down_button.setKeyText(((char)down_key) + "");
		right_button.setKeyText(((char)right_key) + "");
		
		change_color_1_button.setKeyText(((char)change_color_1_key) + "");
		change_color_2_button.setKeyText(((char)change_color_2_key) + "");
		change_color_3_button.setKeyText(((char)change_color_3_key) + "");
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
				color_1_button.setIcon(new ColorIcon(color_1, 60, 60));
				color_1_button.setColor(color_1);
				break;
			case 1:
				color_2 = Color.decode(readString);
				color_2_button.setIcon(new ColorIcon(color_2, 60, 60));
				color_2_button.setColor(color_2);
				break;
			case 2:
				color_3 = Color.decode(readString);
				color_3_button.setIcon(new ColorIcon(color_3, 60, 60));
				color_3_button.setColor(color_3);
				break;
			case 3:
				up_key = Integer.parseInt(readString);
				up_button.setKeyText(((char)up_key) + "");
				break;
			case 4:
				left_key = Integer.parseInt(readString);
				left_button.setKeyText(((char)left_key) + "");
				break;
			case 5:
				down_key = Integer.parseInt(readString);
				down_button.setKeyText(((char)down_key) + "");
				break;
			case 6:
				right_key = Integer.parseInt(readString);
				right_button.setKeyText(((char)right_key) + "");
				break;
			case 7:
				change_color_1_key = Integer.parseInt(readString);
				change_color_1_button.setKeyText(((char)change_color_1_key) + "");
				break;
			case 8:
				change_color_2_key = Integer.parseInt(readString);
				change_color_2_button.setKeyText(((char)change_color_2_key) + "");
				break;
			case 9:
				change_color_3_key = Integer.parseInt(readString);
				change_color_3_button.setKeyText(((char)change_color_3_key) + "");
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
	
	public void setClickedKeys(int id) {
		if(last_clicked_id != id && last_clicked_id != -1) {
			switch(last_clicked_id) {
			case 0:
				up_button.resetKeyText();
				up_button.setClicked(false);
				break;
			case 1:
				left_button.resetKeyText();
				left_button.setClicked(false);
				break;
			case 2:
				down_button.resetKeyText();
				down_button.setClicked(false);
				break;
			case 3:
				right_button.resetKeyText();
				right_button.setClicked(false);
				break;
			case 4:
				change_color_1_button.resetKeyText();
				change_color_1_button.setClicked(false);
				break;
			case 5:
				change_color_2_button.resetKeyText();
				change_color_2_button.setClicked(false);
				break;
			case 6:
				change_color_3_button.resetKeyText();
				change_color_3_button.setClicked(false);
				break;
			}
		}
		last_clicked_id = id;
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
	
	public static boolean getEscClicked() {
		return esc_clicked;
	}
	
	public static void setEscClicked(boolean b) {
		esc_clicked = b;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			esc_clicked = true;
		}
		else if(clicked_active) {
			String key_code = ((char) key.getKeyCode()) + "";
			switch(last_clicked_id) {
			case 0:
				up_button.setKeyText(key_code);
				up_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			case 1:
				left_button.setKeyText(key_code);
				left_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			case 2:
				down_button.setKeyText(key_code);
				down_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			case 3:
				right_button.setKeyText(key_code);
				right_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			case 4:
				change_color_1_button.setKeyText(key_code);
				change_color_1_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			case 5:
				change_color_2_button.setKeyText(key_code);
				change_color_2_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			case 6:
				change_color_3_button.setKeyText(key_code);
				change_color_3_button.notifyPropertyListener(key_code);
				clicked_active = false;
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent change) {
		// TODO Auto-generated method stub
		if(change.getPropertyName().equals("color")) {
			int id = ((ColorPickerButton) change.getSource()).getId();
			switch(id) {
			case 0:
				color_1 = color_1_button.getColor();
				break;
			case 1:
				color_2 = color_2_button.getColor();
				break;
			case 2:
				color_3 = color_3_button.getColor();
				break;
			}
		}
		else if(change.getPropertyName().equals("clicked")) {
			int id = ((KeyPickerButton) change.getSource()).getId();
			setClickedKeys(id);
			if(((boolean) change.getNewValue()) == true) {
				clicked_active = true;
			}
			else {
				clicked_active = false;
			}
		}
		else if(change.getPropertyName().equals("key")) {
			int id = ((KeyPickerButton) change.getSource()).getId();
			switch(id) {
			case 0:
				up_key = up_button.getKey();
				break;
			case 1:
				left_key = left_button.getKey();
				break;
			case 2:
				down_key = down_button.getKey();
				break;
			case 3:
				right_key = right_button.getKey();
				break;
			case 4:
				change_color_1_key = change_color_1_button.getKey();
				break;
			case 5:
				change_color_2_key = change_color_2_button.getKey();
				break;
			case 6:
				change_color_3_key = change_color_3_button.getKey();
				break;
			}
		}
		else if(change.getPropertyName().equals("ok")) {
			writeToFile();
			esc_clicked = true;
		}
		else if(change.getPropertyName().equals("cancel")) {
			esc_clicked = true;
		}
		else if(change.getPropertyName().equals("reset")) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("highscore.txt","UTF-8");
				writer.println(0);
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(change.getPropertyName().equals("restore")) {
			restoreDefault();
			manageButtons();
		}
	}
}
