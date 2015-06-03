import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

public class ColorPickerButton extends JButton implements ActionListener {
	
	private Color chosen_color;
	private String action_command;
	private JColorChooser color_chooser;
	
	public ColorPickerButton() {
		this(Color.WHITE);
	}
	
	public ColorPickerButton(Color color) {
		chosen_color = color;
		action_command = "pick_color";
		color_chooser = new JColorChooser(chosen_color);
		
		setActionCommand(action_command);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == action_command) {
			color_chooser = new JColorChooser(chosen_color);
			//JDialog color_dialog = JColorChooser.createDialog(this, "Choose a color", true, color_chooser, this, this);
			Color picked_color = JColorChooser.showDialog(this, "Choose a color", chosen_color);
			if(picked_color != null) {
				chosen_color = picked_color;
			}
		}
	}
}
