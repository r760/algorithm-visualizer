import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Algorithm Visualizer
 * 
 * @author https://github.com/r760/
 */
public class algorithmVisualizer extends JPanel {
	private static final long serialVersionUID = 1;
	private Font regularFont;
	private Font largeFont;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;

	private int actionDelay;
	private int repaintDelay;
	private Timer timer;

	private final int MAX_SIZE = 50;
	private Node[] array = new Node[MAX_SIZE];

	/**
	 * Constructor, initializes variables, and calls init()
	 */
	algorithmVisualizer() {
		frame = new JFrame("algorithmVisualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1090, 990);
		frame.setResizable(false);
		this.setBackground(Color.BLACK);

		regularFont = new Font("Arial", Font.ITALIC, 18);
		largeFont = new Font("Arial", Font.ITALIC, 22);

		menu = new JMenu("Settings");
		menu.setFont(largeFont);
		menu.setBackground(Color.BLACK);
		menu.setForeground(Color.GREEN);

		menuBar = new JMenuBar();
		menuBar.setFont(largeFont);
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.GREEN);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		setAnimationSpeed(250);
		initAnimationSpeedMenu();
		init();
	}

	/**
	 * Sets the animation speed
	 * 
	 * @param delay
	 * @return void
	 */
	private void setAnimationSpeed(int delay) {
		actionDelay = delay;
		repaintDelay = actionDelay / 2;
		timer = new Timer(repaintDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
	}

	/**
	 * Initializes the animation speed menu
	 * 
	 * @return void
	 */
	private void initAnimationSpeedMenu() {
		JMenu speedMenu = new JMenu("Animation Speed");
		speedMenu.setFont(largeFont);

		JMenuItem jMenuItem1, jMenuItem2, jMenuItem3, jMenuItem4, jMenuItem5;
		jMenuItem1 = new JMenuItem("Speed 1");
		jMenuItem2 = new JMenuItem("Speed 2");
		jMenuItem3 = new JMenuItem("Speed 3");
		jMenuItem4 = new JMenuItem("Speed 4");
		jMenuItem5 = new JMenuItem("Speed 5");

		jMenuItem1.setFont(largeFont);
		jMenuItem2.setFont(largeFont);
		jMenuItem3.setFont(largeFont);
		jMenuItem4.setFont(largeFont);
		jMenuItem5.setFont(largeFont);

		jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnimationSpeed(750);
			}
		});

		jMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnimationSpeed(500);
			}
		});

		jMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnimationSpeed(250);
			}
		});

		jMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnimationSpeed(125);
			}
		});

		jMenuItem5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnimationSpeed(25);
			}
		});

		speedMenu.add(jMenuItem1);
		speedMenu.add(jMenuItem2);
		speedMenu.add(jMenuItem3);
		speedMenu.add(jMenuItem4);
		speedMenu.add(jMenuItem5);
		menu.add(speedMenu);
	}

	/**
	 * Randomizes array
	 * 
	 * @return void
	 */
	private void randomizeArray() {
		for (int i = 0; i < array.length; i++) {
			array[i] = new Node(new Random().nextInt(600) + 200);
		}
	}

	/**
	 * Reloads the frame
	 * 
	 * @return void
	 */
	private void reloadFrame() {
		frame.setVisible(false);
		frame.setVisible(true);
	}

	/**
	 * Produces a blink animation for the key Node
	 * 
	 * @param key
	 * @param c1
	 * @param c2
	 * @param duration
	 * @throws InterruptedException
	 * @return void
	 */
	private void blink(Node key, Color c1, Color c2, int duration) throws InterruptedException {
		key.setC(c1);
		Thread.sleep(duration);
		key.setC(c2);
		Thread.sleep(duration);
		key.setC(c1);
	}

	/**
	 * Produces a blink animation for Nodes k1 and k2
	 * 
	 * @param k1
	 * @param k2
	 * @param c1
	 * @param c2
	 * @param duration
	 * @throws InterruptedException
	 * @return void
	 */
	private void blink(Node k1, Node k2, Color c1, Color c2, int duration) throws InterruptedException {
		k1.setC(c1);
		k2.setC(c1);
		Thread.sleep(duration);
		k1.setC(c2);
		k2.setC(c2);
		Thread.sleep(duration);
		k1.setC(c1);
		k2.setC(c1);
	}

	/**
	 * Runs and animates the insertion sort algorithm on array
	 * 
	 * @return void
	 */
	private void insertionSort() {
		timer.start();
		new Thread() {
			public void run() {
				try {
					for (int i = 1; i < array.length; i++) {
						Node key = array[i];
						int j = i;
						blink(key, Color.RED, Color.GRAY, actionDelay);
						while (j > 0 && key.compareTo(array[j - 1]) < 0) {
							Thread.sleep(actionDelay);
							array[j] = array[j - 1];
							array[--j] = key;
						}
						blink(key, Color.GREEN, Color.GRAY, actionDelay);
						key.setC(Color.GRAY);
					}
					reloadFrame();
					sleep(actionDelay);
					timer.stop();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}.start();
	}

	/**
	 * Runs and animates the selection sort algorithm on array
	 * 
	 * @return void
	 */
	private void selectionSort() {
		timer.start();
		new Thread() {
			public void run() {
				try {
					for (int i = 0; i < array.length - 1; i++) {
						int index = i;
						Node key = array[index];
						for (int j = i + 1; j < array.length; j++) {
							if (array[j].compareTo(array[index]) < 0)
								index = j;
						}
						blink(key, Color.RED, Color.GRAY, actionDelay);
						if (index != i) {
							blink(array[index], Color.RED, Color.GRAY, actionDelay);
							Thread.sleep(actionDelay);
							array[i] = array[index];
							array[index] = key;
							blink(array[i], array[index], Color.GREEN, Color.GRAY, actionDelay);
							array[i].setC(Color.GRAY);
							array[index].setC(Color.GRAY);
						} else {
							Thread.sleep(actionDelay * 2);
							key.setC(Color.GREEN);
							Thread.sleep(actionDelay * 2);
							key.setC(Color.GRAY);
						}
					}
					reloadFrame();
					sleep(actionDelay);
					timer.stop();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}.start();
	}

	/**
	 * Runs and animates the bubble sort algorithm on array
	 * 
	 * @return void
	 */
	private void bubbleSort() {
		timer.start();
		new Thread() {
			public void run() {
				try {
					for (int i = 0; i < array.length - 1; i++) {
						for (int j = 0; j < (array.length - 1 - i); j++) {
							blink(array[j], array[j + 1], Color.RED, Color.GRAY, actionDelay);
							blink(array[j], array[j + 1], Color.RED, Color.GRAY, actionDelay);
							if (array[j].compareTo(array[j + 1]) > 0) {
								int temp = array[j].getX();
								array[j].setX(array[j + 1].getX());
								array[j + 1].setX(temp);
							}
							blink(array[j], array[j + 1], Color.GREEN, Color.GRAY, actionDelay);
							array[j].setC(Color.GRAY);
							array[j + 1].setC(Color.GRAY);
						}
					}
					reloadFrame();
					sleep(actionDelay);
					timer.stop();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}.start();
	}

	/**
	 * Initializes Swing components, and their listeners
	 * 
	 * @return void
	 */
	private void init() {
		randomizeArray();

		JButton randomizeArrayButton = new JButton("Randomize");
		randomizeArrayButton.setBounds(20, 10, 150, 40);
		randomizeArrayButton.setFocusPainted(false);
		randomizeArrayButton.setFont(regularFont);
		randomizeArrayButton.setBackground(Color.BLACK);
		randomizeArrayButton.setForeground(Color.GREEN);
		randomizeArrayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomizeArray();
				reloadFrame();
			}
		});

		JButton insertionSortButton = new JButton("Insertion Sort");
		insertionSortButton.setBounds(180, 10, 150, 40);
		insertionSortButton.setFocusPainted(false);
		insertionSortButton.setFont(regularFont);
		insertionSortButton.setBackground(Color.BLACK);
		insertionSortButton.setForeground(Color.GREEN);
		insertionSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertionSort();
			}
		});

		JButton selectionSortButton = new JButton("Selection Sort");
		selectionSortButton.setBounds(340, 10, 150, 40);
		selectionSortButton.setFocusPainted(false);
		selectionSortButton.setFont(regularFont);
		selectionSortButton.setBackground(Color.BLACK);
		selectionSortButton.setForeground(Color.GREEN);
		selectionSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionSort();
			}
		});

		JButton bubbleSortButton = new JButton("Bubble Sort");
		bubbleSortButton.setBounds(500, 10, 150, 40);
		bubbleSortButton.setFocusPainted(false);
		bubbleSortButton.setFont(regularFont);
		bubbleSortButton.setBackground(Color.BLACK);
		bubbleSortButton.setForeground(Color.GREEN);
		bubbleSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleSort();
			}
		});

		frame.add(randomizeArrayButton);
		frame.add(insertionSortButton);
		frame.add(selectionSortButton);
		frame.add(bubbleSortButton);
		frame.add(this);
		reloadFrame();
	}

	/**
	 * Paints bars
	 * 
	 * @return void
	 */
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

	public static void main(String[] args) {
		new algorithmVisualizer();
	}
}