import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class sortingAlgorithm extends algorithmVisualizer {
	private static final long serialVersionUID = 1;
	protected final int MAX = 50;
	protected int[] array = new int[MAX];

	sortingAlgorithm() {
		super();
		init();
	};

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

		frame.add(randomizeArrayButton);
		frame.add(insertionSortButton);
		frame.add(this);
		reloadFrame();
	}

	protected void randomizeArray() {
		for (int i = 0; i < array.length; i++) {
			array[i] = new Random().nextInt(600) + 200;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = 20, height, offset = 0;
		for (int a : array) {
			height = a;
			g.fillRect(20 + offset, 930 - height, width, height);
			offset += width + 1;
		}
	}

	protected void insertionSort() {
		timer.start();
		new Thread() {
			public void run() {
				for (int i = 1; i < array.length; i++) {
					int key = array[i];
					int j = i;
					while (j > 0 && key < array[j - 1]) {
						try {
							Thread.sleep(ACTION_DELAY);
						} catch (Exception e) {
							e.printStackTrace();
						}
						array[j] = array[j - 1];
						array[--j] = key;
					}
				}
				timer.stop();
			}
		}.start();
	}

	public static void main(String[] args) {
		new sortingAlgorithm();
	}
}
