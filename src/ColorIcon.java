import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class ColorIcon extends ImageIcon {

	private Color color;
	private int width;
	private int height;
	
	public ColorIcon(Color color, int width, int height) {
		this.color = color;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		super.paintIcon(c, g, x, y);
		
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}
}
