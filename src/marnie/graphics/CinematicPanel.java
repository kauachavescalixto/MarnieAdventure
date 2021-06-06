package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class CinematicPanel extends JPanel {

	int w, h;

	public JButton play;

	private Timer t;

	private JLabel BG = new JLabel(new ImageIcon(getClass().getResource("StartImage.png")));
	private int x, y, timerCount;
	
	public JTextField tfNome;

	private JLabel messageDialog = new JLabel(new ImageIcon(getClass().getResource("MessageDialog.png")));
	
	public CinematicPanel(int w, int h) {

		this.w = w;
		this.h = h;
		setLayout(null);
		setPreferredSize(new Dimension(w, h));

		init();
	}

	public void init() {
		
		tfNome = new JTextField();
		
		
		play = new JButton();

		BG.setSize(1152, 1154);
		animationBG();
		play.setSize(200, 110);
		play.setLocation((w / 2) - (play.getWidth() / 2), h - 180);
		play.setContentAreaFilled(false);
	}

	public void animationBG() {
		add(BG);

		x = 0;
		y = 0;
		timerCount = 0;

		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerCount++;
				if (timerCount >= 100) {
					y -= 1;
					BG.setLocation(x, y);
					repaint();
					if (y == -500) {
						t.stop();
						add(play);
						repaint();
					}

				}

			}
		});

		t.start();
	}

	public void animation_transition() {

		play.setVisible(false);

		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerCount++;
				y -= 2;
				BG.setLocation(x, y);
				repaint();
				if (y == -1500) {
					t.stop();
					add(play);
					repaint();
				}

			}
		});

		t.start();
	}

}
