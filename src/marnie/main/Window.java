package marnie.main;

import javax.swing.JFrame;

import marnie.graphics.MainPanel;

public class Window extends JFrame{
	
	private MainPanel mp;
	
	public Window() {
		setTitle("Marnie Adventure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		mp = new MainPanel(1152, 648);
		mp.setLocation(0,0);
		setContentPane(mp);
		setSize(1152 + 30, 648 + 50);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}