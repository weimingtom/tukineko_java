package app.boya.tukineko.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.Hashtable;

public class SarFile {
	private RandomAccessFile raf;
	private int num;
	private int top;
	private Hashtable<String, SarEntry> entry;

	public static void main(String[] args) {
		try {
			SarFile file = new SarFile("arc.sar");
			Enumeration<SarEntry> entries = file.entries();
			System.out.println("size:" + file.size());
			while (entries.hasMoreElements() == true) {
				System.out.println(entries.nextElement().toString());
			}
		} catch (IOException localIOException) {
			System.err.println("Error: Can' read 'arc.sar' file.");
		}
	}

	private int readByte(RandomAccessFile file) throws IOException {
		return file.read();
	}

	private int readWord(RandomAccessFile file) throws IOException {
		int i = readByte(file) << 8;
		return i | readByte(file);
	}

	private int readLong(RandomAccessFile file) throws IOException {
		int i = readByte(file) << 24;
		i |= readByte(file) << 16;
		i |= readByte(file) << 8;
		return i | readByte(file);
	}

	private String readString(RandomAccessFile file) throws IOException {
		StringBuffer sb = new StringBuffer();
		char c;
		while ((c = (char) file.read()) != 0) {
			sb.append(c);
		}
		return sb.toString();
	}

	public SarFile(String filename) throws IOException {
		this.entry = new Hashtable<String, SarEntry>();
		this.raf = new RandomAccessFile(filename, "r");
		this.num = readWord(this.raf);
		this.top = readLong(this.raf);
		for (int k = 0; k < this.num; k++) {
			String str = readString(this.raf);
			int i = readLong(this.raf) + this.top;
			int j = readLong(this.raf);
			this.entry.put(str, new SarEntry(str, i, j));
		}
	}

	public int size() {
		return this.entry.size();
	}

	public Enumeration<SarEntry> entries() {
		return this.entry.elements();
	}

	public SarEntry getSarEntry(String key) {
		return this.entry.get(key);
	}

	public SarInputStream getInputStream(SarEntry se) throws IOException {
		return new SarInputStream(this.raf, se);
	}
}
