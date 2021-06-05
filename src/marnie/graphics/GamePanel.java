package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.omg.CORBA.DynamicImplementation;

import marnie.util.Money;

public class GamePanel extends JPanel {

	int w, h;

	private JLabel BG = new JLabel(new ImageIcon(getClass().getResource("BG1152X648.png")));
	private JLabel BarraLateral = new JLabel(new ImageIcon(getClass().getResource("BarraLateral.png")));

	private JButton closeBt;

	private JPanel animais;
	private AnimalPanel[] ap;

	public static Money money;
	private Timer t;

	public GamePanel(int w, int h) {
		this.w = w;
		this.h = h;
		setLayout(null);
		setPreferredSize(new Dimension(w, h));

		init();
		moneyDraw();
		def_eventos();
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
		
		ap[0] = new AnimalPanel(w, h, 0, false);
		animais.add(ap[0]);
		for (int i = 1; i < ap.length; i++) {
			ap[i] = new AnimalPanel(w, h, i, true);
			animais.add(ap[i]);
		}

		money = new Money(w, h);
		money.setSize(43 + 300, 50);
		money.setLocation(w - (int) (money.getWidth() * 1.5), 0);

		add(money);
		add(animais);
		add(closeBt);
		add(BarraLateral);
		add(BG);

	}

	public void moneyDraw() {

		t = new Timer(1000, new ActionListener() {

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
	}

}
