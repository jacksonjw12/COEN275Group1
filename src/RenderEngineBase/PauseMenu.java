package RenderEngineBase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PauseMenu extends Menu {
	
	public PauseMenu(String title, String message, int points) {
		super(title, message);
		JLabel lblPoints = new JLabel("Points: " + String.valueOf(points));
		add(lblPoints, BorderLayout.NORTH);
		
		super.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Close();
			}
			});
		
		Show();
	}
	
	
	
	
	
}