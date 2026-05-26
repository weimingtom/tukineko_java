package app.boya.tukineko.entry;

public class NsColor {
	public final static NsColor white = new NsColor(255, 255, 255);
	public final static NsColor black = new NsColor(0, 0, 0);

	private int value;

	public NsColor(int rgb) {
		value = 0xff000000 | rgb;
	}

	public NsColor(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public NsColor(int r, int g, int b, int a) {
		value = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)
				| ((b & 0xFF) << 0);
	}

	public int getRGB() {
		return value;
	}
}
