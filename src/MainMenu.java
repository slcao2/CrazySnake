import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainMenu extends JPanel implements KeyListener {
	private MenuButton play;
	private MenuButton options;
	private MenuButton exit;
	private MenuButton about;
	
	private Color color_1;
	private Color color_2;
	private Color color_3;
	
	public MainMenu() {
		play = new MenuButton("Play");
		options = new MenuButton("Options");
		exit = new MenuButton("Exit");
		about = new MenuButton("About");
		
		color_1 = Color.RED.darker();
		color_2 = Color.GREEN.darker();
		color_3 = Color.BLUE.darker();
		
		add(play);
		add(options);
		add(exit);
		add(about);
		
		addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//Setup Buttons
		play.setButtonWidth(g2D.getFontMetrics(play.getFont()).stringWidth(play.getText()));
		play.setButtonHeight(g2D.getFontMetrics(play.getFont()).getHeight());
		play.setXPosition(GameFrame.FRAME_WIDTH / 2 - play.getButtonWidth() / 2);
		play.setYPosition(350);
		
		options.setButtonWidth(g2D.getFontMetrics(options.getFont()).stringWidth(options.getText()));
		options.setButtonHeight(g2D.getFontMetrics(options.getFont()).getHeight());
		options.setXPosition(GameFrame.FRAME_WIDTH / 2 - options.getButtonWidth() / 2);
		options.setYPosition(450);
		
		exit.setButtonWidth(g2D.getFontMetrics(exit.getFont()).stringWidth(exit.getText()));
		exit.setButtonHeight(g2D.getFontMetrics(exit.getFont()).getHeight());
		exit.setXPosition(GameFrame.FRAME_WIDTH / 2 - exit.getButtonWidth() / 2);
		exit.setYPosition(650);
		
		about.setButtonWidth(g2D.getFontMetrics(about.getFont()).stringWidth(about.getText()));
		about.setButtonHeight(g2D.getFontMetrics(about.getFont()).getHeight());
		about.setXPosition(GameFrame.FRAME_WIDTH / 2 - about.getButtonWidth() / 2);
		about.setYPosition(550);
		
		//Draw Background
		g2D.setColor(Color.WHITE.darker());
		g2D.fillRect(0, 0, GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
		
		//Draw Title
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 80);
		g2D.setFont(titleFont);
		FontMetrics font_metric = g2D.getFontMetrics();
		int title_width = font_metric.stringWidth("Crazy Snake");
		switch((int)(Math.random() * 3)) {
		case 0:
			g2D.setColor(color_1);
			break;
		case 1:
			g2D.setColor(color_2);
			break;
		case 2:
			g2D.setColor(color_3);
			break;
		}
		g2D.drawString("Crazy ", GameFrame.FRAME_WIDTH / 2 - title_width / 2, 200);
		switch((int)(Math.random() * 3)) {
		case 0:
			g2D.setColor(color_1);
			break;
		case 1:
			g2D.setColor(color_2);
			break;
		case 2:
			g2D.setColor(color_3);
			break;
		}
		g2D.drawString("Snake", GameFrame.FRAME_WIDTH / 2, 200);
		
		//Draw Buttons
		play.paintComponent(g2D);
		options.paintComponent(g2D);
		exit.paintComponent(g2D);
		about.paintComponent(g2D);
	}
	
	public void readColorsFromOptions() throws IOException {
		FileReader optionsReader = new FileReader("options.txt");
		String readString = "";
		for(int i = 0; i < 3; i++) {
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
			}
			readString = "";
		}
		optionsReader.close();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		else if(key.getKeyCode() == KeyEvent.VK_SPACE) {
			MenuButton.setPlayClicked(true);
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
}
