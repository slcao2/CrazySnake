import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;


public class MenuButton extends JComponent implements MouseListener {

	private static boolean play_clicked = false;
	private static boolean options_clicked = false;
	
	private int xpos;
	private int ypos;
	private String text;
	
	private int button_width;
	private int button_height;
	private Color color;
	private Font font;
	
	public MenuButton(String text) {
		this(text, 0, 0);
	}
	
	public MenuButton(String text, int xpos, int ypos) {
		super();
		
		this.xpos = xpos;
		this.ypos = ypos;
		this.text = text;
		
		button_width = 0;
		button_height = 0;
		color = Color.BLACK;
		font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		
		addMouseListener(this);
	}
	
	public String getText() {
		return text;
	}
	
	public Font getFont() {
		return font;
	}
	
	public void setButtonWidth(int button_width) {
		this.button_width = button_width;
	}
	
	public void setButtonHeight(int button_height) {
		this.button_height = button_height;
	}

	public int getButtonWidth() {
		return button_width;
	}
	
	public void setXPosition(int x) {
		xpos = x;
	}
	
	public void setYPosition(int y) {
		ypos = y;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(button_width, button_height);
	}
	
	@Override 
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(font);
		FontMetrics font_metrics = g.getFontMetrics();
		button_width = font_metrics.stringWidth(text);
		button_height = font_metrics.getHeight();
		g.setColor(color);
		
		setBounds(xpos, ypos - button_height + button_height / 4, button_width, button_height);
		g.drawString(text, xpos, ypos);
	}
	
	public static boolean getPlayClicked() {
		return play_clicked;
	}
	
	public static void setPlayClicked(boolean bool) {
		play_clicked = bool;
	}
	
	public static boolean getOptionsClicked() {
		return options_clicked;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		// TODO Auto-generated method stub
		color = Color.DARK_GRAY;
		font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
		// TODO Auto-generated method stub
		color = Color.BLACK;
		font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		// TODO Auto-generated method stub
		if(text.equals("Exit")) {
			System.exit(0);
		}
		else if(text.equals("Play")) {
			play_clicked = true;
		}
		else if(text.equals("Options")) {
			options_clicked = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}