package marnie.main;

import javax.swing.JFrame;

import marnie.graphics.MainPanel;

public class Window extends JFrame{
	
	public Window() {
		setTitle("Loop v1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MainPanel(1152, 648));
		setSize(1152, 648);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}