import java.awt.CardLayout;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 800;
	
	private int window_width;
	private int window_height;
	
	private CrazySnake crazy_snake_panel;
	private MainMenu main_menu_panel;
	private OptionsMenu options_menu_panel;
	
	public GameFrame() {
		window_width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		window_height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		this.setTitle("CrazySnake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(FRAME_WIDTH + 6, FRAME_HEIGHT + 28);
		this.setLocation(window_width/2 - FRAME_WIDTH/2, window_height/2 - FRAME_HEIGHT/2 - 30);
		this.setResizable(false);
		
		main_menu_panel = new MainMenu();
		crazy_snake_panel = new CrazySnake();
		options_menu_panel = new OptionsMenu();
	}
	
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		
		boolean read_from_file = false;
		
		try {
			frame.options_menu_panel.readFromFile();
			frame.crazy_snake_panel.readFromOptions(frame.options_menu_panel);
			frame.main_menu_panel.readColorsFromOptions();
		} catch (FileNotFoundException e) {
			System.out.println("options.txt not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel(new CardLayout());
		panel.add(frame.main_menu_panel);
		panel.add(frame.crazy_snake_panel);
		panel.add(frame.options_menu_panel);
		frame.add(panel);
		
		CardLayout card_flipper = (CardLayout) panel.getLayout();
		frame.main_menu_panel.requestFocus();
		card_flipper.first(panel);
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(MenuButton.getPlayClicked()) {
				if(!CrazySnake.getEscClicked()) {
					frame.crazy_snake_panel.readFromOptions(frame.options_menu_panel);
					try {
						frame.crazy_snake_panel.setHighscore(frame.crazy_snake_panel.getHighScore());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					card_flipper.next(panel);
					frame.crazy_snake_panel.requestFocus();
					frame.crazy_snake_panel.run();
				}
				else {
					card_flipper.first(panel);
					MenuButton.setPlayClicked(false);
					CrazySnake.setEscClicked(false);
					frame.main_menu_panel.requestFocus();
					frame.repaint();
				}
			}
			else if(MenuButton.getOptionsClicked()) {
				if(!OptionsMenu.getEscClicked()) {
					if(!read_from_file) {
						try {
							frame.options_menu_panel.readFromFile();
							read_from_file = true;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					card_flipper.last(panel);
					frame.options_menu_panel.requestFocus();
				}
				else {
					read_from_file = false;
					try {
						frame.main_menu_panel.readColorsFromOptions();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					card_flipper.first(panel);
					MenuButton.setOptionsClicked(false);
					OptionsMenu.setEscClicked(false);
					frame.main_menu_panel.requestFocus();
					frame.repaint();
				}
			}
		}
		
	}
}
