package app.boya.tukineko.entry;

import java.io.Serializable;

public class NsButton implements Serializable {
	private static final long serialVersionUID = 1L;

	public int no;
	public int x;
	public int y;
	public int width;
	public int height;
	public int u;
	public int v;

	public NsButton(int no, int x, int y, int width, int height, int u, int v) {
		this.no = no;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.u = u;
		this.v = v;
	}
}
