import java.awt.Color;

/**
 * @author https://github.com/r760/
 */
public class Node implements Comparable<Node> {
	private int x;
	private Color c;

	/**
	 * Constructor, initializes variables
	 */
	Node(int x) {
		setX(x);
		setC(Color.GRAY);
	}

	/**
	 * Constructor, initializes variables
	 */
	Node(int x, Color c) {
		setX(x);
		setC(c);
	}

	/**
	 * Returns x
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns c
	 * 
	 * @return c
	 */
	public Color getC() {
		return c;
	}

	/**
	 * Sets class variable x to parameter x
	 * 
	 * @param x
	 * @return void
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets class variable c to parameter c
	 * 
	 * @param c
	 * @return void
	 */
	public void setC(Color c) {
		this.c = c;
	}

	/**
	 * Compares this Node to other Node
	 * 
	 * @param other
	 * @return an integer i such that i <= 0 iff this <= other or i >= 1 iff this >
	 *         other
	 */
	public int compareTo(Node other) {
		return this.getX() - other.getX();
	}
}
