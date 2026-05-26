package app.boya.tukineko.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class NsaInputStream extends InputStream {
	private NsaEntry ne;
	private RandomAccessFile raf;
	private int position;
	private int length;

	public NsaInputStream(RandomAccessFile raf, NsaEntry ne) throws IOException {
		this.raf = raf;
		this.ne = ne;
		this.raf.seek(this.ne.offset);
		this.position = 0;
		this.length = this.ne.length;
	}

	@Override
	public void close() {
		this.position = this.length;
	}

	@Override
	public int read() throws IOException {
		if (this.ne.type != 0) {
			throw new IOException("Not support no-raw type nsa entry.");
		}
		if (this.position < this.length) {
			this.position += 1;
			return this.raf.read();
		}
		return -1;
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		return read(bytes, 0, bytes.length);
	}

	@Override
	public int read(byte[] bytes, int offset, int len) throws IOException {
		if (this.ne.type != 0) {
			throw new IOException("Not support no-raw type nsa entry.");
		}
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
		if (this.ne.type != 0) {
			throw new IOException("Not support no-raw type nsa entry.");
		}
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
