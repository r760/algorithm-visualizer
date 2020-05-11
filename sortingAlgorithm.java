import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class sortingAlgorithm extends algorithmVisualizer {
	private static final long serialVersionUID = 1;
	protected final int MAX = 50;
	protected final int ACTION_DELAY = 250;
	protected final int REPAINT_DELAY = ACTION_DELAY / 2;

	protected int[] array = new int[MAX];

	protected Timer timer = new Timer(REPAINT_DELAY, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	});

	sortingAlgorithm() {
		super();
		init();
	};

	private void init() {
		randomizeArray();

		Font f = new Font("Arial", Font.PLAIN, 15);

		JButton randomizeArrayButton = new JButton("Randomize Array");
		randomizeArrayButton.setBounds(20, 10, 150, 40);
		randomizeArrayButton.setFocusPainted(false);
		randomizeArrayButton.setFont(f);
		randomizeArrayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomizeArray();
				repaint();
			}
		});

		JButton insertionSortButton = new JButton("Insertion Sort");
		insertionSortButton.setBounds(180, 10, 150, 40);
		insertionSortButton.setFocusPainted(false);
		insertionSortButton.setFont(f);
		insertionSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertionSort();
			}
		});

		frame.add(randomizeArrayButton);
		frame.add(insertionSortButton);
		frame.add(this);
		repaint();
	}

	protected void randomizeArray() {
		for (int i = 0; i < array.length; i++) {
			array[i] = new Random().nextInt(600) + 200;
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int offSet = 0;
		for (int a : array) {
			g.fillRect(20 + offSet, 60, 20, a);
			offSet += 20;
		}
	}
	protected void insertionSort() {
		timer.start();
		new Thread() {
			public void run() {
				for (int i = 1; i < array.length; i++) {
					int key = array[i];
					int j = i;
					while (j > 0 && array[j - 1] > key) {
						array[j] = array[j - 1];
						j--;
						array[j] = key;
						try {
							Thread.sleep(ACTION_DELAY);
						} catch (Exception e) {
							e.printStackTrace();
						}
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
