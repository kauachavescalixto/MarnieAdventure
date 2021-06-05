package marnie.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import marnie.graphics.AnimalPanel;

public class Money extends JPanel {

	private AnimalPanel ap;

	private JLabel lbMoney = new JLabel("");
	public int money;
	public int ResultMoney;
	private JLabel imgMoney = new JLabel(new ImageIcon(getClass().getResource("Money.png")));

	public Money() {

	}

	public Money(int w, int h) {
		imgMoney.setSize(43, 50);
		lbMoney = new JLabel("R$ " + money);
		lbMoney.setFont(new Font("courier new", Font.ITALIC, 32));
		lbMoney.setForeground(Color.white);

		add(imgMoney);
		add(lbMoney);
		setOpaque(false);
	}

	public void update(int count, int index, int qntd) {

		switch (index) {
		case 0:
			money += count;
			break;
		case 1:
			money += count * 10;
			break;
		case 2:
			money += count * 20;
			break;
		case 3:
			money += count * 30;
			break;
		case 4:
			money += count * 40;
			break;
		}
		ResultMoney += (money * qntd);
		money = 0;
		
		lbMoney.setText("R$ " + ResultMoney);
		repaint();

	}
	
	public int getMoney() {
		return ResultMoney;

	}

	public void updateMoney(int i) {
		ResultMoney -= i;
		lbMoney.setText("R$ " + ResultMoney);
		repaint();
	}
	
}
