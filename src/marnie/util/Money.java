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
	public float money;
	public float ResultMoney;
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

		money = 100;
		
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
	
}
