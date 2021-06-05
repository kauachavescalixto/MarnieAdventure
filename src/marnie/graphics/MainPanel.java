package marnie.graphics;

import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel extends JPanel{

	int w,h;
	
	private CinematicPanel cp;
	private GamePanel gp;
	
	public MainPanel(int w, int h) {
		this.w = w;
		this.h = h;
		setPreferredSize(new Dimension(w,h));
		
		init();
		
	}
	
	public void init() {
		gp = new GamePanel(w,h);
		
		add(gp);
		
	}
	
}
