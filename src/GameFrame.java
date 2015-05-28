import java.awt.CardLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 800;
	
	private int window_width;
	private int window_height;
	
	private CrazySnake crazy_snake_panel;
	private MainMenu main_menu_panel;
	
	public GameFrame() {
		window_width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		window_height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		//JFrame frame = new JFrame("Crazy Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(FRAME_WIDTH + 6, FRAME_HEIGHT + 28);
		this.setLocation(window_width/2 - FRAME_WIDTH/2, window_height/2 - FRAME_HEIGHT/2 - 30);
		this.setResizable(false);
		
		main_menu_panel = new MainMenu();
		crazy_snake_panel = new CrazySnake();
	}
	
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		JPanel panel = new JPanel(new CardLayout());
		panel.add(frame.main_menu_panel);
		panel.add(frame.crazy_snake_panel);
		frame.add(panel);
		
		CardLayout card_flipper = (CardLayout) panel.getLayout();
		frame.main_menu_panel.requestFocus();
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(MenuButton.getPlayClicked()) {
				if(!CrazySnake.getEscClicked()) {
					card_flipper.next(panel);
					frame.crazy_snake_panel.requestFocus();
					frame.crazy_snake_panel.run();
				}
				else {
					card_flipper.previous(panel);
					MenuButton.setPlayClicked(false);
					CrazySnake.setEscClicked(false);
					frame.main_menu_panel.requestFocus();
					frame.repaint();
				}
			}
			else if(MenuButton.getOptionsClicked()) {
				
			}
		}
		
	}
}
