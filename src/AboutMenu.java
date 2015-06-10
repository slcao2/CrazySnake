import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AboutMenu extends JPanel implements KeyListener {

	private static boolean esc_clicked = false;
	
	private MenuButton back;
	
	public AboutMenu() {
		back = new MenuButton("Back");
		
		add(back);
		
		addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//Background
		g2D.setColor(Color.WHITE.darker());
		g2D.fillRect(0, 0, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		
		//Title
		Font about_font = new Font(Font.SANS_SERIF, Font.BOLD, 60);
		g2D.setColor(Color.BLACK);
		g2D.setFont(about_font);
		g2D.drawString("About", 100, 100);
		
		//Instructions
		about_font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g2D.setColor(new Color(40, 40, 40));
		g2D.setFont(about_font);
		g2D.drawString("Instructions", 150, 175);
		
		about_font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g2D.setColor(new Color(60, 60, 60));
		g2D.setFont(about_font);
		g2D.drawString("A colorful variant of the classic game Snake. ", 185, 205);
		g2D.drawString("Try and grow your colored snake by eating colored ", 160, 230);
		g2D.drawString("food. ", 160, 255);
		g2D.drawString("Eating the same color food gives you a score bonus ", 185, 280);
		g2D.drawString("based on how many you have eaten in a row. Make sure ", 160, 305);
		g2D.drawString("your screen color is the same as the food color or ", 160, 330);
		g2D.drawString("you might just die. In fact, yeah, you die.", 160, 355);
		g2D.drawString("The other rules are the same as Snake. If you run ", 185, 380);
		g2D.drawString("into the wall or into your own tail, RIP Snake.", 160, 405);

		//Shortcuts
		about_font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g2D.setColor(new Color(40, 40, 40));
		g2D.setFont(about_font);
		g2D.drawString("Shortcuts", 150, 505);
		
		about_font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g2D.setColor(new Color(60, 60, 60));
		g2D.setFont(about_font);
		g2D.drawString("Press ESC at any menu to go back.", 160, 535);
		g2D.drawString("Press SPACE at the main menu to play.", 160, 560);
		g2D.drawString("Press SPACE while playing to restart.", 160, 585);
		
		//Back Button
		back.setButtonWidth(g2D.getFontMetrics(back.getFont()).stringWidth(back.getText()));
		back.setButtonHeight(g2D.getFontMetrics(back.getFont()).getHeight());
		back.setXPosition(GameFrame.FRAME_WIDTH / 2 - back.getButtonWidth() / 2);
		back.setYPosition(720);
		
		back.paintComponent(g2D);
	}
	
	public static void setEscClicked(boolean b) {
		esc_clicked = b;
	}
	
	public static boolean getEscClicked() {
		return esc_clicked;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			esc_clicked = true;
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
