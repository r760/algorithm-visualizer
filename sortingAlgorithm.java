import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class sortingAlgorithm extends algorithmVisualizer {
	private static final long serialVersionUID = 1;
	protected final int MAX = 50;

	protected Node[] array = new Node[MAX];

	sortingAlgorithm() {
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

		JButton insertionSortButton = new JButton("Insertion Sort");
		insertionSortButton.setBounds(180, 10, 150, 40);
		insertionSortButton.setFocusPainted(false);
		insertionSortButton.setFont(regularFont);
		insertionSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertionSort();
				repaint();
			}
		});

		JButton selectionSortButton = new JButton("Selection Sort");
		selectionSortButton.setBounds(340, 10, 150, 40);
		selectionSortButton.setFocusPainted(false);
		selectionSortButton.setFont(regularFont);
		selectionSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionSort();
				repaint();
			}
		});

		frame.add(randomizeArrayButton);
		frame.add(insertionSortButton);
		frame.add(selectionSortButton);
		frame.add(this);
		reloadFrame();
	}

	protected void randomizeArray() {
		for (int i = 0; i < array.length; i++) {
			array[i] = new Node(new Random().nextInt(600) + 200);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = 20, height, offset = 0;
		for (Node a : array) {
			height = a.getX();
			g.setColor(a.getC());
			g.fillRect(20 + offset, 930 - height, width, height);
			offset += width + 1;
		}
	}

	protected void blink(Node key, Color c1, Color c2, int duration) throws InterruptedException {
		key.setC(c1);
		Thread.sleep(duration);
		key.setC(c2);
		Thread.sleep(duration);
		key.setC(c1);
	}

	protected void insertionSort() {
		timer.start();
		new Thread() {
			public void run() {
				try {
					for (int i = 1; i < array.length; i++) {
						Node key = array[i];
						int j = i;

						blink(key, Color.RED, Color.BLACK, ACTION_DELAY);

						// <loop-condition> j > 0 AND array[i] < array[j-1]
						while (j > 0 && key.compareTo(array[j - 1]) < 0) {
							Thread.sleep(ACTION_DELAY);
							array[j] = array[j - 1];
							array[--j] = key;
						}

						Thread.sleep(ACTION_DELAY * 2);
						key.setC(Color.GREEN);
						Thread.sleep(ACTION_DELAY * 2);
						key.setC(Color.BLACK);
					}

					Thread.sleep(ACTION_DELAY);
					timer.stop();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	protected void selectionSort() {
		timer.start();
		new Thread() {
			public void run() {
				try {
					for (int i = 0; i < array.length - 1; i++) {
						int index = i;
						Node key = array[index];

						for (int j = i + 1; j < array.length; j++) {
							// <if-condition> array[j] < array[index]
							if (array[j].compareTo(array[index]) < 0)
								index = j;
						}

						blink(key, Color.RED, Color.BLACK, ACTION_DELAY);

						if (index != i) {

							Thread.sleep(ACTION_DELAY * 2);

							blink(array[index], Color.RED, Color.BLACK, ACTION_DELAY);

							Thread.sleep(ACTION_DELAY * 2);

							array[i] = array[index];
							array[index] = key;

							array[i].setC(Color.GREEN);
							array[index].setC(Color.GREEN);

							Thread.sleep(ACTION_DELAY);

							array[i].setC(Color.BLACK);
							array[index].setC(Color.BLACK);

						} else {
							Thread.sleep(ACTION_DELAY * 2);
							key.setC(Color.GREEN);
							Thread.sleep(ACTION_DELAY * 2);
							key.setC(Color.BLACK);
						}
					}

					Thread.sleep(ACTION_DELAY);
					timer.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void main(String[] args) {
		new sortingAlgorithm();
	}
}
