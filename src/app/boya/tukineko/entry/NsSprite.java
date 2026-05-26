package app.boya.tukineko.entry;

import java.io.Serializable;

public class NsSprite implements Serializable {
	private static final long serialVersionUID = 1L;

	public String image;
	public int x;
	public int y;
	public int alpha;
	public boolean visible;

	public NsSprite() {
		this.visible = false;
	}
}
