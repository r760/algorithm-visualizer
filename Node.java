import java.awt.Color;

public class Node implements Comparable<Node> {
	private int x;
	private Color c;

	Node(int x) {
		setX(x);
		setC(Color.BLACK);
	}

	Node(int x, Color c) {
		setX(x);
		setC(c);
	}

	public int getX() {
		return x;
	}

	public Color getC() {
		return c;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int compareTo(Node other) {
		return this.getX() - other.getX();
	}
}
