package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.omg.CORBA.DynamicImplementation;

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

	public UsersDAO usersdao;

	public int qntdGalinhas;
	public int qntdPatos;
	public int qntdVacas;
	public int qntdCabras;
	public int qntdOvelhas;

	private JPanel nomePlayerpn;
	public JLabel nomePlayer;
	
	public GamePanel(int w, int h, int id) {
		this.w = w;
		this.h = h;
		usersdao = new UsersDAO();
		usersdao.users.setId(id);

		setLayout(null);
		setPreferredSize(new Dimension(w, h));

		overFence.setSize(w, h);
		add(overFence);
		init();
		moneyDraw();
		def_eventos();
		entities();
		add(BG);
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

		if (usersdao.localizar()) {

			ap[0] = new AnimalPanel(w, h, 0, false, usersdao.users.getGalinha());
			animais.add(ap[0]);

			int[] getters = new int[] {usersdao.users.getGalinha(),
					usersdao.users.getPato(),
					usersdao.users.getVaca(),
					usersdao.users.getCabra(),
					usersdao.users.getOvelha()};

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

		} else {
			System.out.println("ERROR - Not found data");
		}

		money = new Money(w, h);
		money.setSize(43 + 300, 50);
		money.setLocation(w - (int) (money.getWidth() * 1.5), 0);

		rancho = new JPanel();
		rancho.setSize(335, 380);
		rancho.setLocation(w - 390, h - 485);
		rancho.setOpaque(false);
		rancho.setLayout(null);

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
				System.exit(0);
			}
		});
		try {

		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		ap[0].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				random = (int) (Math.random() * 300);
				random2 = (int) (Math.random() * 300);
				rancho.add(new Entity(0, rancho.getWidth(), rancho.getHeight(), random, random2));
				repaint();
			}
		});
		ap[1].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				random = (int) (Math.random() * 320);
				random2 = (int) (Math.random() * 320);
				rancho.add(new Entity(1, rancho.getWidth(), rancho.getHeight(), random, random2));
				repaint();

			}
		});
		ap[2].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				random = (int) (Math.random() * 180);
				random2 = (int) (Math.random() * 200);
				rancho.add(new Entity(2, rancho.getWidth(), rancho.getHeight(), random, random2));
				repaint();

			}
		});
		ap[3].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				random = (int) (Math.random() * 225);
				random2 = (int) (Math.random() * 225);
				rancho.add(new Entity(3, rancho.getWidth(), rancho.getHeight(), random, random2));
				repaint();

			}
		});
		ap[4].comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				random = (int) (Math.random() * 200);
				random2 = (int) (Math.random() * 200);
				rancho.add(new Entity(4, rancho.getWidth(), rancho.getHeight(), random, random2));
				repaint();

			}
		});

	}

}
