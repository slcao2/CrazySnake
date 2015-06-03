import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

@SuppressWarnings("serial")
public class ColorPickerButton extends JButton implements ActionListener {
	
	private static int global_id =  0;
	
	private Color chosen_color;
	private String action_command;
	private int id;
	
	private int width;
	private int height;
	private int xpos;
	private int ypos;
	
	public ColorPickerButton(Color color, int x, int y) {
		chosen_color = color;
		action_command = "pick_color";
		id = global_id;
		global_id++;
		
		width = 60;
		height = 60;
		xpos = x;
		ypos = y;
		
		setActionCommand(action_command);
		addActionListener(this);
		
		setIcon(new ColorIcon(chosen_color, width, height));
		setPreferredSize(new Dimension(width, height));
		setBounds(xpos, ypos, width, height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == action_command) {
			Color picked_color = JColorChooser.showDialog(this, "Choose a color", chosen_color);
			if(picked_color != null) {
				Color old_color = chosen_color;
				chosen_color = picked_color;
				setIcon(new ColorIcon(chosen_color, width, height));
				firePropertyChange("color", old_color, chosen_color);
			}
		}
	}
	
	public int getId() {
		return id;
	}
	
	public int getXPos() {
		return xpos;
	}
	
	public int getYPos() {
		return ypos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return chosen_color;
	}
	
	public void setColor(Color c) {
		chosen_color = c;
	}
}
