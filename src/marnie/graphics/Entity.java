package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Entity extends JLabel {

	private String[] nomes = new String[] { "Galinha", "Pato", "Vaca", "Cabra", "Ovelha" };
	private int x;
	private int y;
	private final int acc = 1;
	private final int repeat = 5;

	private ImageIcon img;
	private ImageIcon flippedimg;

	private int w, h;

	private Timer t;

	public Entity(int index, int w, int h, int locationX, int locationY) {
		this.w = w;
		this.h = h;
		init(index);

		x = locationX;
		y = locationY;

	}

	public void init(int index) {
		img = new ImageIcon(getClass().getResource("Resized" + nomes[index] + ".png"));
		flippedimg = new ImageIcon(getClass().getResource("ResizedFlipped" + nomes[index] + ".png"));

		switch (index) {
		case 0:
			setSize(50, 50);
			break;
		case 1:
			setSize(36, 36);
			break;
		case 2:
			setSize(120, 100);
			break;
		case 3:
			setSize(75, 75);
			break;
		case 4:
			setSize(85, 85);
			break;
		default:
			break;
		}
		move();

	}

	public void move() {

		t = new Timer(80, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				repaint();
				
				if (option() == 0) {

					int xMove = (int) (Math.random() * 2);
					int yMove = (int) (Math.random() * 2);

					for (int i = 0; i < repeat; i++) {

						if (xMove == 0) {
							if (x + getWidth() >= w) {
							} else {
								setIcon(img);
								x += acc;
							}

						} else {
							if (x <= 0) {
							} else {
								setIcon(flippedimg);
								x -= acc;
							}

						}
						if (yMove == 0) {
							if (y + getHeight() >= h) {
							} else {
								y += acc;
							}

						} else {
							if (y <= 0) {
							} else {
								y -= acc;
							}

						}

						setLocation(x, y);
						
					}

					
				}
			}
		});

		t.start();
	}

	public int option() {
		return (int) (Math.random() * 2);
	}

}
