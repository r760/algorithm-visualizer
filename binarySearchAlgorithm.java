import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class binarySearchAlgorithm extends algorithmVisualizer {
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

	binarySearchAlgorithm() {
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

		JTextField binarySearchTextField = new JTextField("" + array[0].getX());
		binarySearchTextField.setBounds(340, 10, 150, 40);
		binarySearchTextField.setFont(regularFont);
		binarySearchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(binarySearchTextField.getText());
				binarySearchTextField.setText("" + x);
			}
		});

		JButton binarySearchButton = new JButton("Binary Search");
		binarySearchButton.setBounds(180, 10, 150, 40);
		binarySearchButton.setFocusPainted(false);
		binarySearchButton.setFont(regularFont);
		binarySearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(binarySearchTextField.getText());
				binarySearch(x);
				repaint();
			}
		});

		frame.add(randomizeArrayButton);
		frame.add(binarySearchButton);
		frame.add(binarySearchTextField);
		frame.add(this);
		reloadFrame();
	}

	protected void randomizeArray() {
		for (int i = 0; i < array.length; i++) {
			int r = new Random().nextInt(100) + 75;
			if (i != 0)
				r += array[i - 1].getX();
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

			if (a.getM() == 'l') {
				g.setColor(Color.GREEN);
			} else if (a.getM() == 'm') {
				g.setColor(Color.ORANGE);
			} else if (a.getM() == 'h') {
				g.setColor(Color.RED);
			} else if (a.getM() == 'c') {
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.BLACK);
			}

			g.fillRect(20 + offset, height, width, height);
			g.setColor(Color.WHITE);

			g.drawString(a.getX() + "", offset + width - 45, height + 50);
			offset += width + 1;
		}
	}

	protected void binarySearch(int key) {
		timer.start();
		new Thread() {
			public void run() {
				try {
					int x = binarySearch(0, array.length - 1, key);
					for (Node a : array) a.setM(' ');

					if (x > -1) {
						for (int i = 0; i < 5; i++) {
							array[x].setM('c');
							repaint();
							Thread.sleep(ACTION_DELAY * 2);

							array[x].setM(' ');
							repaint();
							Thread.sleep(ACTION_DELAY * 2);
						}

						repaint();
					}

					timer.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	protected int binarySearch(int low, int high, int key) throws Exception {
		if (low <= high) {
			int mid = (low + high) / 2;

			array[low].setM('l');
			Thread.sleep(ACTION_DELAY * 2);
			array[high].setM('h');
			Thread.sleep(ACTION_DELAY * 2);
			array[mid].setM('m');
			Thread.sleep(ACTION_DELAY * 2);

			array[low].setM(' ');
			array[high].setM(' ');
			array[mid].setM(' ');

			if (key == array[mid].getX()) {
				return mid;
			} else if (key < array[mid].getX()) {
				return binarySearch(low, mid - 1, key);
			} else {
				return binarySearch(mid + 1, high, key);
			}
		} else
			return -1;
	}

	public static void main(String[] args) {
		new binarySearchAlgorithm();
	}
}
