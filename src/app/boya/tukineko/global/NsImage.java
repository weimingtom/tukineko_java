package app.boya.tukineko.global;

import java.awt.Image;

public class NsImage {
	public String name;
	public Image image;

	public NsImage(String name) {
		this.name = name;
		this.image = null;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return this.image;
	}

	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((NsImage) obj).name);
	}
}
