package marnie.graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.input.KeyCode;

public class MainPanel extends JPanel {

	int w, h;

	private CinematicPanel cp;
	private GamePanel gp;
	public static PreGameConnection pgc;

	private Timer t;
	private int timerCount = 0;
	private int y = 648;

	private JLabel transitionBG = new JLabel(new ImageIcon(getClass().getResource("transitionBG.png")));

	public MainPanel(int w, int h) {
		this.w = w;
		this.h = h;
		setPreferredSize(new Dimension(w, h));

		init();
		def_eventos();
	}

	public void init() {

		transitionBG.setSize(w, h);

		cp = new CinematicPanel(w, h);
		
		pgc = new PreGameConnection();

		add(cp);

	}

	public void def_eventos() {

		KeyListener enter = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					cp.play.doClick();
				}

			}
		};
		
		cp.play.addKeyListener(enter);
		cp.tfNome.addKeyListener(enter);
		cp.yes.addKeyListener(enter);
		
		cp.play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (pgc.usersdao.localizar(cp.tfNome.getText())) {
					gp = new GamePanel(w, h, false);
					gp.setSize(w, h);
					add(gp);

					cp.tfNome.setVisible(false);

					animate();
					repaint();

				} else {
					cp.yes.setVisible(true);
					cp.no.setVisible(true);
					cp.messageDialog.setVisible(true);

				}

			}
		});

		cp.yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pgc.usersdao.users.setId(0);
				pgc.usersdao.users.setNome(cp.tfNome.getText());
				pgc.usersdao.users.setGalinha(1);
				pgc.usersdao.users.setPato(0);
				pgc.usersdao.users.setVaca(0);
				pgc.usersdao.users.setCabra(0);
				pgc.usersdao.users.setOvelha(0);
				pgc.usersdao.users.setDinheiro(10);
				pgc.usersdao.users.setLogin((int) (System.currentTimeMillis()));
				pgc.usersdao.atualizar(pgc.usersdao.INCLUSAO);
				gp = new GamePanel(w, h, true);
				animate();
			}
		});

		cp.no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cp.yes.setVisible(false);
				cp.no.setVisible(false);
				cp.messageDialog.setVisible(false);
			}
		});
		
	}

	public void animate() {
		removeAll();
		add(transitionBG);
		transitionBG.setVisible(true);		
		transitionBG.setOpaque(false);
		y = 0;

		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				y -= 2;

				transitionBG.setLocation(7, y + 7);
				repaint();
				if (y == (-h - 10)) {
					transitionBG.setVisible(false);
					add(gp);
					t.stop();
				}

			}
		});

		t.start();
	}

	public void animation_transition() {
	}

}
