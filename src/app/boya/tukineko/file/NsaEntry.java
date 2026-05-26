package app.boya.tukineko.file;

public class NsaEntry {
	// FIXME:
	public int offset;
	public int type;
	public int length;

	private String name;
	private int compressLength;

	public NsaEntry(String name, int offset, int compressLength, int type,
			int length) {
		this.name = name;
		this.offset = offset;
		// FIXME:
		this.compressLength = length;
		this.type = type;
		this.length = length;
	}

	public int length() {
		return this.length;
	}

	@Override
	public String toString() {
		return new String("NsaEntry name:" + this.name + ",offset:"
				+ this.offset + ",compress length:" + this.compressLength
				+ ",type:"
				+ (this.type == 2 ? "lzss" : this.type == 1 ? "spb" : "raw")
				+ ",length:" + this.length);
	}
}
