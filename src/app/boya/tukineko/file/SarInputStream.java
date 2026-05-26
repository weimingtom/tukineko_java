package app.boya.tukineko.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class SarInputStream extends InputStream {
	private RandomAccessFile raf;
	private int position;
	private int length;

	public SarInputStream(RandomAccessFile file, SarEntry se)
			throws IOException {
		this.raf = file;
		this.raf.seek(se.offset);
		this.position = 0;
		this.length = se.length;
	}

	@Override
	public void close() {
		this.position = this.length;
	}

	@Override
	public int read() throws IOException {
		if (this.position < this.length) {
			this.position += 1;
			return this.raf.read();
		}
		return -1;
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		int j = bytes.length;
		if (this.position < this.length) {
			int i;
			if (this.position + j <= this.length) {
				i = j;
			} else {
				i = this.length - this.position;
			}
			this.position += i;
			return this.raf.read(bytes, 0, i);
		}
		return -1;
	}

	@Override
	public int read(byte[] bytes, int offset, int len) throws IOException {
		if (this.position < this.length) {
			int i;
			if (this.position + len <= this.length)
				i = len;
			else {
				i = this.length - this.position;
			}
			this.position += i;
			return this.raf.read(bytes, offset, i);
		}
		return -1;
	}

	@Override
	public long skip(long len) throws IOException {
		if (this.position < this.length) {
			int i;
			if (this.position + (int) len <= this.length)
				i = (int) len;
			else {
				i = this.length - this.position;
			}
			this.position += i;
			return this.raf.skipBytes(i);
		}
		return -1L;
	}
}
