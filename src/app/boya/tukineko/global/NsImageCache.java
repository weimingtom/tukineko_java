package app.boya.tukineko.global;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.util.Vector;

public class NsImageCache {
	// FIXME:
	// private static final int IMAGE_CACHE_MAX = 4;

	private static Vector<NsImage> cache;
	private static MediaTracker mt;

	public static void init(Component comp) {
		cache = new Vector<NsImage>();
		mt = new MediaTracker(comp);
	}

	public static boolean set(String name) {
		NsImage img = new NsImage(name);
		File file = new File(name);
		if (file.exists() == true) {
			img.image = Toolkit.getDefaultToolkit().createImage(name);
			mt.addImage(img.image, 0);
			try {
				mt.waitForID(0);
			} catch (Exception localException) {
				System.err.println("read direct image");
			}
			mt.removeImage(img.image, 0);
		} else {
			img.image = null;
		}
		cache.addElement(img);
		return img.image != null;
	}

	public static Image get(String name) {
		NsImage img = new NsImage(name);
		int i = cache.indexOf(img);
		if (i >= 0) {
			img = cache.elementAt(i);
			cache.removeElementAt(i);
		} else {
			if (cache.size() >= 4) {
				cache.removeElementAt(0);
			}
			img.setImage(NsResource.readImage(name));
		}
		cache.addElement(img);
		return img.getImage();
	}
}
