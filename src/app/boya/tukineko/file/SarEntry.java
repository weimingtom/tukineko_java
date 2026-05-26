package app.boya.tukineko.file;

public class SarEntry {
	public String name;
	public int offset;
	public int length;

	public SarEntry(String name, int offset, int length) {
		this.name = name;
		this.offset = offset;
		this.length = length;
	}

	public int length() {
		return this.length;
	}

	@Override
	public String toString() {
		return new String("SarEntry name:" + this.name + ",offset:"
				+ this.offset + ",length:" + this.length);
	}
}
