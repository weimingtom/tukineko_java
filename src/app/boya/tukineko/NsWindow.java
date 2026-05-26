package app.boya.tukineko;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import app.boya.tukineko.entry.NsButton;
import app.boya.tukineko.entry.NsColor;
import app.boya.tukineko.entry.NsData;
import app.boya.tukineko.entry.NsText;
import app.boya.tukineko.global.NsImageCache;
import app.boya.tukineko.global.NsResource;
import app.boya.tukineko.global.NsThread;
import app.boya.tukineko.global.NsTimer;
import app.boya.tukineko.global.NsValueStorage;
import app.boya.tukineko.parser.NScripter;

public class NsWindow extends Panel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private NScripter ns;
	private PopupMenu menuSys;

	// TODO:
	public NsThread thd;
	private PopupMenu menu;
	public Menu menuSave;
	public Menu menuLoad;

	private transient Image frmBuffB;
	private transient Graphics frmBuffBG;
	private transient Image frmBuffF;
	private transient Graphics frmBuffFG;
	private transient Image frmBuffR;

	private NsActionListener al;
	private transient NsTimer timer;

	private NsData ndata = new NsData();

	public NsWindow() {
//		String path = "./";
		boolean isRotate = false;
		
//		Frame frame = new Frame();
//		frame.addNotify();
		_start();
		
		this.ndata = new NsData();
//		this.ndata.path = path;
		this.ndata.rotate = isRotate;
//		this.addNotify();
		if (!this.ndata.rotate) {
			this.setSize(320, 240);
		} else {
			this.setSize(240, 320);
		}
		this.addMouseListener(this);
		this.menuSys = new PopupMenu();
		this.ndata.menuVisible = false;
		this.menuSys.add("Fade");
		this.menuSys.addSeparator();
		this.menuSys.add("Exit");
		this.menuSys.addActionListener(this);
		this.add(this.menuSys);

		// //

		//
		this.initGraph();
		//

		this.ns = new NScripter(this, this.ndata);
		//
		NsResource.initLog(this.ndata.fchk);
		NsImageCache.init(this);
		this.paintF();
		//
		this.setVisible(true);
		//
		// TODO:
		this.menu = new PopupMenu();
		//
		this.al = new NsActionListener(this.ns, this);
		this.menu.addActionListener(this.al);
		this.add(this.menu);

		//
		// this.ns.start();
		this.thd = new NsThread(this.ns);
		thd.start();
		//
		this.timer = new NsTimer();
	}
	
	private void _start() {
		setPreferredSize(new Dimension(320, 240));
		Frame frame = new Frame();
		frame.add(this);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
//				isStopped = true;
//				if (drawThread != null) {
//					try {
//						drawThread.join(1000);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
//				}
//				onExit();
				System.exit(0);	
			}
		});
//		frame.setTitle(title);	
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		this.requestFocus(); //listen for keyboard event
//		bufImage = this.createImage(canvasWidth, canvasHeight);
//		bufGraph = bufImage.getGraphics();
//		bufGraph.clearRect(0, 0, canvasWidth, canvasHeight);
//		drawThread = new Thread(this);
//		drawThread.start();			
	}

	/*
	 * public void menuVisibleClear() { this.menuVisible = false; }
	 */

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (!this.ndata.menuVisible) {
			if (this.ndata.textVisible
					&& ((!this.ndata.rotate && event.getY() < 16 && event
							.getX() < 32))
					|| (this.ndata.rotate && event.getX() < 16 && event.getY() > 288)) {
				this.menu.show(event.getComponent(), 0, 0);
				this.ndata.menuVisible = true;
			} else if ((!this.ndata.rotate && event.getY() > 224 && event
					.getX() < 32)
					|| (this.ndata.rotate && event.getX() > 224 && event.getY() > 288)) {
				this.menuSys.show(event.getComponent(), 0, 0);
				this.ndata.menuVisible = true;
			} else if (!this.ndata.rotate) {
				this.ns.click(event.getX() * 2, event.getY() * 2);
			} else {
				this.ns.click((319 - event.getY()) * 2, event.getX() * 2);
			}
		} else {
			this.ndata.menuVisible = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String str = event.getActionCommand();
		if ("Exit".equals(str)) {
			this.ns.storageState = 3;
			this.paintF();
			this.ns.exitFlag = true;
		} else if ("Fade".equals(str)) {
			this.fadeToggle();
			this.ndata.menuVisible = false;
		}
	}

	private void fadeToggle() {
		if (!this.ndata.fadeMode) {
			this.ndata.fadeMode = true;
			this.ndata.fadeFlag = false;
		} else {
			this.ndata.fadeMode = false;
		}
		this.paintB();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		if (this.frmBuffF != null) {
			if (!this.ndata.rotate)
				g.drawImage(this.frmBuffF, 0, 0, this);
			else {
				g.drawImage(this.frmBuffR, 0, 0, this);
			}
		}
		if (this.ndata.error != null) {
			g.setColor(Color.black);
			g.drawString(this.ndata.error, 17, 17);
			g.setColor(Color.white);
			g.drawString(this.ndata.error, 16, 16);
		}
	}

	/*
	 * public static void error(String str) { error = str; }
	 * 
	 * public static boolean hasError() { return error != null; }
	 * 
	 * public static String getError() { return error; }
	 */

	private void makeFileMenu(Menu paramMenu, int savenumber, String path,
			String savenameTitle) {
		if (paramMenu != null)
			paramMenu.removeAll();
		for (int i = 0; i < savenumber; i++) {
			File localFile = new File(path + "SAVE" + Integer.toString(i + 1)
					+ ".DAT");
			if (localFile.exists() == true) {
				Date localDate = new Date(localFile.lastModified());
				// FIXME:
				/*
				 * int j = localDate.getMonth() + 1; int k =
				 * localDate.getDate(); int m = localDate.getHours(); int n =
				 * localDate.getMinutes();
				 */
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(localDate);
				int j = calendar.get(Calendar.MONTH) + 1;
				int k = calendar.get(Calendar.DAY_OF_MONTH);
				int m = calendar.get(Calendar.HOUR);
				int n = calendar.get(Calendar.MINUTE);
				paramMenu.add(savenameTitle + (i < 9 ? "0" : "")
						+ Integer.toString(i + 1) + " " + (j < 10 ? "0" : "")
						+ Integer.toString(j) + "/" + (k < 10 ? "0" : "")
						+ Integer.toString(k) + " " + (m < 10 ? "0" : "")
						+ Integer.toString(m) + ":" + (n < 10 ? "0" : "")
						+ Integer.toString(n));
			} else {
				paramMenu.add(savenameTitle + (i < 9 ? "0" : "")
						+ Integer.toString(i + 1) + " " + "--/-- --:--");
			}
		}
	}

	public void makemenu(int savenumber, String path, String savenameTitle) {
		this.makeFileMenu(this.menuSave, savenumber, path, savenameTitle);
		this.makeFileMenu(this.menuLoad, savenumber, path, savenameTitle);
	}

	public void createMenuSave(String str) {
		this.menuSave = new Menu(str);
		this.menuSave.addActionListener(this.al);
		this.menu.add(this.menuSave);
	}

	public void createMenuLoad(String str) {
		this.menuLoad = new Menu(str);
		this.menuLoad.addActionListener(this.al);
		this.menu.add(this.menuLoad);
	}

	private static Image createImage(int w, int h, int[] pix, int off, int scan) {
		return Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(w, h, pix, off, scan));
	}

	private static void grabPixels(Image img, int x, int y, int w, int h,
			int[] pix, int off, int scansize) {
		try {
			new PixelGrabber(img, x, y, w, h, pix, off, scansize).grabPixels();
		} catch (InterruptedException e) {

		}
	}

	private static void drawString(Graphics paramGraphics, String paramString,
			int paramInt1, int paramInt2, int paramInt3) {
		if (paramString == null) {
			return;
		}
		FontMetrics localFontMetrics = paramGraphics.getFontMetrics();
		for (int i = 0; i < paramString.length(); i++) {
			String str = paramString.substring(i, i + 1);
			int j = localFontMetrics.stringWidth(str);
			paramGraphics.drawString(str,
					(paramInt1 + paramInt3 * i + (paramInt3 - j) / 2) / 2,
					paramInt2 / 2);
		}
	}

	public void paintB() {
		Image localImage;
		if (this.ndata.bgColor != null) {
			this.frmBuffBG.setColor(getColor(this.ndata.bgColor));
			this.frmBuffBG.fillRect(0, 0, 320, 240);
		} else if (this.ndata.bgImage != null) {
			if (this.ndata.quakex != 0) {
				this.frmBuffBG.setColor(Color.black);
				this.frmBuffBG.fillRect((this.ndata.quakex & 0x1) == 0 ? 0
						: 304, 0, 16, 240);
			}
			if (this.ndata.quakey != 0) {
				this.frmBuffBG.setColor(Color.black);
				this.frmBuffBG.fillRect(0, (this.ndata.quakey & 0x1) == 0 ? 0
						: 224, 320, 16);
			}

			if ((localImage = NsImageCache.get(this.ndata.bgImage)) != null) {
				this.frmBuffBG.drawImage(localImage, this.ndata.quakex == 0 ? 0
						: 16 - (this.ndata.quakex & 0x1) * 32,
						this.ndata.quakey == 0 ? 0
								: 16 - (this.ndata.quakey & 0x1) * 32, this);
			} else {
				this.frmBuffBG.setColor(Color.black);
				this.frmBuffBG.fillRect(0, 0, 320, 240);
			}
		}
		if ((this.ndata.btnVisible == true) && (this.ndata.btnSel != -1)) {
			NsButton localNsButton = this.ndata.btn
					.elementAt(this.ndata.btnSel);
			this.frmBuffBG.setClip(localNsButton.x >> 1, localNsButton.y >> 1,
					localNsButton.width >> 1, localNsButton.height >> 1);
			if ((localImage = NsImageCache.get(this.ndata.btnImage)) != null) {
				this.frmBuffBG.drawImage(localImage, (localNsButton.x >> 1)
						- (localNsButton.u >> 1), (localNsButton.y >> 1)
						- (localNsButton.v >> 1), this);
			}
			this.frmBuffBG.setClip(0, 0, 320, 240);
		}
		int j = 0;
		for (; j < 3; j++) {
			if (this.ndata.shell[j] != null) {
				int i;
				switch (j) {
				case 0:
					i = 80 - (this.ndata.shell[j].width >> 1);
					break;
				default:
					i = 160 - (this.ndata.shell[j].width >> 1);
					break;
				case 2:
					i = 240 - (this.ndata.shell[j].width >> 1);
				}
				if ((localImage = NsImageCache.get(this.ndata.shell[j].image)) != null) {
					this.frmBuffBG.drawImage(localImage, i,
							240 - this.ndata.shell[j].height, this);
				}
			}
		}
		for (j = 0; j < 50; j++) {
			if ((this.ndata.sprite[j].visible != true)
					|| ((localImage = NsImageCache
							.get(this.ndata.sprite[j].image)) == null)) {
				continue;
			}
			this.frmBuffBG.drawImage(localImage, this.ndata.sprite[j].x >> 1,
					this.ndata.sprite[j].y >> 1, this);
		}
		if ((this.ndata.fadeMode == true) && (this.ndata.textVisible == true)
				&& (this.ndata.text != null) && (this.ndata.text.getY() != 0)) {
			NsWindow.grabPixels(this.frmBuffB, 0, 0, 320, 240,
					this.ndata.fadeImg, 0, 320);
			for (j = 0; j < 76800; j++) {
				this.ndata.fadeImg[j] = ((this.ndata.fadeImg[j] & 0xFEFEFE) >> 1 | 0xFF000000);
			}
			localImage = NsWindow.createImage(320, 240, this.ndata.fadeImg, 0,
					320);
			this.frmBuffBG.drawImage(localImage, 0, 0, this);
			this.ndata.fadeFlag = true;
		} else {
			this.ndata.fadeFlag = false;
		}
		paintF();
	}

	/**
	 * fade
	 */
	public void paintF() {
		if (this.ns.storageState != 0) {
			this.frmBuffFG.setColor(Color.black);
			this.frmBuffFG.fillRect(0, 0, 320, 240);
			this.frmBuffFG.setColor(Color.white);
			switch (this.ns.storageState) {
			case -2:
				Image localImage;
				if ((localImage = NsImageCache
						.get(this.ns.path + "MOON.PNG")) != null) {
					this.frmBuffFG.drawImage(localImage, 5, 70, this);
				} else {
					this.frmBuffFG.drawRect(5, 70, 100, 100);
				}
				if ((localImage = NsImageCache
						.get(this.ns.path + "PLUS.PNG")) != null) {
					this.frmBuffFG.drawImage(localImage, 110, 70, this);
				} else {
					this.frmBuffFG.drawRect(110, 70, 100, 100);
				}
				if ((localImage = NsImageCache.get(this.ns.path
						+ "KAGETU.PNG")) != null)
					this.frmBuffFG.drawImage(localImage, 215, 70, this);
				else {
					this.frmBuffFG.drawRect(215, 70, 100, 100);
				}
				break;
			case -1:
				this.frmBuffFG.drawString("初期化中", 140, 100);
				break;
			case 1:
				this.frmBuffFG.drawString("保存中", 140, 100);
				break;
			case 2:
				this.frmBuffFG.drawString("読出中", 140, 100);
				break;
			case 3:
				this.frmBuffFG.drawString("終了中", 140, 100);
			case 0:
			}
		} else {
			this.frmBuffFG.drawImage(this.frmBuffB, 0, 0, this);
			if ((this.ndata.text != null) && (this.ndata.textVisible == true)) {
				int i = this.ndata.twinLx;
				int j = this.ndata.twinLy + this.ndata.twinFh;
				for (int k = 0; k < this.ndata.text.getY(); k++) {
					if (this.ndata.twinShadow == true) {
						this.frmBuffFG.setColor(Color.black);
						NsWindow.drawString(this.frmBuffFG,
								this.ndata.text.getMess(k), i + 2, j + 2,
								this.ndata.twinFw + this.ndata.twinSw);
						if (this.ndata.twinBold == true) {
							NsWindow.drawString(this.frmBuffFG,
									this.ndata.text.getMess(k), i + 4, j + 2,
									this.ndata.twinFw + this.ndata.twinSw);
						}
					}
					if (this.ndata.text.getAttr(k) == true) {
						this.frmBuffFG.setColor(getColor(this.ndata.text
								.getColor(k)));
					} else {
						this.frmBuffFG.setColor(new Color(144, 144, 144));
					}
					NsWindow.drawString(this.frmBuffFG,
							this.ndata.text.getMess(k), i, j, this.ndata.twinFw
									+ this.ndata.twinSw);
					if (this.ndata.twinBold == true) {
						NsWindow.drawString(this.frmBuffFG,
								this.ndata.text.getMess(k), i + 2, j,
								this.ndata.twinFw + this.ndata.twinSw);
					}
					j += this.ndata.twinFh + this.ndata.twinSh;
				}
			}
		}
		this.frmBuffFG.setColor(Color.black);
		this.frmBuffFG.drawString(
				Integer.toString((int) Runtime.getRuntime().freeMemory())
						+ ":"
						+ Integer.toString((int) Runtime.getRuntime()
								.totalMemory()), 200, 239);
		this.frmBuffFG.setColor(Color.white);
		this.frmBuffFG.drawString(
				Integer.toString((int) Runtime.getRuntime().freeMemory())
						+ ":"
						+ Integer.toString((int) Runtime.getRuntime()
								.totalMemory()), 201, 238);
		repaintWin();
	}

	private void repaintWin() {
		if (this.ndata.rotate == true) {
			int[] arrayOfInt1 = new int[76800];
			int[] arrayOfInt2 = new int[76800];
			NsWindow.grabPixels(this.frmBuffF, 0, 0, 320, 240, arrayOfInt1, 0,
					320);
			int k = 0;
			for (int i = 0; i < 320; i++) {
				for (int j = 0; j < 240; j++) {
					arrayOfInt2[(k++)] = arrayOfInt1[(319 - i + j * 320)];
				}
			}
			this.frmBuffR = NsWindow.createImage(240, 320, arrayOfInt2, 0, 240);
		}
		this.repaint();
	}

	public void blt(int j, int k, int m, int n, int i1, int i2) {
		this.frmBuffBG.setClip(j >> 1, k >> 1, m >> 1, n >> 1);
		Image localImage;
		if ((localImage = NsImageCache.get(this.ndata.btnImage)) != null) {
			this.frmBuffBG.drawImage(localImage, (j >> 1) - (i1 >> 1), (k >> 1)
					- (i2 >> 1), this);
		}
		this.frmBuffBG.setClip(0, 0, 320, 240);
	}

	private void initGraph() {
		this.frmBuffB = this.createImage(320, 240);
		this.frmBuffBG = this.frmBuffB.getGraphics();
		this.frmBuffF = this.createImage(320, 240);
		this.frmBuffFG = this.frmBuffF.getGraphics();
		this.frmBuffBG.setColor(Color.black);
		this.frmBuffBG.fillRect(0, 0, 320, 240);
	}

	public int putMess(NsText nt, String mess, NsColor color,
			boolean paramBoolean1, boolean paramBoolean2) {
		String str;
		if (!paramBoolean2) {
			str = mess;
		} else {
			if (nt.curY > 0) {
				nt.curY -= 1;
			}
			str = nt.mess[nt.curY] + mess;
			nt.mess[nt.curY] = "";
		}
		int j = str.length();
		int i = 0;
		if (j < 2) {
			i = 1;
		} else {
			i = (j - 1) / nt.width + 1;
		}
		if (j == 0) {
			nt.mess[nt.curY] = "";
			nt.color[nt.curY] = color;
			nt.attr[(nt.curY++)] = paramBoolean1;
			return 1;
		}
		i = 0;
		for (int k = 0; k < str.length(); k += nt.width) {
			if (nt.curY >= nt.height - 1) {
				nt.mess[nt.curY - 1] += "▼";
				if (!this.ndata.fadeFlag) {
					this.paintB();
				} else {
					this.paintF();
				}
				this.newpage(true);
			}
			if (k + nt.width < str.length()) {
				int m = 0;
				for (; m < 1 && k + nt.width + m < j; m++) {
					if ("、。」▼▽".indexOf(str.charAt(k + nt.width + m)) == -1) {
						break;
					}
				}
				nt.mess[nt.curY] = this.ndata.evalStr(str.substring(k, k
						+ nt.width + m));
				k += m;
			} else {
				nt.mess[nt.curY] = this.ndata.evalStr(str.substring(k));
			}
			nt.color[nt.curY] = color;
			nt.attr[(nt.curY++)] = paramBoolean1;
			i++;
		}
		return i;
	}

	public void timerExit() {
		this.timer.exit();
	}

	public void timerClear() {
		this.timer.clear();
	}

	public int timerRead() {
		return this.timer.read();
	}

	public void initSar(String filename) {
		NsResource.initSar(filename, this);
	}

	public void initNsa(String filename) {
		NsResource.initNsa(filename, this);
	}

	public boolean setImageCache(String name) {
		return NsImageCache.set(name);
	}

	public void loadValueStorage(InputStream paramInputStream,
			int[] paramArrayOfInt, String[] paramArrayOfString, int paramInt1,
			int paramInt2) {
		NsValueStorage.load(paramInputStream, paramArrayOfInt,
				paramArrayOfString, paramInt1, paramInt2);
	}

	public void saveValueStorage(OutputStream paramOutputStream,
			int[] paramArrayOfInt, String[] paramArrayOfString, int paramInt1,
			int paramInt2) {
		NsValueStorage.save(paramOutputStream, paramArrayOfInt,
				paramArrayOfString, paramInt1, paramInt2);
	}

	/*
	 * Image localImage = NsImageCache.get(image); if (localImage != null) {
	 * this.width = localImage.getWidth(null); this.height =
	 * localImage.getHeight(null); } else { this.width = 0; this.height = 0; }
	 */
	public int getImageWidth(String image) {
		Image localImage = NsImageCache.get(image);
		if (localImage != null) {
			return localImage.getWidth(null);
		} else {
			return 0;
		}
	}

	public int getImageHeight(String image) {
		Image localImage = NsImageCache.get(image);
		if (localImage != null) {
			return localImage.getHeight(null);
		} else {
			return 0;
		}
	}

	private static Color getColor(NsColor color) {
		return new Color(color.getRGB(), true);
	}

	/*
	 * private static NsColor toNsColor(Color color) { return new NsColor(
	 * color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); }
	 */
	public void popupMenuAdd(String str) {
		this.menu.add(str);
	}

	public void wait(int paramInt, boolean paramBoolean) {
		if (!paramBoolean) {
			try {
				Thread.sleep(paramInt);
			} catch (Exception localException1) {
			}
		} else {
			this.ndata.click = false;
			if (paramInt == 0) {
				do {
					try {
						Thread.sleep(100L);
					} catch (Exception localException2) {
					}
					if (this.ndata.click) {
						break;
					}
				} while (this.ns.storageState == 0);
			} else {
				int i = paramInt;
				while ((this.ndata.click != true)
						&& (this.ns.storageState == 0)) {
					if (i > 100) {
						try {
							Thread.sleep(100L);
						} catch (Exception localException3) {
						}
						i -= 100;
						continue;
					}
					try {
						Thread.sleep(i);
					} catch (Exception localException4) {
					}
				}
			}
		}
	}

	public void newpage(boolean paramBoolean) {
		this.ndata.selectState = 1;
		if (paramBoolean == true) {
			this.wait(this.ndata.autoclick, true);
		}
		if (this.ns.storageState == 0) {
			this.ndata.select.removeAllElements();
			this.ndata.text.cls();
		}
	}
}
