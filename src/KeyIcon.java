import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class KeyIcon extends ImageIcon {
	
	private String key;
	private int width;
	private int height;
	private int font_size;
	
	public KeyIcon(String key, int font_size, int width, int height) {
		this.key = key;
		this.width = width;
		this.height = height;
		this.font_size = font_size;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		super.paintIcon(c, g, x, y);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, font_size));
		FontMetrics key_metrics = g.getFontMetrics();
		g.setColor(Color.BLACK);
		g.drawString(key, width / 2 - key_metrics.stringWidth(key) / 2, height / 2 + 7 * key_metrics.getHeight() / 24);
	}
}
