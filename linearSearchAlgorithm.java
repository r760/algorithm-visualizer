import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class linearSearchAlgorithm extends algorithmVisualizer {
	private static final long serialVersionUID = 1;
	protected final int MAX = 12;

	protected static class Node {
		private int x;
		private char m;

		Node(int x) {
			setX(x);
			m = ' ';
		}

		public int getX() {
			return x;
		}

		public char getM() {
			return m;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setM(char m) {
			this.m = m;
		}
	}

	protected Node[] array = new Node[MAX];

	linearSearchAlgorithm() {
		super();
		init();
	}

	private void init() {
		randomizeArray();

		JButton randomizeArrayButton = new JButton("Randomize Array");
		randomizeArrayButton.setBounds(20, 10, 150, 40);
		randomizeArrayButton.setFocusPainted(false);
		randomizeArrayButton.setFont(regularFont);
		randomizeArrayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomizeArray();
				repaint();
			}
		});

		JTextField linearSearchTextField = new JTextField("" + array[0].getX());
		linearSearchTextField.setBounds(340, 10, 150, 40);
		linearSearchTextField.setFont(regularFont);
		linearSearchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(linearSearchTextField.getText());
				linearSearchTextField.setText("" + x);
			}
		});

		JButton linearSearchButton = new JButton("Linear Search");
		linearSearchButton.setBounds(180, 10, 150, 40);
		linearSearchButton.setFocusPainted(false);
		linearSearchButton.setFont(regularFont);
		linearSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(linearSearchTextField.getText());
				linearSearch(x);
				repaint();
			}
		});

		frame.add(randomizeArrayButton);
		frame.add(linearSearchButton);
		frame.add(linearSearchTextField);
		frame.add(this);
		reloadFrame();
	}

	protected void randomizeArray() {
		for (int i = 0; i < array.length; i++) {
			int r = new Random().nextInt(700) + 100;
			array[i] = new Node(r);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = 100, height = width, offset = 0;
		Font largerFont = new Font("Arial", Font.PLAIN, 22);
		g.setFont(largerFont);
		for (Node a : array) {
			g.setColor(Color.BLACK);

			if (a.getM() == 'g') {
				g.setColor(Color.GREEN);
			} else if (a.getM() == 'r') {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}

			g.fillRect(20 + offset, height, width, height);
			g.setColor(Color.WHITE);

			g.drawString(a.getX() + "", offset + width - 45, height + 50);
			offset += width + 1;
		}
	}

	protected void linearSearch(int key) {
		timer.start();
		new Thread() {
			public void run() {
				try {
					for (Node n : array) {
						if (n.getX() == key) {
							for (int i = 0; i < 5; i++) {
								n.setM('g');
								repaint();
								Thread.sleep(ACTION_DELAY * 2);

								n.setM(' ');
								repaint();
								Thread.sleep(ACTION_DELAY * 2);
							}
							break;
						} else {
							n.setM('r');
						}
						Thread.sleep(ACTION_DELAY * 2);
					}

					for (Node a : array) a.setM(' ');
					repaint();

					timer.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void main(String[] args) {
		new linearSearchAlgorithm();
	}
}
