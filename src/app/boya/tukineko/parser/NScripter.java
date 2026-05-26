package app.boya.tukineko.parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import app.boya.tukineko.NsWindow;
import app.boya.tukineko.entry.NsButton;
import app.boya.tukineko.entry.NsColor;
import app.boya.tukineko.entry.NsData;
import app.boya.tukineko.entry.NsSelect;
import app.boya.tukineko.entry.NsShell;
import app.boya.tukineko.entry.NsText;

import com.iteye.weimingtom.nsdumper.parser.NSParser;

public class NScripter extends NSParser implements Serializable {
	private static final long serialVersionUID = 1L;

	private static NScripter instance;
	public static NScripter getInstance() {
		return instance;
	}
	
	// --------------------------------

	public transient NsWindow tn;
	public NsData nd;

	// -------------------------
	//private transient RandomAccessFile raf;
//	private transient byte[] readBuff;
//	private transient int readTop;
//	private transient int readEnd;
//	private transient int readPos;
	//
//	private String line;
	//to parser
//	public String lineRest;
//	private boolean lineCont;
//	private Vector<String> args;

	// -------------------------

	public NScripter(NsWindow paramtukineko, NsData nd) {
		super("./");
		instance = this;
		
		this.tn = paramtukineko;
		this.nd = nd;
		
		//
		this.readBuff = new byte[4096];
		this.readTop = 0;
		this.readEnd = 0;
		this.readPos = 0;
		//
		this.line = null;
		this.lineRest = null;
		this.lineCont = false;
		//
//		this.args = new Vector<String>();
		this.argCont = false;
		// FIXME:
		// this.tn.initLog(this.nd.fchk);
		// this.tn.initImageCache();
		// this.tn.paintF();
	}

	private void destructor() {
		if (this.nd.globalon == true) {
			saveGlobalData();
		}
		if (this.nd.filelog == true) {
			saveLogData(this.path + "NSCRFLOG.DAT", this.nd.fchk);
		}
		if (this.nd.labellog == true) {
			saveLogData(this.path + "NSCRLLOG.DAT", this.nd.lchk);
		}
		if (this.raf != null)
			try {
				this.raf.close();
			} catch (IOException e) {
			}
		this.tn.timerExit();
	}

	/*
	 * public void fadeToggle() { if (!this.nd.fadeMode) { this.nd.fadeMode =
	 * true; this.nd.fadeFlag = false; } else { this.nd.fadeMode = false; }
	 * this.tn.paintB(); }
	 */
	public void run() {
		initParser();
		boolean bool1 = this.tn.setImageCache(this.path + "MOON.PNG");
		boolean bool2 = this.tn.setImageCache(this.path + "PLUS.PNG");
		boolean bool3 = this.tn.setImageCache(this.path + "KAGETU.PNG");
		this.storageState = -2;
		this.tn.paintF();
		while (!this.exitFlag) {
			if (this.nd.click == true) {
				this.nd.click = false;
				if ((this.nd.clickY >= 140) && (this.nd.clickY < 340)) {
					if ((bool1 == true) && (this.nd.clickX >= 10)
							&& (this.nd.clickX < 210)) {
						this.path += "MOON" + File.separator;
						break;
					}
					if ((bool2 == true) && (this.nd.clickX >= 220)
							&& (this.nd.clickX < 420)) {
						this.path += "PLUS" + File.separator;
						break;
					}
					if ((bool3 == true) && (this.nd.clickX >= 430)
							&& (this.nd.clickX < 630)) {
						this.path += "KAGETU" + File.separator;
						break;
					}
				}
			}
			try {
				Thread.sleep(100L);
			} catch (Exception localException1) {
			}
		}
		if (this.exitFlag == true) {
			System.exit(0);
		}
		this.storageState = -1;
		this.tn.paintF();
		try {
			this.raf = new RandomAccessFile(this.path + "NSCRIPT.DAT", "r");
			File localFile = new File(this.path + "LABEL.DAT");
			int j;
			byte[] arrayOfByte;
			if (!localFile.exists()) {
				FileOutputStream localObject = new FileOutputStream(localFile);
				while ((this.line = readLine()) != null) {
					int i = 0;
					for (; i < this.line.length(); i++) {
						if (" \t".indexOf(this.line.charAt(i)) == -1) {
							break;
						}
					}
					if (i > 0) {
						this.line = this.line.substring(i);
					}
					if (this.line.startsWith("*") == true) {
						j = getFilePointer();
						this.nd.label.put(this.line.substring(1),
								new Integer(j));
						arrayOfByte = this.line.substring(1).getBytes();
						localObject.write(arrayOfByte.length);
						localObject.write(arrayOfByte, 0, arrayOfByte.length);
						localObject.write(j & 0xFF);
						localObject.write(j >> 8 & 0xFF);
						localObject.write(j >> 16 & 0xFF);
						localObject.write(j >> 24 & 0xFF);
					}
				}
				localObject.close();
			} else {
				FileInputStream localObject = new FileInputStream(localFile);
				arrayOfByte = new byte[64];
				while ((j = ((FileInputStream) localObject).read()) >= 0) {
					((FileInputStream) localObject).read(arrayOfByte, 0, j);
					int k = ((FileInputStream) localObject).read();
					k |= ((FileInputStream) localObject).read() << 8;
					k |= ((FileInputStream) localObject).read() << 16;
					k |= ((FileInputStream) localObject).read() << 24;
					this.nd.label.put(new String(arrayOfByte, 0, j, "SJIS"),
							new Integer(k));
				}
				localObject.close();
			}
		} catch (IOException e) {
			System.out.println("Error: LABEL.DAT" + e);
			System.exit(1);
		}
		this.storageState = 0;
		//FIXME:run
		try {
			gotoLabel("*define");
			this.exitFlag = false;
			while (!this.exitFlag) {
				switch (exec()) {
				case 1:
					saveLocalData();
					break;
				case 2:
					loadLocalData();
				}
				if (hasError()) {
					this.tn.repaint();
					// FIXME:
					// System.err.println("Error: " + tukineko.getError());
					while (true) {
						try {
							Thread.sleep(1000L);
							continue;
						} catch (Exception e) {
						}
					}
				}
			}
		} catch (IOException e) {
			error("Error: Read Script");
		}
		destructor();
		System.exit(0);
	}

	public void click(int paramInt1, int paramInt2) {
		if (this.nd.textVisible == true) {
			this.nd.click = true;
			this.nd.clickX = paramInt1;
			this.nd.clickY = paramInt2;
		} else {
			this.nd.textVisible = true;
			this.tn.paintB();
		}
	}

	public void loadGlobalData() {
		try {
			File localFile = new File(this.path + "GLOVAL.SAV");
			if (localFile.exists() == true) {
				int i;
				byte[] arrayOfByte = new byte[i = (int) localFile.length()];
				FileInputStream localFileInputStream = new FileInputStream(
						localFile);
				localFileInputStream.read(arrayOfByte, 0, i);
				localFileInputStream.close();

				ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(
						arrayOfByte);
				this.tn.loadValueStorage(localByteArrayInputStream,
						this.nd.valueNum, this.nd.valueStr, 200, 4095);
				localByteArrayInputStream.close();
			}
		} catch (IOException localIOException) {
			error("load-gloval: IOException");
		}
	}

	private void saveGlobalData() {
		try {
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			this.tn.saveValueStorage(localByteArrayOutputStream,
					this.nd.valueNum, this.nd.valueStr, 200, 4095);
			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			localByteArrayOutputStream.close();

			FileOutputStream localFileOutputStream = new FileOutputStream(
					this.path + "GLOVAL.SAV");
			localFileOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
			localFileOutputStream.close();
		} catch (IOException localIOException) {
			error("save-global: IOException");
		}
	}

	public static void loadLogData(String paramString,
			Hashtable<String, Integer> paramHashtable) {
		byte[] arrayOfByte = new byte[1024];
		try {
			FileInputStream localFileInputStream = new FileInputStream(
					paramString);
			int i = 0;
			int j;
			while ((j = localFileInputStream.read()) != 10) {
				i = i * 10 + (j - 48);
			}
			for (int k = 0; k < i; k++) {
				if (localFileInputStream.read() != 34) {
					System.err.println("error: read " + paramString);
					break;
				}
				int m = 0;
				for (; (j = localFileInputStream.read()) != 34; m++) {
					j ^= 132;
					arrayOfByte[m] = (j < 128 ? (byte) j : (byte) (j - 256));
				}
				paramHashtable.put(new String(arrayOfByte, 0, m),
						new Integer(1));
			}
			localFileInputStream.close();
		} catch (Exception localException) {
			System.err.println("error: read " + paramString);
		}
	}

	private static void saveLogData(String paramString,
			Hashtable<String, Integer> paramHashtable) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					paramString);
			localFileOutputStream.write(Integer.toString(paramHashtable.size())
					.getBytes());
			localFileOutputStream.write(10);
			Enumeration<String> localEnumeration = paramHashtable.keys();
			while (localEnumeration.hasMoreElements() == true) {
				byte[] arrayOfByte = ((String) localEnumeration.nextElement())
						.getBytes();
				for (int i = 0; i < arrayOfByte.length; i++) {
					arrayOfByte[i] ^= 0x84;
				}
				localFileOutputStream.write(34);
				localFileOutputStream.write(arrayOfByte);
				localFileOutputStream.write(34);
			}
			localFileOutputStream.close();
		} catch (Exception localException) {
			System.err.println("error: write " + paramString);
		}
	}

	public void loadLocalData(String paramString) {
		this.tn.paintF();
		try {
			File localFile = new File(this.path + paramString);
			if (!localFile.exists()) {
				this.storageState = 0;
				this.tn.paintB();
				continueSelect();
				return;
			}
			int j;
			byte[] arrayOfByte = new byte[j = (int) localFile.length()];
			FileInputStream localFileInputStream = new FileInputStream(
					localFile);
			localFileInputStream.read(arrayOfByte, 0, j);
			localFileInputStream.close();

			ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(
					arrayOfByte);

			int i = localByteArrayInputStream.read();
			i |= localByteArrayInputStream.read() << 8;
			i |= localByteArrayInputStream.read() << 16;
			i |= localByteArrayInputStream.read() << 24;
			setFilePointer(i);

			tn.loadValueStorage(localByteArrayInputStream, this.nd.valueNum,
					this.nd.valueStr, 0, 199);

			ObjectInputStream localObjectInputStream = new ObjectInputStream(
					localByteArrayInputStream);
			NScripter localNScripter = (NScripter) localObjectInputStream
					.readObject();
			localObjectInputStream.close();
			localByteArrayInputStream.close();

			this.nd.selectState = localNScripter.nd.selectState;

			this.line = newString(localNScripter.line);
			this.lineRest = newString(localNScripter.lineRest);
			this.lineCont = localNScripter.lineCont;

			this.nd.text = new NsText(localNScripter.nd.text.width,
					localNScripter.nd.text.height);
			int k = 0;
			for (; k < localNScripter.nd.text.height; k++) {
				this.nd.text.mess[k] = newString(localNScripter.nd.text.mess[k]);
				this.nd.text.color[k] = localNScripter.nd.text.color[k];
				this.nd.text.attr[k] = localNScripter.nd.text.attr[k];
			}
			this.nd.text.curX = localNScripter.nd.text.curX;
			this.nd.text.curY = localNScripter.nd.text.curY;

			this.nd.textVisible = localNScripter.nd.textVisible;

			for (k = 0; k < 8; k++) {
				this.nd.gosub[k].retpos = localNScripter.nd.gosub[k].retpos;
				this.nd.gosub[k].rest = newString(localNScripter.nd.gosub[k].rest);
			}
			this.nd.gosubPos = localNScripter.nd.gosubPos;

			this.nd.twinLx = localNScripter.nd.twinLx;
			this.nd.twinLy = localNScripter.nd.twinLy;
			this.nd.twinLw = localNScripter.nd.twinLw;
			this.nd.twinLh = localNScripter.nd.twinLh;
			this.nd.twinFw = localNScripter.nd.twinFw;
			this.nd.twinFh = localNScripter.nd.twinFh;
			this.nd.twinSw = localNScripter.nd.twinSw;
			this.nd.twinSh = localNScripter.nd.twinSh;
			this.nd.twinSpeed = localNScripter.nd.twinSpeed;
			this.nd.twinHx = localNScripter.nd.twinHx;
			this.nd.twinHy = localNScripter.nd.twinHy;
			this.nd.twinEx = localNScripter.nd.twinEx;
			this.nd.twinEy = localNScripter.nd.twinEy;
			this.nd.twinBold = localNScripter.nd.twinBold;
			this.nd.twinShadow = localNScripter.nd.twinShadow;
			this.nd.twinColor = newColor(localNScripter.nd.twinColor);
			this.nd.twinImage = newString(localNScripter.nd.twinImage);

			this.nd.bgImage = newString(localNScripter.nd.bgImage);
			this.nd.bgColor = newColor(localNScripter.nd.bgColor);
			this.nd.bgEffect = localNScripter.nd.bgEffect;
			this.nd.autoclick = localNScripter.nd.autoclick;

			for (k = 0; k < 3; k++) {
				if (localNScripter.nd.shell[k] == null) {
					this.nd.shell[k] = null;
				} else {
					this.nd.shell[k] = new NsShell(
							localNScripter.nd.shell[k].image,
							localNScripter.nd.shell[k].effect,
							localNScripter.nd.shell[k].width,
							localNScripter.nd.shell[k].height);
				}
			}

			for (k = 0; k < 50; k++) {
				this.nd.sprite[k].image = newString(localNScripter.nd.sprite[k].image);
				this.nd.sprite[k].x = localNScripter.nd.sprite[k].x;
				this.nd.sprite[k].y = localNScripter.nd.sprite[k].y;
				this.nd.sprite[k].alpha = localNScripter.nd.sprite[k].alpha;
				this.nd.sprite[k].visible = localNScripter.nd.sprite[k].visible;
			}

			this.nd.quakex = localNScripter.nd.quakex;
			this.nd.quakey = localNScripter.nd.quakey;

			this.nd.select.removeAllElements();
			// Object localObject;
			for (k = 0; k < localNScripter.nd.select.size(); k++) {
				NsSelect localObject = localNScripter.nd.select.elementAt(k);
				this.nd.select.addElement(new NsSelect(localObject.message,
						localObject.label, localObject.y, localObject.height,
						localObject.subrutine, localObject.selected));
			}

			this.nd.textSel = localNScripter.nd.textSel;
			this.nd.selSkipFlag = localNScripter.nd.selSkipFlag;
			this.nd.selnum = localNScripter.nd.selnum;
			this.nd.textcolor = localNScripter.nd.textcolor;

			this.nd.btnImage = newString(localNScripter.nd.btnImage);

			this.nd.btn.removeAllElements();
			for (k = 0; k < localNScripter.nd.btn.size(); k++) {
				NsButton localObject = localNScripter.nd.btn.elementAt(k);
				this.nd.btn.addElement(new NsButton(localObject.no,
						localObject.x, localObject.y, localObject.width,
						localObject.height, localObject.u, localObject.v));
			}
			this.nd.btnSel = localNScripter.nd.btnSel;
			this.nd.btnVisible = localNScripter.nd.btnVisible;

			for (k = 0; k < 100; k++) {
				this.nd.history[k] = localNScripter.nd.history[k];
			}
			this.nd.historyPos = localNScripter.nd.historyPos;
			this.nd.historyCount = localNScripter.nd.historyCount;
			this.nd.jumpBack = localNScripter.nd.jumpBack;

			this.args.clear();
			for (k = 0; k < localNScripter.args.size(); k++) {
				this.args.add(newString(localNScripter.args.get(k)));
			}
			this.argCont = localNScripter.argCont;
		} catch (Exception localException) {
			System.err.println(localException);
		}
		this.storageState = 0;
		this.tn.paintB();
	}

	public void saveLocalData(String paramString) {
		this.tn.paintF();
		if (this.nd.globalon == true) {
			saveGlobalData();
		}
		if (this.nd.filelog == true) {
			saveLogData(this.path + "NSCRFLOG.DAT", this.nd.fchk);
		}
		if (this.nd.labellog == true) {
			saveLogData(this.path + "NSCRLLOG.DAT", this.nd.lchk);
		}
		try {
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();

			int i = getFilePointer();
			localByteArrayOutputStream.write(i & 0xFF);
			localByteArrayOutputStream.write(i >> 8 & 0xFF);
			localByteArrayOutputStream.write(i >> 16 & 0xFF);
			localByteArrayOutputStream.write(i >> 24 & 0xFF);

			tn.saveValueStorage(localByteArrayOutputStream, this.nd.valueNum,
					this.nd.valueStr, 0, 199);

			ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(
					localByteArrayOutputStream);
			localObjectOutputStream.writeObject(this);
			localObjectOutputStream.flush();
			localObjectOutputStream.close();

			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			localByteArrayOutputStream.close();

			FileOutputStream localFileOutputStream = new FileOutputStream(
					this.path + paramString);
			localFileOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
			localFileOutputStream.close();
		} catch (Exception localException) {
			System.err.println(localException);
		}
		this.storageState = 0;
		this.tn.paintB();
	}

	private void loadLocalData() {
		loadLocalData("SAVE" + Integer.toString(this.nd.storageNo) + ".DAT");
		continueSelect();
	}

	private void saveLocalData() {
		saveLocalData("SAVE" + Integer.toString(this.nd.storageNo) + ".DAT");
		tn.makemenu(this.nd.savenumber, this.path, this.nd.savenameTitle);
		continueSelect();
	}

	public void save(String str) {
		if (this.nd.rmenu[2] != null) {
			this.storageState = 1;
			this.nd.storageNo = Integer.parseInt(str.substring(
					this.nd.savenameTitle.length(),
					this.nd.savenameTitle.length() + 2));
			this.nd.menuVisible = false;
		}
	}

	public void load(String str) {
		if (this.nd.rmenu[3] != null) {
			this.storageState = 2;
			this.nd.storageNo = Integer.parseInt(str.substring(
					this.nd.savenameTitle.length(),
					this.nd.savenameTitle.length() + 2));
			this.nd.menuVisible = false;
		}
	}

	// FIXME:???
	public void menu3(String str) {
		if ((this.nd.rmenu[5] != null) && str.equals(this.nd.rmenu[5])) {
			this.nd.textVisible = false;
			this.tn.paintB();
			this.nd.menuVisible = false;
		}
	}

	//parser
	@Override
	public void gotoLabel(String paramString) throws IOException {
		if (!paramString.startsWith("*")) {
			error("Error Label:" + paramString);
			return;
		}
		Integer localInteger;
		if ((localInteger = (Integer) this.nd.label.get(paramString
				.substring(1))) == null) {
			error("Error Label:" + paramString);
			return;
		}
		setFilePointer(localInteger.intValue());
		this.nd.historyPos = 0;
		this.nd.historyCount = 0;
		this.lineRest = null;
	}

	public void backHistory(int paramInt) throws IOException {
		if (this.nd.historyPos >= paramInt + 1) {
			this.nd.historyPos -= paramInt + 1;
		} else {
			this.nd.historyPos = (this.nd.historyPos - (paramInt + 1) + 100);
		}
		setFilePointer(this.nd.history[this.nd.historyPos]);
		this.nd.historyPos = 0;
		this.nd.historyCount = 0;
		this.lineRest = null;
	}

	@Override
	protected void addHistory() throws IOException {
		this.nd.history[this.nd.historyPos] = getFilePointer();
		this.nd.historyPos = ((this.nd.historyPos + 1) % 100);
		this.nd.historyCount += 1;
	}

	@Override
	public void continueSelect() {
		super.continueSelect();
		
		switch (this.nd.selectState) {
		case 1:
			this.tn.newpage(true);
			break;

		case 2:
			selectWait();
		}
	}

	public void selectWait() {
		this.nd.selectState = 2;
		this.nd.click = false;
		NsSelect localNsSelect1;
		while (true) {
			if (this.storageState != 0) {
				return;
			}
			if (this.nd.click == true) {
				this.nd.click = false;
				this.nd.textSel = -1;
				int i = 0;
				for (; i < this.nd.select.size(); i++) {
					localNsSelect1 = this.nd.select.elementAt(i);
					int j = this.nd.twinLy + localNsSelect1.y
							* (this.nd.twinFh + this.nd.twinSh);
					if ((j > this.nd.clickY)
							|| (this.nd.clickY >= j + localNsSelect1.height
									* (this.nd.twinFh + this.nd.twinSh))) {
						continue;
					}
					this.nd.textSel = i;
					break;
				}
				NsSelect localNsSelect2;
				int k;
				if (this.nd.textSel == -1) {
					for (i = 0; i < this.nd.select.size(); i++) {
						localNsSelect2 = this.nd.select.elementAt(i);
						localNsSelect2.selected = false;
						for (k = 0; k < localNsSelect2.height; k++) {
							this.nd.text.setAttr(localNsSelect2.y + k, false);
						}
					}
				} else {
					localNsSelect1 = this.nd.select.elementAt(this.nd.textSel);
					if (localNsSelect1.selected) {
						break;
					}
					for (i = 0; i < this.nd.select.size(); i++) {
						localNsSelect2 = this.nd.select.elementAt(i);
						localNsSelect2.selected = false;
						for (k = 0; k < localNsSelect2.height; k++) {
							this.nd.text.setAttr(localNsSelect2.y + k, false);
						}
					}
					localNsSelect1.selected = true;
					for (k = 0; k < localNsSelect1.height; k++) {
						this.nd.text.setAttr(localNsSelect1.y + k, true);
					}
				}
				if (!this.nd.fadeFlag) {
					this.tn.paintB();
				} else {
					this.tn.paintF();
				}
			}
			try {
				Thread.sleep(100L);
			} catch (Exception e) {
			}
		}
		try {
			if (this.nd.selnum != -1) {
				this.nd.valueNum[this.nd.selnum] = Integer
						.parseInt(localNsSelect1.label);
				this.nd.selnum = -1;
				this.tn.newpage(false);
			} else {
				if (!this.nd.selSkipFlag) {
					this.nd.gosub[this.nd.gosubPos].retpos = getFilePointer();
					this.nd.gosub[this.nd.gosubPos].rest = this.lineRest;
					this.nd.gosubPos += 1;
				}
				this.tn.newpage(false);
				gotoLabel(localNsSelect1.label);
			}
		} catch (Exception e) {
			error("select|selgosub|selnum");
		}
	}

	@Override
	public void error(String str) {
		super.error(str);
		this.nd.error = str;
	}

	private boolean hasError() {
		return this.nd.error != null;
	}
	
	private static NsColor newColor(NsColor color) {
		if (color == null) {
			return null;
		}
		return new NsColor(color.getRGB());
	}	
	
	@Override 
	protected void textStar() {
		if (this.nd.labellog == true) {
			String str2 = this.line.substring(1).toUpperCase();
			if (!this.nd.lchk.containsKey(str2)) {
				this.nd.lchk.put(str2, new Integer(1));
			}
		}
	}
	
	@Override
	protected void textPage() {
		this.tn.putMess(this.nd.text, "¨‹", this.nd.textcolor, true, true);
		if (!this.nd.fadeFlag)
			this.tn.paintB();
		else {
			this.tn.paintF();
		}
		this.tn.newpage(true);		
	}
	
	@Override
	protected void textSd() {
		this.nd.twinSpeed = this.nd.defSpeed;
	}
	
	@Override
	protected void textW() {
		if (!this.nd.fadeFlag) {
			this.tn.paintB();
		} else {
			this.tn.paintF();
		}
		this.tn.wait(Integer.parseInt(this.line.substring(2)), false);
	}
	
	@Override
	protected void textSharp() {
		this.nd.textcolor = nd.evalColor(this.line.substring(0, 7));
	}
	
	@Override
	protected void textTilde() {
		try {
			this.nd.jumpBack = getFilePointer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//FIXME:???
	@Override
	protected String evalStr(String paramString) {
		return nd.evalStr(paramString);
	}	
	
	@Override
	protected void textShow(String str1) {
		if (str1.length() > 0) {
			tn.putMess(this.nd.text, str1, this.nd.textcolor, true,
					this.lineCont);
			System.out.println(new String(str1.getBytes()));
		}
		if (!this.nd.fadeFlag) {
			this.tn.paintB();
		} else {
			this.tn.paintF();
		}		
	}
}
