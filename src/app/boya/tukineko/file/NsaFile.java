package app.boya.tukineko.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.Hashtable;

public class NsaFile {
	private RandomAccessFile raf;
	private int num;
	private int top;
	private Hashtable<String, NsaEntry> entry;

	public static void main(String[] args) {
		try {
			NsaFile localNsaFile = new NsaFile("arc.nsa");
			Enumeration<NsaEntry> localEnumeration = localNsaFile.entries();
			System.out.println("size:" + localNsaFile.size());
			while (localEnumeration.hasMoreElements()) {
				System.out.println(localEnumeration.nextElement().toString());
			}
		} catch (IOException e) {
			System.err.println("Error: Can' read 'arc.nsa' file.");
			System.err.println(e);
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

	public NsaFile(String filename) throws IOException {
		this.entry = new Hashtable<String, NsaEntry>();
		this.raf = new RandomAccessFile(filename, "r");
		this.num = readWord(this.raf);
		this.top = readLong(this.raf);
		for (int n = 0; n < this.num; n++) {
			String str = readString(this.raf);
			int k = readByte(this.raf);
			int i = readLong(this.raf) + this.top;
			int j = readLong(this.raf);
			int m = readLong(this.raf);
			this.entry.put(str, new NsaEntry(str, i, j, k, m));
		}
	}

	public int size() {
		return this.entry.size();
	}

	public Enumeration<NsaEntry> entries() {
		return this.entry.elements();
	}

	public NsaEntry getNsaEntry(String key) {
		return this.entry.get(key);
	}

	public NsaInputStream getInputStream(NsaEntry ne) throws IOException {
		return new NsaInputStream(this.raf, ne);
	}
}
