package app.boya.tukineko.entry;

import java.io.Serializable;

public class NsSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	public String message;
	public String label;
	public int y;
	public int height;
	public boolean subrutine;
	public boolean selected;

	public NsSelect(String message, String label, int y, int height,
			boolean subrutine) {
		this.message = message;
		this.label = label;
		this.y = y;
		this.height = height;
		this.subrutine = subrutine;
		this.selected = false;
	}

	public NsSelect(String message, String label, int y, int height,
			boolean subrutine, boolean selected) {
		this.message = message;
		this.label = label;
		this.y = y;
		this.height = height;
		this.subrutine = subrutine;
		this.selected = selected;
	}
}
