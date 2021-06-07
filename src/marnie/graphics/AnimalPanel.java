package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import marnie.util.Money;

public class AnimalPanel extends JPanel {

	int w, h;

	private String[] nomes = new String[] { "Galinha", "Pato", "Vaca", "Cabra", "Ovelha" };
	int[] max = new int[] { 10, 8, 6, 3, 1 };

	private JLabel img;
	private JLabel BG = new JLabel(new ImageIcon(getClass().getResource("BGItem371X129.png")));

	private JLabel locked_BG = new JLabel(new ImageIcon(getClass().getResource("BGItemLocked371X129.png")));
	public JButton unlockBt = new JButton();
	private JLabel unlockLb;

	private JLabel comprarLb;
	private int count = 0;
	public JButton comprarBt = new JButton();
	private JLabel name;
	private JPanel alignName;
	private JLabel qntd;
	private int intQntd;

	private int[] precos = new int[] { 10, 15, 20, 25, 30 };
	
	private JLabel time;

	private int division;
	private int progresso;
	private JProgressBar bar;

	private Timer t;
	private int inverted;

	private float[] lucro = new float[] { 1f, 1.5f, 2f, 3.5f, 4f };
	private JLabel lbLucro = new JLabel();

	public AnimalPanel(int w, int h, int index, boolean lock, int qntd) {
		
		intQntd = qntd;
		
		if (lock) {
			this.w = (int) (w / 3.10f);
			this.h = (int) (h / 5.2f);
			setPreferredSize(new Dimension((int) (w / 3.10f), (int) (h / 5.2f)));
			setLayout(null);
			setOpaque(false);

			lockedInit(index);
		} else {
			this.w = (int) (w / 3.10f);
			this.h = (int) (h / 5.2f);
			setPreferredSize(new Dimension((int) (w / 3.10f), (int) (h / 5.2f)));
			setLayout(null);
			setOpaque(false);

			init(index);
		}
		
		

	}

	public void lockedInit(int index) {

		try {
			img = new JLabel(new ImageIcon(getClass().getResource("Locked" + nomes[index] + ".png")));
			img.setSize(48, 48);
			img.setLocation(35, 30);
			add(img);
		} catch (NullPointerException e) {

		}
		alignName = new JPanel();
		alignName.setBounds(15, h - 33, (int) (w / 4), (int) (h / 5.8));
		alignName.setOpaque(false);

		name = new JLabel(nomes[index], SwingConstants.CENTER);
		name.setFont(new Font("Courier new", Font.ITALIC + Font.BOLD, 18));
		name.setForeground(new Color(80, 29, 17));

		alignName.add(name);

		qntd = new JLabel("x" + intQntd);
		qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 48));
		qntd.setForeground(new Color(80, 29, 17));
		qntd.setSize(150, 150);
		qntd.setLocation(w - qntd.getWidth() + 30, h - qntd.getHeight() + 20);

		bar = new JProgressBar();
		bar.setSize(225, 20);
		bar.setLocation(alignName.getWidth() + 33, 10);
		bar.setForeground(new Color(230, 115, 47));
		bar.setOpaque(false);
		bar.setBorder(BorderFactory.createLineBorder(new Color(225, 110, 45, 50)));

		unlockBt.setSize(108, 24);
		unlockBt.setLocation(w - 125, 60);
		unlockBt.setContentAreaFilled(false);

		int[] unlockprecos = new int[] { 0, 20, 40, 70, 100 };
		unlockLb = new JLabel("" + unlockprecos[index]);
		unlockLb.setSize(100, 25);
		unlockLb.setLocation(w - 110, 85);
		unlockLb.setForeground(Color.white);
		unlockLb.setFont(new Font("courier new", Font.ITALIC, 22));
		
		add(unlockLb);
		add(unlockBt);
		add(bar);

		add(alignName);
		locked_BG.setSize(w, h);
		add(locked_BG);

		
		unlockBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (GamePanel.money.getMoney() >= unlockprecos[index]) {
					intQntd++;
					GamePanel.money.updateMoney(unlockprecos[index]);
					
					removeAll();
					init(index);
					
					repaint();
				} else {
				}

			}
		});

	}

	public void init(int index) {

		try {
			img = new JLabel(new ImageIcon(getClass().getResource(nomes[index] + ".png")));
			img.setSize(48, 48);
			img.setLocation(35, 30);
			add(img);
		} catch (NullPointerException e) {

		}
		comprarBt.setSize((int) (w / 2.46f), (int) (h / 5.5));
		comprarBt.setLocation((int) (comprarBt.getWidth() * 0.84f), (int) (comprarBt.getHeight() * 1.9f));
		comprarBt.setContentAreaFilled(false);
		comprarBt.setBorder(null);
		
		alignName = new JPanel();
		alignName.setBounds(15, h - 33, (int) (w / 4), (int) (h / 5.8));
		alignName.setOpaque(false);

		name = new JLabel(nomes[index], SwingConstants.CENTER);
		name.setFont(new Font("Courier new", Font.ITALIC + Font.BOLD, 18));
		name.setForeground(new Color(80, 29, 17));

		alignName.add(name);

		time = new JLabel("");
		time.setFont(new Font("Courier new", Font.ITALIC + Font.BOLD, 18));
		time.setForeground(new Color(80, 29, 17));
		time.setSize(100, 50);
		time.setLocation((int) (w / 2.5), h - 55);

		qntd = new JLabel("x" + intQntd);
		qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 44));
		qntd.setForeground(new Color(80, 29, 17));
		qntd.setSize(150, 150);
		qntd.setLocation(w - qntd.getWidth() + 70, h - qntd.getHeight() + 20);

		if (intQntd >= 10) {
			qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 34));
		}
		if (intQntd >= 100) {
			qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 24));
		}
		if (intQntd >= 1000) {
			qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 18));
		}
		
		comprarLb = new JLabel("Comprar R$ " + precos[index]);
		comprarLb.setFont(new Font("Courier new", Font.ITALIC + Font.BOLD, 16));
		comprarLb.setForeground(new Color(80, 29, 17));
		comprarLb.setSize(150, 50);
		comprarLb.setLocation((int) (w / 2.5) - 15, h - 97);

		bar = new JProgressBar();
		bar.setSize(225, 20);
		bar.setLocation(alignName.getWidth() + 33, 10);
		bar.setForeground(new Color(230, 115, 47));
		bar.setOpaque(false);
		bar.setBorder(BorderFactory.createLineBorder(new Color(225, 110, 45)));
		bar.setMaximum(1000);
		
		lbLucro.setSize(150, 25);
		lbLucro.setLocation((w / 2) - (lbLucro.getWidth() / 2) + alignName.getWidth(), 10);
		lbLucro.setFont(new Font("Courier new", Font.BOLD, 14));
		lbLucro.setForeground(new Color(80, 29, 17));
		lbLucro.setText("+ R$ " + lucro[index]);

		add(lbLucro);
		add(bar);
		add(comprarLb);
		add(qntd);
		add(time);
		Timer(max[index], index);
		add(alignName);
		add(comprarBt);
		BG.setSize(w, h);
		add(BG);

		def_eventos(index);
		time.setText("00:00");
	}

	public void Timer(int max, int index) {

		progresso = 0;

		division = 1000 / max / 10;
		inverted = max * 1000;
		t = new Timer(100, new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				count = 0;

				progresso += division;
				bar.setValue(progresso);

				String text = "";

				time.setText(text.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(inverted),
						TimeUnit.MILLISECONDS.toSeconds(inverted)
								- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(inverted))));

				inverted -= 100;
				if (inverted <= 0) {
					progresso = 0;
					inverted = max * 1000;
					count++;
					t.restart();
				}

			}
		});
		t.start();

	}

	public void def_eventos(int index) {

		comprarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (GamePanel.money.getMoney() >= precos[index]) {
					intQntd++;
					if (intQntd >= 10) {
						qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 34));
					}
					if (intQntd >= 100) {
						qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 24));
					}
					if (intQntd >= 1000) {
						qntd.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 18));
					}

					qntd.setText("x" + intQntd);
				}
			}
		});

	}

	public int getCount() {
		return count;
	}

	public int getQntd() {
		return intQntd;

	}

}
