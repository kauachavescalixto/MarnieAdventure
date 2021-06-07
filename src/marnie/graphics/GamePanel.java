package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import database.BD;
import database.Users;
import database.UsersDAO;
import marnie.util.Money;

public class GamePanel extends JPanel {

	private int w, h;

	private JLabel BG = new JLabel(new ImageIcon(getClass().getResource("BG1152X648.png")));
	private JLabel overFence = new JLabel(new ImageIcon(getClass().getResource("OverFence.png")));
	private JLabel BarraLateral = new JLabel(new ImageIcon(getClass().getResource("BarraLateral.png")));
	private JButton closeBt;

	private JPanel animais;
	public AnimalPanel[] ap;
	public static Money money;
	private Timer t;

	private JPanel rancho = new JPanel();
	private int random, random2;

	public PreGameConnection pgc;

	public int qntdGalinhas;
	public int qntdPatos;
	public int qntdVacas;
	public int qntdCabras;
	public int qntdOvelhas;

	private int[] precos = new int[] { 10, 15, 20, 25, 30 };

	private JPanel nomePlayerpn;
	public JLabel nomePlayer;

	private JLabel warning = new JLabel(new ImageIcon(getClass().getResource("MarnieWarning.png")));
	private JLabel endGameWin = new JLabel(new ImageIcon(getClass().getResource("endGameWin.png")));
	private JLabel endGameLose = new JLabel(new ImageIcon(getClass().getResource("endGameLose.png")));
	private JButton endGameBt = new JButton();
	private JButton hard = new JButton();
	private JButton easy = new JButton();
	private Timer hTimer;
	private int inverted;
	private int max;
	private int grana;
	private JLabel time;

	private int[] unlockprecos = new int[] { 0, 20, 40, 70, 100 };

	public GamePanel(int w, int h, boolean firstTime) {
		this.w = w;
		this.h = h;
		
		pgc = MainPanel.pgc;

		setLayout(null);
		setPreferredSize(new Dimension(w, h));

		overFence.setSize(w, h);
		add(overFence);
		if (firstTime) {
			History();
		}
		init();
		moneyDraw();
		def_eventos();
		entities();
		add(BG);
	}

	public void History() {
		warning.setSize(w, h);

		hard.setSize(262, 67);
		hard.setLocation(399, 398);
		hard.setContentAreaFilled(false);
		easy.setSize(262, 67);
		easy.setLocation(127, 398);
		easy.setContentAreaFilled(false);

		time = new JLabel("");
		time.setFont(new Font("Courier new", Font.ITALIC + Font.BOLD, 24));
		time.setForeground(Color.white);
		time.setSize(200, 50);
		time.setLocation(850, 613);
		time.setText("calculando...");

		add(time);
		time.setVisible(false);
		add(hard);
		add(easy);
		add(warning);
		add(endGameBt);
		endGameWin.setVisible(false);
		add(endGameWin);
		endGameLose.setVisible(false);
		add(endGameLose);
		endGameBt.setVisible(false);
	}

	public void HistoryTimer() {
		time.setVisible(true);

		endGameBt.setSize(262, 67);
		endGameBt.setLocation(267, 398);
		endGameBt.setContentAreaFilled(false);
		endGameWin.setSize(w, h);
		endGameLose.setSize(w, h);

		inverted = max * 1000;

		hTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (money.getMoney() >= grana) {
					hTimer.stop();
					endGameWin.setVisible(true);
					endGameBt.setVisible(true);
				}

				String text = "";

				time.setText(text.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(inverted),
						TimeUnit.MILLISECONDS.toMinutes(inverted)
								- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(inverted)),
						TimeUnit.MILLISECONDS.toSeconds(inverted)
								- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(inverted))));

				inverted -= 1000;

				if (inverted <= 0) {
					hTimer.stop();
					endGameLose.setVisible(true);
					endGameBt.setVisible(true);

					pgc.usersdao.atualizar(pgc.usersdao.EXCLUSAO);
				}

			}
		});
		hTimer.start();
	}

	public void init() {

		closeBt = new JButton();
		closeBt.setSize(50, 50);
		closeBt.setLocation(w - closeBt.getWidth() - 5, 5);
		closeBt.setContentAreaFilled(false);

		BarraLateral.setSize(w, h);
		BG.setSize(w, h);

		animais = new JPanel();
		animais.setSize((int) (w / 3.10f), h);
		animais.setLocation((int) (w / 3.8f), 0);
		animais.setOpaque(false);

		ap = new AnimalPanel[5];

		ap[0] = new AnimalPanel(w, h, 0, false, pgc.usersdao.users.getGalinha());
		animais.add(ap[0]);

		int[] getters = new int[] { pgc.usersdao.users.getGalinha(), pgc.usersdao.users.getPato(),
				pgc.usersdao.users.getVaca(), pgc.usersdao.users.getCabra(), pgc.usersdao.users.getOvelha() };

		for (int i = 1; i < getters.length; i++) {
			if (getters[i] > 0) {
				ap[i] = new AnimalPanel(w, h, i, false, getters[i]);
			} else {
				ap[i] = new AnimalPanel(w, h, i, true, getters[i]);
			}
		}

		for (int i = 1; i < ap.length; i++) {
			animais.add(ap[i]);
		}

		money = new Money(w, h);
		money.setSize(43 + 300, 50);
		money.setLocation(w - (int) (money.getWidth() * 1.5), 0);

		rancho = new JPanel();
		rancho.setSize(335, 380);
		rancho.setLocation(w - 390, h - 485);
		rancho.setOpaque(false);
		rancho.setLayout(null);

		nomePlayer = new JLabel(pgc.usersdao.users.getNome());
		nomePlayer.setFont(new Font("Courier new", Font.BOLD, 18));
		nomePlayer.setForeground(new Color(80, 29, 17));

		nomePlayerpn = new JPanel();
		nomePlayerpn.setSize(150, 25);
		nomePlayerpn.setLocation(70, 150);
		nomePlayerpn.setOpaque(false);
		nomePlayerpn.add(nomePlayer);

		add(nomePlayerpn);
		add(rancho);
		add(money);
		add(animais);
		add(closeBt);
		add(BarraLateral);

	}

	public void entities() {
		for (int i = 0; i < ap.length; i++) {
			for (int j = 0; j < ap[i].getQntd(); j++) {
				random = (int) (Math.random() * 200);
				random2 = (int) (Math.random() * 200);
				Entity e = new Entity(i, rancho.getWidth(), rancho.getHeight(), random, random2);
				rancho.add(e);
			}
		}

		repaint();

	}

	public void moneyDraw() {
		int[] qntd = new int[] { ap[0].getQntd(), ap[1].getQntd(), ap[2].getQntd(), ap[3].getQntd(), ap[4].getQntd(), };
		money.addLongTimeMoney(pgc.usersdao.users.getLogin(), qntd);
		money.addMoney(pgc.usersdao.users.getDinheiro());
		t = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				money.update(ap[0].getCount(), 0, ap[0].getQntd());
				money.update(ap[1].getCount(), 1, ap[1].getQntd());
				money.update(ap[2].getCount(), 2, ap[2].getQntd());
				money.update(ap[3].getCount(), 3, ap[3].getQntd());
				money.update(ap[4].getCount(), 4, ap[4].getQntd());
				repaint();

			}
		});
		t.start();
	}

	public void def_eventos() {

		closeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pgc.usersdao.users.setGalinha(ap[0].getQntd());
				pgc.usersdao.users.setPato(ap[1].getQntd());
				pgc.usersdao.users.setVaca(ap[2].getQntd());
				pgc.usersdao.users.setCabra(ap[3].getQntd());
				pgc.usersdao.users.setOvelha(ap[4].getQntd());
				pgc.usersdao.users.setDinheiro((int) (money.getMoney()));
				pgc.usersdao.users.setLogin((int) (System.currentTimeMillis()));

				pgc.usersdao.atualizar(pgc.usersdao.ALTERACAO);

				System.exit(0);
			}
		});
		
		ap[0].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= precos[0]) {
					random = (int) (Math.random() * 300);
					random2 = (int) (Math.random() * 300);
					rancho.add(new Entity(0, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[0]);
				}
			}
		});
		ap[1].unlockBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= unlockprecos[1]) {
					random = (int) (Math.random() * 300);
					random2 = (int) (Math.random() * 300);
					rancho.add(new Entity(1, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[1]);
				}
			}
		});
		ap[1].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= precos[1]) {
					random = (int) (Math.random() * 300);
					random2 = (int) (Math.random() * 300);
					rancho.add(new Entity(1, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[1]);
				}
			}
		});
		ap[2].unlockBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= unlockprecos[2]) {
					random = (int) (Math.random() * 180);
					random2 = (int) (Math.random() * 200);
					rancho.add(new Entity(2, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[2]);
				}
			}
		});
		ap[2].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= precos[2]) {
					random = (int) (Math.random() * 180);
					random2 = (int) (Math.random() * 200);
					rancho.add(new Entity(2, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[2]);
				}
			}
		});
		ap[3].unlockBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= unlockprecos[3]) {
					random = (int) (Math.random() * 225);
					random2 = (int) (Math.random() * 225);
					rancho.add(new Entity(3, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[3]);
				}
			}
		});
		ap[3].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= precos[3]) {
					random = (int) (Math.random() * 225);
					random2 = (int) (Math.random() * 225);
					rancho.add(new Entity(3, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[3]);
				}
			}
		});
		ap[4].unlockBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= unlockprecos[4]) {
					random = (int) (Math.random() * 200);
					random2 = (int) (Math.random() * 200);
					rancho.add(new Entity(4, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[4]);
				}
			}
		});
		ap[4].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (money.getMoney() >= precos[4]) {
					random = (int) (Math.random() * 200);
					random2 = (int) (Math.random() * 200);
					rancho.add(new Entity(4, rancho.getWidth(), rancho.getHeight(), random, random2));
					repaint();
					money.updateMoney(precos[4]);
				}
			}
		});

		endGameBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pgc.usersdao.atualizar(pgc.usersdao.EXCLUSAO);
				System.exit(0);
			}
		});

		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				max = 432000;
				grana = 100000;
				hard.setVisible(false);
				easy.setVisible(false);
				warning.setVisible(false);
				HistoryTimer();
			}
		});

		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				max = 360;
				grana = 100;
				hard.setVisible(false);
				easy.setVisible(false);
				warning.setVisible(false);
				HistoryTimer();
			}
		});

	}

}
