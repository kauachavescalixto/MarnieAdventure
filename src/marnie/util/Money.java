package marnie.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import marnie.graphics.AnimalPanel;

public class Money extends JPanel {

	private JLabel lbMoney = new JLabel("");
	public float money;
	public float ResultMoney;
	private JLabel imgMoney = new JLabel(new ImageIcon(getClass().getResource("Money.png")));
	private int vezes;
	
	private JButton bt;
	
	public Money() {

	}

	public Money(int w, int h) {
		imgMoney.setSize(43, 50);
		lbMoney = new JLabel("R$ " + money);
		lbMoney.setFont(new Font("courier new", Font.ITALIC, 32));
		lbMoney.setForeground(Color.white);

		bt = new JButton("dev");
		bt.setSize(0,0);
		add(bt);
		add(imgMoney);
		add(lbMoney);
		setOpaque(false);
	}

	public void update(int count, int index, int qntd) {

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMoney(10);
			}
		});
		
		
		switch (index) {
		case 0:
			money += count;
			break;
		case 1:
			money += count * 1.5f;
			break;
		case 2:
			money += count * 2f;
			break;
		case 3:
			money += count * 3.5;
			break;
		case 4:
			money += count * 4f;
			break;
		}
		ResultMoney += (money * qntd);
		money = 0;

		lbMoney.setText("R$ " + ResultMoney);
		repaint();

	}

	public float getMoney() {
		return ResultMoney;

	}

	public void updateMoney(int i) {
		ResultMoney -= i;
		lbMoney.setText("R$ " + ResultMoney);
		repaint();
	}

	public void addMoney(int i) {
		ResultMoney += i;
		lbMoney.setText("R$ " + ResultMoney);
		repaint();

	}

	public void addLongTimeMoney(int i, int []qntd) {
		int diferenca = ((int) (System.currentTimeMillis()) - i) / 1000;
		int[] tempo = new int[] { 10, 8, 6, 3, 1 };
		float[] lucro = new float[] {1f, 1.5f, 2f, 3.5f, 4f};
		
		float total = 0;
		
		float rf = 0;
		for (int j = 0; j < 5; j++) {
			vezes = diferenca / tempo[j];
			total += vezes * lucro[j];
			rf = total * qntd[j];
			ResultMoney+=(int)(rf);
		}
		
	}

}
