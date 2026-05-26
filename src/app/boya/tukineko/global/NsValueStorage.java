package app.boya.tukineko.global;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NsValueStorage {
	public static void save(OutputStream paramOutputStream,
			int[] paramArrayOfInt, String[] paramArrayOfString, int paramInt1,
			int paramInt2) {
		try {
			for (int i = paramInt1; i <= paramInt2; i++) {
				paramOutputStream.write(paramArrayOfInt[i] & 0xFF);
				paramOutputStream.write(paramArrayOfInt[i] >> 8 & 0xFF);
				paramOutputStream.write(paramArrayOfInt[i] >> 16 & 0xFF);
				paramOutputStream.write(paramArrayOfInt[i] >> 24 & 0xFF);
				byte[] arrayOfByte = paramArrayOfString[i].getBytes("SJIS");
				if (arrayOfByte.length > 0)
					paramOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
				paramOutputStream.write(0);
			}
		} catch (IOException localIOException) {
			System.err.println("IOException: save");
		}
	}

	public static void load(InputStream paramInputStream,
			int[] paramArrayOfInt, String[] paramArrayOfString, int paramInt1,
			int paramInt2) {
		byte[] arrayOfByte = new byte[1024];
		try {
			for (int i = paramInt1; i <= paramInt2; i++) {
				paramArrayOfInt[i] = paramInputStream.read();
				paramArrayOfInt[i] |= paramInputStream.read() << 8;
				paramArrayOfInt[i] |= paramInputStream.read() << 16;
				paramArrayOfInt[i] |= paramInputStream.read() << 24;
				int j = 0;
				for (; j < 1024; j++) {
					if ((arrayOfByte[j] = (byte) paramInputStream.read()) == 0)
						break;
				}
				if (j == 0) {
					paramArrayOfString[i] = "";
				} else {
					paramArrayOfString[i] = new String(arrayOfByte, 0, j,
							"SJIS");
				}
			}
		} catch (IOException localIOException) {
			System.err.println("IOException: load");
		}
	}
}
