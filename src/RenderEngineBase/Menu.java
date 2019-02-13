package RenderEngineBase;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Menu extends JFrame{
	private JLabel lblMessage;
	public JButton btnOK, btnCancel;
	
	public Menu(String title, String message) {
		setTitle(title);
		lblMessage = new JLabel(message);
		
		btnOK = new JButton("Play");
		btnCancel = new JButton("Quit");
		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnOK);
		pnlButtons.add(btnCancel);
		add(lblMessage, BorderLayout.CENTER);
		add(pnlButtons, BorderLayout.SOUTH);
		setAlwaysOnTop(true);
	}
	
	public void Show() {
		pack();
		setVisible(true);
	}
	
	public void Close() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
