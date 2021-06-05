package marnie.main;

import javax.swing.JFrame;

import marnie.graphics.MainPanel;

public class Window extends JFrame{
	
	public Window() {
		setTitle("Marnie Adventure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setContentPane(new MainPanel(1152, 648));
		//setSize(1152, 648);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}