import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class algorithmVisualizer extends JPanel {
	private static final long serialVersionUID = 1;
	protected int ACTION_DELAY;
	protected int REPAINT_DELAY;
	protected Timer timer;

	protected Font regularFont = new Font("Arial", Font.PLAIN, 15);
	protected Font largeFont = new Font("Arial", Font.PLAIN, 18);
	protected JFrame frame = new JFrame("algorithmVisualizer");
	protected JMenuBar menuBar = new JMenuBar();
	protected JMenu menu = new JMenu("Settings");

	algorithmVisualizer() {
		setAnimationSpeed(1000);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1090, 1010);

		menuBar.setFont(regularFont);
		menu.setFont(largeFont);

		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		initAnimationSpeedMenu();
	}

	private void initAnimationSpeedMenu() {
		JMenu speedMenu = new JMenu("Animation Speed");
		speedMenu.setFont(largeFont);

		JMenuItem jmi1, jmi2, jmi3, jmi4;
		jmi1 = new JMenuItem("Slow");
		jmi2 = new JMenuItem("Medium");
		jmi3 = new JMenuItem("Fast");
		jmi4 = new JMenuItem("Fastest");

		jmi1.setFont(largeFont);
		jmi2.setFont(largeFont);
		jmi3.setFont(largeFont);
		jmi4.setFont(largeFont);

		ActionListener selectSpeed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jmi1) {
					setAnimationSpeed(1000);
				} else if (e.getSource() == jmi2) {
					setAnimationSpeed(750);
				} else if (e.getSource() == jmi3) {
					setAnimationSpeed(500);
				} else if (e.getSource() == jmi4) {
					setAnimationSpeed(250);
				}
			}
		};

		jmi1.addActionListener(selectSpeed);
		jmi2.addActionListener(selectSpeed);
		jmi3.addActionListener(selectSpeed);
		jmi4.addActionListener(selectSpeed);

		speedMenu.add(jmi1);
		speedMenu.add(jmi2);
		speedMenu.add(jmi3);
		speedMenu.add(jmi4);

		menu.add(speedMenu);
	}

	protected void setAnimationSpeed(int delay) {
		ACTION_DELAY = delay;
		REPAINT_DELAY = ACTION_DELAY / 2;
		timer = new Timer(REPAINT_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
	}

	protected void reloadFrame() {
		repaint();
		frame.setVisible(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) {}
}
