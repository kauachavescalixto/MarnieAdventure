package marnie.graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel{

	int w,h;
	
	private CinematicPanel cp;
	private GamePanel gp;
	
	private Timer t;
	private int timerCount = 0;
	private int y = 648;
	
	public MainPanel(int w, int h) {
		this.w = w;
		this.h = h;
		setPreferredSize(new Dimension(w,h));
		
		init();
		def_eventos();
	}
	
	public void init() {
		
		
		//id do usuário
		
		cp = new CinematicPanel(w,h);
		//cp.setSize(w,h);
		gp = new GamePanel(w,h,3);
		//gp.setSize(w,h);
		
		//add(cp);
		add(gp);
		
	}
	
	public void def_eventos() {
		
		cp.play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cp.animation_transition();
				animation_transition();
				repaint();
			}
		});
		
	
		
	}
	public void animation_transition() {
		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerCount++;
					y -= 2;
					System.out.println(""+y);
					gp.setLocation(0, y);
					
					if (y == 0) {
						removeAll();
						add(gp);
						t.stop();
						repaint();
					}

				

			}
		});

		t.start();
	}
	
}
