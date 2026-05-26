package app.boya.tukineko.global;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import app.boya.tukineko.file.NsaEntry;
import app.boya.tukineko.file.NsaFile;
import app.boya.tukineko.file.SarEntry;
import app.boya.tukineko.file.SarFile;

public class NsResource {
	private static MediaTracker mt;
	private static SarFile sar;
	private static NsaFile nsa;
	private static Hashtable<String, Integer> log;

	public static void initLog(Hashtable<String, Integer> logtable) {
		log = logtable;
	}

	public static void initSar(String filename, Component comp) {
		mt = new MediaTracker(comp);
		try {
			sar = new SarFile(filename);
			nsa = null;
		} catch (IOException e) {
			System.err.println("resource: " + filename);
		}
	}

	public static void initNsa(String filename, Component comp) {
		mt = new MediaTracker(comp);
		try {
			sar = null;
			nsa = new NsaFile(filename);
		} catch (IOException e) {
			System.err.println("resource: " + filename);
		}
	}

	public static byte[] read(String key) {
		byte[] bytes = null;
		try {
			int i;
			InputStream istream;
			if (sar != null) {
				SarEntry localSarEntry;
				if ((localSarEntry = sar.getSarEntry(key)) == null) {
					System.err.println("no resource: " + key);
					return null;
				}
				i = localSarEntry.length();
				bytes = new byte[i];
				istream = sar.getInputStream(localSarEntry);
			} else {
				NsaEntry ne = nsa.getNsaEntry(key);
				if (ne == null) {
					// FIXME:
					// System.err.println("no resource: " + key);
					System.err.println("no resource: " + key);
					//
					return null;
				}
				i = ne.length();
				bytes = new byte[i];
				istream = nsa.getInputStream(ne);
			}
			istream.read(bytes, 0, i);
			istream.close();
		} catch (IOException e) {
			System.err.println("read resource");
		}
		String str = key.toUpperCase();
		if (!log.containsKey(str)) {
			log.put(str, new Integer(1));
		}
		return bytes;
	}

	public static Image readImage(String key) {
		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();
		int j = 0;
		int i = 0;
		if (key.startsWith(":") == true)
			if (key.startsWith(":a;") == true) {
				i = 1;
				j = 3;
			} else if (key.startsWith(":c;") == true) {
				j = 3;
			} else {
				System.err.println("image: " + key);
				return null;
			}
		byte[] bytes;
		if ((bytes = read(key.substring(j))) == null) {
			return null;
		}
		Image image = Toolkit.getDefaultToolkit().createImage(bytes);
		mt.addImage(image, 0);
		try {
			mt.waitForID(0);
		} catch (Exception e) {
			System.err.println("read resource image");
		}
		mt.removeImage(image, 0);
		if (i == 1) {
			return makeAlpha(image);
		}
		return image;
	}

	public static Image makeAlpha(Image image) {
		int i = image.getWidth(null);
		int j = image.getHeight(null);
		int[] pix = new int[i * j];
		try {
			new PixelGrabber(image, 0, 0, i, j, pix, 0, i).grabPixels();
		} catch (InterruptedException e) {

		}
		for (int m = 0; m < j; m++) {
			for (int k = 0; k < i >> 1; k++) {
				int n = ((pix[(k + (i >> 1) + m * i)] & 0xFF) < 64 ? 255 : 0) << 24;
				pix[(k + m * i)] = (pix[(k + m * i)] & 0xFFFFFF | n);
			}
		}
		return Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(i >> 1, j, pix, 0, i));
	}
}
