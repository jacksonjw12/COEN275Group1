package RenderEngineBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends Menu {
	
	public StartMenu(String title, String message) {
		super(title, message);
		super.btnOK.setText("Start");
		super.btnCancel.setText("Quit game");
		
		super.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Close();
				System.exit(1);
			}
		});
		
		Show();
	}

}
