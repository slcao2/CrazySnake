import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


@SuppressWarnings("serial")
public class KeyPickerButton extends JButton implements ActionListener {
	
	private static int global_id = 0;
	
	private String chosen_key;
	private String action_command;
	private boolean clicked;
	private int id;
	
	private int font_size;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	
	public KeyPickerButton(String key, int x, int y) {
		chosen_key = key;
		action_command = "pick_key";
		clicked = false;
		id = global_id;
		global_id++;
		
		font_size = 40;
		xpos = x;
		ypos = y;
		width = 60;
		height = 60;
		
		setActionCommand(action_command);
		addActionListener(this);
		
		setIcon(new KeyIcon(chosen_key, font_size, width, height));
		setPreferredSize(new Dimension(width, height));
		setBounds(xpos, ypos, width, height);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == action_command && !clicked) {
			setIcon(new KeyIcon("...", font_size, width, height));
			clicked = true;
			firePropertyChange("clicked", false, true);
		}
		else {
			setIcon(new KeyIcon(chosen_key, font_size, width, height));
			clicked = false;
			firePropertyChange("clicked", true, false);
		}
	}
	
	public void notifyPropertyListener(String key_code) {
		firePropertyChange("key", "No Such Key", key_code);
	}

	public void resetKeyText() {
		setIcon(new KeyIcon(chosen_key, font_size, width, height));
	}

	public void setKeyText(String key) {
		chosen_key = key;
		setIcon(new KeyIcon(chosen_key, font_size, width, height));
		clicked = false;
	}
	
	public void setClicked(boolean c) {
		clicked = c;
	}
	
	public int getId() {
		return id;
	}
	
	public char getKey() {
		return chosen_key.charAt(0);
	}
}
