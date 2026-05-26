package app.boya.tukineko.entry;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class NsData implements Serializable {
	private static final long serialVersionUID = 1L;

	public int selectState;
	public boolean textVisible; // text
	public int gosubPos; // gosub

	public int twinLx;
	public int twinLy;
	public int twinLw;
	public int twinLh;
	public int twinFw;
	public int twinFh;
	public int twinSw;
	public int twinSh;
	public int twinSpeed;
	public int twinHx;
	public int twinHy;
	public int twinEx;
	public int twinEy;
	public boolean twinBold;
	public boolean twinShadow;
	public NsColor twinColor;
	public String twinImage;

	public String bgImage;
	public NsColor bgColor;
	public int bgEffect;
	public int autoclick;

	public int quakex;
	public int quakey;

	public int textSel; // select
	public boolean selSkipFlag;
	public int selnum;
	public NsColor textcolor;

	public String btnImage; // btn

	public int btnSel;// btn
	public boolean btnVisible;

	public int[] history;
	public int historyPos;
	public int historyCount;
	public int jumpBack;

	// not write
	public transient int effectblank;
	public transient int cdfadeout;
	public transient String clickstr;
	public transient int clickstrLine;
	public transient NsColor selectcolorOn;
	public transient NsColor selectcolorOut;
	public transient int menusetwindowFx;
	public transient int menusetwindowFy;
	public transient int menusetwindowSx;
	public transient int menusetwindowSy;
	public transient boolean menusetwindowBold;
	public transient boolean menusetwindowShadow = false;
	public transient NsColor menusetwindowColor;
	public transient NsColor menuselectcolorOn;
	public transient NsColor menuselectcolorOut;
	public transient NsColor menuselectcolorNosave;

	// rmenu ?
	public transient String savenameSave;
	public transient String savenameLoad;
	public transient String savenameTitle;

	public transient NsColor lookbackcolor;

	public transient boolean globalon;
	public transient boolean filelog;
	public transient boolean labellog;

	public transient int defSpeed;
	public transient int defSpeedLow;
	public transient int defSpeedMiddle;
	public transient int defSpeedHigh;

	public transient int savenumber;

	public transient String[] rmenu;

	public transient int[] fadeImg;
	public transient boolean fadeMode;
	public transient boolean fadeFlag;

	public transient boolean click;
	public transient int clickX;
	public transient int clickY;

//	public transient boolean exitFlag;

//	public transient int storageState;
	public transient int storageNo;

	public transient int[] valueNum;
	public transient String[] valueStr;

	public Hashtable<String, Integer> label;
	public Hashtable<String, String> numalias;
	public Hashtable<String, String> stralias;
	public Hashtable<String, NsEffect> effect;
	public Hashtable<String, Integer> fchk;
	public Hashtable<String, Integer> lchk;

	public NsText text;
	public NsGosub[] gosub;
	public NsShell[] shell;
	public NsSprite[] sprite;
	public Vector<NsSelect> select;
	public Vector<NsButton> btn;

	public boolean menuVisible;
//	public String path;
	public boolean rotate;

	// FIXME: global exit
	public String error;

	public NsData() {
		this.error = null;

		this.click = false;
		this.clickX = -1;
		this.clickY = -1;
		this.valueNum = new int[4096];
		this.valueStr = new String[4096];
		for (int i = 0; i < 4096; i++) {
			this.valueNum[i] = 0;
			this.valueStr[i] = "";
		}
//		this.storageState = -1;
		this.storageNo = 0;
		//
		this.fadeImg = new int[76800];
		this.fadeMode = true;
		this.fadeFlag = false;
		this.selectState = 0;
		//
		this.text = null;
		this.textVisible = true;
		this.gosub = new NsGosub[8];
		this.gosubPos = 0;
		for (int i = 0; i < 8; i++) {
			this.gosub[i] = new NsGosub();
		}
		this.label = new Hashtable<String, Integer>();
		this.numalias = new Hashtable<String, String>();
		this.stralias = new Hashtable<String, String>();
		this.effect = new Hashtable<String, NsEffect>();
		this.fchk = new Hashtable<String, Integer>();
		this.lchk = new Hashtable<String, Integer>();
		//
		this.rmenu = new String[6];
		this.globalon = false;
		this.filelog = false;
		this.labellog = false;
		this.defSpeedLow = 20;
		this.defSpeedMiddle = 10;
		this.defSpeedHigh = 0;
		this.defSpeed = this.defSpeedMiddle;
		this.twinSpeed = this.defSpeed;
		this.autoclick = 0;
		this.shell = new NsShell[3];
		this.sprite = new NsSprite[50];
		for (int i = 0; i < 50; i++) {
			this.sprite[i] = new NsSprite();
		}
		this.btn = new Vector<NsButton>();
		this.btnSel = -1;
		this.btnVisible = false;
		this.quakex = 0;
		this.quakey = 0;
		this.select = new Vector<NsSelect>();
		this.textSel = -1;
		this.selSkipFlag = false;
		this.selnum = -1;
		this.textcolor = NsColor.white;
		this.history = new int[100];
		this.historyPos = 0;
		this.historyCount = 0;
		this.jumpBack = 0;
	}

	public int evalNumAlias(String paramString) {
		if (this.numalias.containsKey(paramString) == true) {
			return Integer.parseInt(this.numalias.get(paramString));
		}
		return Integer.parseInt(paramString);
	}

	public int evalNum(String paramString) {
		if (paramString.startsWith("%")) {
			return this.valueNum[evalNumAlias(paramString.substring(1))];
		}
		return evalNumAlias(paramString);
	}

	public String evalStrAlias(String paramString) {
		if (this.stralias.containsKey(paramString) == true) {
			return this.stralias.get(paramString);
		}
		return paramString;
	}

	public String evalStr(String paramString) {
		if (paramString.startsWith("$") == true) {
			return this.valueStr[evalNum(paramString.substring(1))];
		}
		return evalStrAlias(paramString);
	}

	public boolean evalBoolean(String paramString) {
		return evalNum(paramString) == 1;
	}

	public NsColor evalColor(String paramString) {
		if ((paramString.length() != 7) || (!paramString.startsWith("#"))) {
			System.err.println("color value: " + paramString);
			return null;
		}
		try {
			return new NsColor(
					Integer.parseInt(paramString.substring(1), 16) | 0xFF000000);
		} catch (NumberFormatException e) {
			System.err.println("color value: " + paramString);
		}
		return null;
	}

}
