package com.iteye.weimingtom.nsdumper.parser;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.iteye.weimingtom.nsdumper.ecmd.BtnCommand;
import com.iteye.weimingtom.nsdumper.ecmd.BtndefCommand;
import com.iteye.weimingtom.nsdumper.ecmd.Btnwait2Command;
import com.iteye.weimingtom.nsdumper.ecmd.BtnwaitCommand;
import com.iteye.weimingtom.nsdumper.ecmd.ClickCommand;
import com.iteye.weimingtom.nsdumper.ecmd.ClickposCommand;
import com.iteye.weimingtom.nsdumper.ecmd.DefineresetCommand;
import com.iteye.weimingtom.nsdumper.ecmd.DelayCommand;
import com.iteye.weimingtom.nsdumper.ecmd.ECommand;
import com.iteye.weimingtom.nsdumper.ecmd.GettimerCommand;
import com.iteye.weimingtom.nsdumper.ecmd.InputstrCommand;
import com.iteye.weimingtom.nsdumper.ecmd.LookbackflushCommand;
import com.iteye.weimingtom.nsdumper.ecmd.ResetCommand;
import com.iteye.weimingtom.nsdumper.ecmd.ResettimerCommand;
import com.iteye.weimingtom.nsdumper.ecmd.RmodeCommand;
import com.iteye.weimingtom.nsdumper.ecmd.SelectCommand;
import com.iteye.weimingtom.nsdumper.ecmd.SelgosubCommand;
import com.iteye.weimingtom.nsdumper.ecmd.SelnumCommand;
import com.iteye.weimingtom.nsdumper.ecmd.SystemcallCommand;
import com.iteye.weimingtom.nsdumper.ecmd.TextspeedCommand;
import com.iteye.weimingtom.nsdumper.ecmd.TrapCommand;
import com.iteye.weimingtom.nsdumper.ecmd.WaitCommand;
import com.iteye.weimingtom.nsdumper.ecmd.WaittimerCommand;
import com.iteye.weimingtom.nsdumper.fcmd.FCommand;
import com.iteye.weimingtom.nsdumper.fcmd.GameCommand;
import com.iteye.weimingtom.nsdumper.fcmd.IntlimitCommand;
import com.iteye.weimingtom.nsdumper.fcmd.SavenumberCommand;
import com.iteye.weimingtom.nsdumper.fecmd.AddCommand;
import com.iteye.weimingtom.nsdumper.fecmd.AtoiCommand;
import com.iteye.weimingtom.nsdumper.fecmd.CmpCommand;
import com.iteye.weimingtom.nsdumper.fecmd.DateCommand;
import com.iteye.weimingtom.nsdumper.fecmd.DecCommand;
import com.iteye.weimingtom.nsdumper.fecmd.DivCommand;
import com.iteye.weimingtom.nsdumper.fecmd.EndCommand;
import com.iteye.weimingtom.nsdumper.fecmd.FECommand;
import com.iteye.weimingtom.nsdumper.fecmd.GetiniCommand;
import com.iteye.weimingtom.nsdumper.fecmd.GetregCommand;
import com.iteye.weimingtom.nsdumper.fecmd.GosubCommand;
import com.iteye.weimingtom.nsdumper.fecmd.GotoCommand;
import com.iteye.weimingtom.nsdumper.fecmd.IfCommand;
import com.iteye.weimingtom.nsdumper.fecmd.IncCommand;
import com.iteye.weimingtom.nsdumper.fecmd.ItoaCommand;
import com.iteye.weimingtom.nsdumper.fecmd.JumpbCommand;
import com.iteye.weimingtom.nsdumper.fecmd.JumpfCommand;
import com.iteye.weimingtom.nsdumper.fecmd.LoadgameCommand;
import com.iteye.weimingtom.nsdumper.fecmd.ModCommand;
import com.iteye.weimingtom.nsdumper.fecmd.MovCommand;
import com.iteye.weimingtom.nsdumper.fecmd.MulCommand;
import com.iteye.weimingtom.nsdumper.fecmd.NotifCommand;
import com.iteye.weimingtom.nsdumper.fecmd.ReturnCommand;
import com.iteye.weimingtom.nsdumper.fecmd.Rnd2Command;
import com.iteye.weimingtom.nsdumper.fecmd.RndCommand;
import com.iteye.weimingtom.nsdumper.fecmd.SavegameCommand;
import com.iteye.weimingtom.nsdumper.fecmd.SaveoffCommand;
import com.iteye.weimingtom.nsdumper.fecmd.SaveonCommand;
import com.iteye.weimingtom.nsdumper.fecmd.SkipCommand;
import com.iteye.weimingtom.nsdumper.fecmd.SubCommand;
import com.iteye.weimingtom.nsdumper.fecmd.TimeCommand;
import com.iteye.weimingtom.nsdumper.secmd.AbssetcursorCommand;
import com.iteye.weimingtom.nsdumper.secmd.SECommand;
import com.iteye.weimingtom.nsdumper.secmd.SetcursorCommand;
import com.iteye.weimingtom.nsdumper.secmd.SetwindowCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.ArcCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.BgaliaCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.CdfadeoutCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.ClickstrCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.ClickvoiceCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.DefSpeedCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.DefaultfontCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.DsoundCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.EffectCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.EffectblankCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.FilelogCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.GlobalonCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.HumanzCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.KillmenuCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.LabellogCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.LookbackbuttonCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.LookbackcolorCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.LookbackvoiceCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.MenuselectcolorCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.MenuselectvoiceCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.MenusetwindowCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.NsaCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.NumaliasCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.RlookbackCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.RmenuCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.RoffCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.SFCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.SavenameCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.SelectcolorCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.SelectvoiceCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.SoundpressplginCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.SpiCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.StraliasCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.TransmodeCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.UnderlineCommand;
import com.iteye.weimingtom.nsdumper.sfcmd.VersionstrCommand;
import com.iteye.weimingtom.nsdumper.sfecmd.CaptionCommand;
import com.iteye.weimingtom.nsdumper.sfecmd.GetversionCommand;
import com.iteye.weimingtom.nsdumper.sfecmd.MousecursorCommand;
import com.iteye.weimingtom.nsdumper.sfecmd.SFECommand;
import com.iteye.weimingtom.nsdumper.sfecmd.WindoweffectCommand;
import com.iteye.weimingtom.nsdumper.vecmd.AutoclickCommand;
import com.iteye.weimingtom.nsdumper.vecmd.AviCommand;
import com.iteye.weimingtom.nsdumper.vecmd.BgCommand;
import com.iteye.weimingtom.nsdumper.vecmd.BltCommand;
import com.iteye.weimingtom.nsdumper.vecmd.BrCommand;
import com.iteye.weimingtom.nsdumper.vecmd.ClCommand;
import com.iteye.weimingtom.nsdumper.vecmd.CspCommand;
import com.iteye.weimingtom.nsdumper.vecmd.DwaveCommand;
import com.iteye.weimingtom.nsdumper.vecmd.DwaveloopCommand;
import com.iteye.weimingtom.nsdumper.vecmd.DwavestopCommand;
import com.iteye.weimingtom.nsdumper.vecmd.ErasetextwindowCommand;
import com.iteye.weimingtom.nsdumper.vecmd.LdCommand;
import com.iteye.weimingtom.nsdumper.vecmd.LocateCommand;
import com.iteye.weimingtom.nsdumper.vecmd.LspCommand;
import com.iteye.weimingtom.nsdumper.vecmd.LsphCommand;
import com.iteye.weimingtom.nsdumper.vecmd.MenuclickdefCommand;
import com.iteye.weimingtom.nsdumper.vecmd.MenuclickpageCommand;
import com.iteye.weimingtom.nsdumper.vecmd.MenufullCommand;
import com.iteye.weimingtom.nsdumper.vecmd.MenuwindowCommand;
import com.iteye.weimingtom.nsdumper.vecmd.MonocroCommand;
import com.iteye.weimingtom.nsdumper.vecmd.Mp3Command;
import com.iteye.weimingtom.nsdumper.vecmd.Mp3loopCommand;
import com.iteye.weimingtom.nsdumper.vecmd.MspCommand;
import com.iteye.weimingtom.nsdumper.vecmd.NegaCommand;
import com.iteye.weimingtom.nsdumper.vecmd.OfscpyCommand;
import com.iteye.weimingtom.nsdumper.vecmd.PlayCommand;
import com.iteye.weimingtom.nsdumper.vecmd.PlayonceCommand;
import com.iteye.weimingtom.nsdumper.vecmd.PlaystopCommand;
import com.iteye.weimingtom.nsdumper.vecmd.PrintCommand;
import com.iteye.weimingtom.nsdumper.vecmd.PuttextCommand;
import com.iteye.weimingtom.nsdumper.vecmd.QuakexCommand;
import com.iteye.weimingtom.nsdumper.vecmd.QuakeyCommand;
import com.iteye.weimingtom.nsdumper.vecmd.StopCommand;
import com.iteye.weimingtom.nsdumper.vecmd.TalCommand;
import com.iteye.weimingtom.nsdumper.vecmd.TextclearCommand;
import com.iteye.weimingtom.nsdumper.vecmd.TextoffCommand;
import com.iteye.weimingtom.nsdumper.vecmd.TextonCommand;
import com.iteye.weimingtom.nsdumper.vecmd.VECommand;
import com.iteye.weimingtom.nsdumper.vecmd.VspCommand;
import com.iteye.weimingtom.nsdumper.vecmd.WaveCommand;
import com.iteye.weimingtom.nsdumper.vecmd.WaveloopCommand;
import com.iteye.weimingtom.nsdumper.vecmd.WavestopCommand;
import com.iteye.weimingtom.nsdumper.vfecmd.MesboxCommand;
import com.iteye.weimingtom.nsdumper.vfecmd.VFECommand;

/**
 * @see http://tlwiki.org/index.php?title=Tools
 * @see http://nscripter.insani.org/reference/
 * 
 * @author 「月猫」 NScripter for PDA in Java (http://www.din.or.jp/~boya/tsukihime/tukineko/index.html)
 *
 */
public class NSParser {
	//TODO: Is the file 'nscript.dat' extracted by ExtractData ?
	protected boolean IS_EXTRACTED = false; 
	
	protected byte[] readBuff = new byte[4096];
	protected int readTop = 0;
	protected int readEnd = 0;
	protected int readPos = 0;
	protected RandomAccessFile raf;
	public int storageState = -1;
	public boolean exitFlag;
	public String path = "./";; //FIXME:???ns.nd.path
	protected String line = null;
	public String lineRest = null;
	protected boolean lineCont = false;
	protected List<String> args = new ArrayList<String>();
	private List<SFCommand> sfCommands = new ArrayList<SFCommand>();
	private List<SFECommand> sfeCommands = new ArrayList<SFECommand>();
	private List<SECommand> seCommands = new ArrayList<SECommand>();
	private List<VFECommand> vfeCommands = new ArrayList<VFECommand>();
	private List<VECommand> veCommands = new ArrayList<VECommand>();
	private List<FCommand> fCommands = new ArrayList<FCommand>();
	private List<ECommand> eCommands = new ArrayList<ECommand>();
	private List<FECommand> feCommands = new ArrayList<FECommand>();
	public boolean argCont;

	public NSParser(String path) {
		this.path = path;
	}
	
	protected void initParser() {
		sfCommands.add(new LookbackbuttonCommand());
		sfCommands.add(new LookbackcolorCommand());
		sfCommands.add(new SavenameCommand());
		sfCommands.add(new ClickstrCommand());
		sfCommands.add(new FilelogCommand());
		sfCommands.add(new LabellogCommand());
		sfCommands.add(new SoundpressplginCommand());
		sfCommands.add(new VersionstrCommand());
		sfCommands.add(new EffectblankCommand());
		sfCommands.add(new BgaliaCommand());
		sfCommands.add(new CdfadeoutCommand());
		sfCommands.add(new SpiCommand());
		sfCommands.add(new ArcCommand());
		sfCommands.add(new NsaCommand());
		sfCommands.add(new EffectCommand());
		sfCommands.add(new TransmodeCommand());
		sfCommands.add(new StraliasCommand());
		sfCommands.add(new NumaliasCommand());
		sfCommands.add(new DefaultfontCommand());
		sfCommands.add(new SelectcolorCommand());
		sfCommands.add(new MenuselectcolorCommand());
		sfCommands.add(new LookbackvoiceCommand());
		sfCommands.add(new ClickvoiceCommand());
		sfCommands.add(new SelectvoiceCommand());
		sfCommands.add(new MenuselectvoiceCommand());
		sfCommands.add(new GlobalonCommand());
		sfCommands.add(new HumanzCommand());
		sfCommands.add(new UnderlineCommand());
		sfCommands.add(new RlookbackCommand());
		sfCommands.add(new RoffCommand());
		sfCommands.add(new RmenuCommand());
		sfCommands.add(new MenusetwindowCommand());
		sfCommands.add(new KillmenuCommand());
		sfCommands.add(new DefSpeedCommand());
		sfCommands.add(new DsoundCommand());

		sfeCommands.add(new WindoweffectCommand());
		sfeCommands.add(new MousecursorCommand());
		sfeCommands.add(new CaptionCommand());
		sfeCommands.add(new GetversionCommand());
		
		seCommands.add(new AbssetcursorCommand());
		seCommands.add(new SetcursorCommand());
		seCommands.add(new SetwindowCommand());
		
		vfeCommands.add(new MesboxCommand());
		
		veCommands.add(new LocateCommand());
		veCommands.add(new PuttextCommand());
		veCommands.add(new AutoclickCommand());
		veCommands.add(new BrCommand());
		veCommands.add(new QuakexCommand());
		veCommands.add(new QuakeyCommand());
		veCommands.add(new ErasetextwindowCommand());
		veCommands.add(new TextoffCommand());
		veCommands.add(new TextonCommand());
		veCommands.add(new TextclearCommand());
		veCommands.add(new MenufullCommand());
		veCommands.add(new MenuwindowCommand());
		veCommands.add(new MenuclickpageCommand());
		veCommands.add(new MenuclickdefCommand());
		veCommands.add(new MonocroCommand());
		veCommands.add(new NegaCommand());
		veCommands.add(new BgCommand());
		veCommands.add(new LdCommand());
		veCommands.add(new ClCommand());
		veCommands.add(new PrintCommand());
		veCommands.add(new TalCommand());
		veCommands.add(new LspCommand());
		veCommands.add(new LsphCommand());
		veCommands.add(new CspCommand());
		veCommands.add(new VspCommand());
		veCommands.add(new MspCommand());
		veCommands.add(new PlayCommand());
		veCommands.add(new PlayonceCommand());
		veCommands.add(new StopCommand());
		veCommands.add(new WaveCommand());
		veCommands.add(new WaveloopCommand());
		veCommands.add(new WavestopCommand());
		veCommands.add(new PlaystopCommand());
		veCommands.add(new Mp3Command());
		veCommands.add(new Mp3loopCommand());
		veCommands.add(new AviCommand());
		veCommands.add(new DwaveCommand());
		veCommands.add(new DwaveloopCommand());
		veCommands.add(new DwavestopCommand());
		veCommands.add(new BltCommand());
		veCommands.add(new OfscpyCommand());
		
		fCommands.add(new IntlimitCommand());
		fCommands.add(new SavenumberCommand());
		fCommands.add(new GameCommand());
		
		eCommands.add(new RmodeCommand());
		eCommands.add(new SystemcallCommand());
		eCommands.add(new TrapCommand());
		eCommands.add(new SelectCommand());
		eCommands.add(new SelgosubCommand());
		eCommands.add(new SelnumCommand());
		eCommands.add(new ResettimerCommand());
		eCommands.add(new WaittimerCommand());
		eCommands.add(new GettimerCommand());
		eCommands.add(new ClickCommand());
		eCommands.add(new ResetCommand());
		eCommands.add(new DefineresetCommand());
		eCommands.add(new DelayCommand());
		eCommands.add(new WaitCommand());
		eCommands.add(new TextspeedCommand());
		eCommands.add(new LookbackflushCommand());
		eCommands.add(new InputstrCommand());
		eCommands.add(new ClickposCommand());
		eCommands.add(new BtndefCommand());
		eCommands.add(new BtnCommand());
		eCommands.add(new BtnwaitCommand());
		eCommands.add(new Btnwait2Command());
		
		feCommands.add(new EndCommand());
		feCommands.add(new MovCommand());
		feCommands.add(new RndCommand());
		feCommands.add(new Rnd2Command());
		feCommands.add(new GetregCommand());
		feCommands.add(new GetiniCommand());
		feCommands.add(new AddCommand());
		feCommands.add(new SubCommand());
		feCommands.add(new IncCommand());
		feCommands.add(new DecCommand());
		feCommands.add(new MulCommand());
		feCommands.add(new DivCommand());
		feCommands.add(new ModCommand());
		feCommands.add(new GotoCommand());
		feCommands.add(new SkipCommand());
		feCommands.add(new GosubCommand());
		feCommands.add(new ReturnCommand());
		feCommands.add(new CmpCommand());
		feCommands.add(new IfCommand());
		feCommands.add(new NotifCommand());
		feCommands.add(new JumpfCommand());
		feCommands.add(new JumpbCommand());
		feCommands.add(new LoadgameCommand());
		feCommands.add(new SavegameCommand());
		feCommands.add(new AtoiCommand());
		feCommands.add(new ItoaCommand());
		feCommands.add(new SaveonCommand());
		feCommands.add(new SaveoffCommand());
		feCommands.add(new DateCommand());
		feCommands.add(new TimeCommand());		
	}

	public void error(String str) {
		System.err.println(str);
	}

	public void debug(String str) {
		System.out.println(str);
	}

	public void putMess(String str, boolean isLineCont) {
		System.out.println(str);
	}

	public void run() throws IOException {
		initParser();
		this.storageState = -2;
		this.storageState = -1;
		this.raf = new RandomAccessFile(this.path, "r");
		this.storageState = 0;
		gotoLabel("*define");
		this.exitFlag = false;
		this.argCont = false;
		while (!this.exitFlag) {
			exec();
		}
		System.exit(0);
	}

	public String newString(String paramString) {
		if (paramString == null) {
			return null;
		}
		return new String(paramString);
	}

	protected int read() throws IOException {
		if ((this.readTop > this.readPos) || (this.readPos >= this.readEnd)) {
			if (this.readPos == this.readEnd) {
				this.readTop = this.readEnd;
				this.readEnd += this.raf.read(this.readBuff, 0, 4096);
			} else {
				this.raf.seek(this.readPos);
				this.readTop = this.readPos;
				this.readEnd = (this.readTop + this.raf.read(this.readBuff, 0,
						4096));
			}
		}
		if ((this.readTop <= this.readPos) && (this.readPos < this.readEnd)) {
			if (IS_EXTRACTED) {
				return this.readBuff[(this.readPos++ - this.readTop)];
			} else {
				return this.readBuff[(this.readPos++ - this.readTop)] & 0xFF ^ 0x84;
			}
		}
		return -1;
	}

	public int getFilePointer() throws IOException {
		return this.readPos;
	}

	public String readLine() throws IOException {
		byte[] arrayOfByte = new byte[1024];
		this.lineCont = false;
		if (this.lineRest == null) {
			addHistory();
			int k;
			int i = k = 0;
			int j;
			while (((j = read()) != 10) && (j != -1)) {
				if (j == 13) {
					continue;
				}
				arrayOfByte[(i++)] = (byte) j;
				if ((j != 32) && (j != 9)) {
					k = i;
				}
				if (j >= 128) {
					arrayOfByte[(i++)] = (byte) read();
					k = i;
				}
			}
			if ((i > 0) || (j != -1)) {
				this.line = new String(arrayOfByte, 0, k, "SJIS");
			} else {
				this.line = null;
			}
		} else {
			this.line = this.lineRest;
			this.lineRest = null;
			this.lineCont = true;
		}
		return this.line;
	}

	public void gotoLabel(String paramString) throws IOException {
		if (!paramString.startsWith("*")) {
			error("Error Label:" + paramString);
			return;
		}
	}

	public void setMsRest() {
		int i;
		if ((i = this.line.indexOf(":")) != -1) {
			this.lineRest = this.line.substring(i + 1);
		}
	}

	public String getArg(int paramInt) {
		return this.args.get(paramInt);
	}

	public int getArgSize() {
		return this.args.size();
	}

	public int evalNumAlias(String paramString) {
		return Integer.parseInt(paramString);
	}

	public int evalNum(String paramString) {
		if (paramString.startsWith("%")) {
			return 0;
		}
		return evalNumAlias(paramString);
	}

	public String evalStrAlias(String paramString) {
		return paramString;
	}

	protected String evalStr(String paramString) {
		if (paramString.startsWith("$") == true) {
			return "";
		}
		return evalStrAlias(paramString);
	}

	public boolean evalBoolean(String paramString) {
		return evalNum(paramString) == 1;
	}

	public boolean checkCommand(String paramString1, String paramString2) {
		return (paramString1.equals(paramString2) == true)
				|| ((paramString1.length() > paramString2.length())
						&& (paramString1.substring(0, paramString2.length())
								.equals(paramString2) == true) && (" \t"
						.indexOf(paramString1.charAt(paramString2.length())) != -1));
	}

	public void parseMessageCommand() {
		int j = 0;
		for (; j < this.line.length(); j++) {
			int i;
			if (((i = this.line.charAt(j)) >= 'Ā')
					|| ((j > 0) && ("!\\".indexOf(i) != -1)))
				break;
		}
		if (j < this.line.length()) {
			this.lineRest = this.line.substring(j);
			this.line = this.line.substring(0, j);
		}
	}

	public void continueSelect() {

	}

	protected int exec() throws IOException {
		readLine();
		// FIXME: EOF
		if (this.line == null) {
			this.exitFlag = true;
			return this.storageState;
		}
		if (this.line.length() == 0) {
			return this.storageState;
		}
		int i = 0;
		for (; i < this.line.length(); i++) {
			if (" \t".indexOf(this.line.charAt(i)) == -1) {
				break;
			}
		}
		if (i > 0) {
			this.line = this.line.substring(i);
		}
		if (this.line.startsWith(";") == true) {
			return this.storageState;
		}
		if (this.line.startsWith("*") == true) {
			textStar();
			return this.storageState;
		}
		if (settingF(this.line) == true) {
			return this.storageState;
		}
		if (settingFE(this.line) == true) {
			return this.storageState;
		}
		if (settingE(this.line) == true) {
			return this.storageState;
		}
		if (visualE(this.line) == true) {
			return this.storageState;
		}
		if (visualFE(this.line) == true) {
			return this.storageState;
		}
		if (execF(this.line) == true) {
			return this.storageState;
		}
		if (execE(this.line) == true) {
			return this.storageState;
		}
		if (execFE(this.line) == true) {
			return this.storageState;
		}
		if (this.line.startsWith("\\") == true) {
			parseMessageCommand();
			textPage();
			return this.storageState;
		}
		if (this.line.startsWith("!sd") == true) {
			parseMessageCommand();
			textSd();
			return this.storageState;
		}
		if (this.line.startsWith("!w") == true) {
			parseMessageCommand();
			textW();
			return this.storageState;
		}
		if (this.line.startsWith("#") == true) {
			parseMessageCommand();
			textSharp();
			return this.storageState;
		}
		if (this.line.startsWith("~") == true) {
			parseMessageCommand();
			textTilde();
			return this.storageState;
		}
		String str1 = evalStr(this.line);
		// FIXME:
		if ((str1.length() > 0) && (str1.charAt(0) < 127 /* 'Ā' */)) {
			error("Warning: " + str1);
			return this.storageState;
		}
		for (i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) == '_' || str1.charAt(i) == '@'
					|| str1.charAt(i) == '%'
					|| (str1.charAt(i) >= '0' && str1.charAt(i) <= '9')) {
				str1 = str1.substring(0, i) + str1.substring(i + 1);
				i--;
			} else if (str1.charAt(i) == '$') {
				int j = i + 1;
				for (; j < str1.length(); j++) {
					if (str1.charAt(j) >= 'Ā') {
						break;
					}
				}
				str1 = str1.substring(0, i) + evalStr(str1.substring(i, j))
						+ str1.substring(j);
			} else if (str1.charAt(i) < 'Ā') {
				this.lineRest = str1.substring(i);
				str1 = str1.substring(0, i);
				break;
			}
		}
		textShow(str1);
		return this.storageState;
	}

	public void makeLineRest(int paramInt) {
		if (paramInt >= getArgSize()) {
			return;
		}
		int j = 0;
		int i = 0;
		for (; i < paramInt; i++) {
			j = this.line.indexOf(getArg(i), j) + getArg(i).length();
		}
		j = this.line.indexOf(getArg(i), j);
		this.lineRest = this.line.substring(j);
	}

	public boolean settingF(String paramString) {
		for (SFCommand command : sfCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}
		return false;
	}

	public boolean settingFE(String paramString) {
		for (SFECommand command : sfeCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}
		return false;
	}

	public boolean settingE(String paramString) {
		for (SECommand command : seCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}
		return false;
	}

	public boolean visualE(String paramString) {
		for (VECommand command : veCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}		
		return false;
	}

	public boolean visualFE(String paramString) {
		for (VFECommand command : vfeCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}		
		return false;
	}

	public boolean execF(String paramString) {
		for (FCommand command : fCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}		
		return false;
	}

	public boolean execE(String paramString) {
		for (ECommand command : eCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}		
		return false;
	}

	public boolean execFE(String paramString) {
		for (FECommand command : feCommands) {
			if (command.check(paramString)) {
				command.execute();
				return true;
			}
		}
		return false;
	}
	
//	public static void main(String[] args) throws IOException {
//		NSParser parser = new NSParser("data/0.txt");
//		parser.run();
//	}
	
	//to parser
	public int parseArgs(boolean paramBoolean) {
		this.args.clear(); //removeAllElements();
		int i = 0;
		label386: if (paramBoolean == true) {
			while (" \t".indexOf(this.line.charAt(i)) == -1)
				i++;
			break label386;
		}
		do {
			while (" \t".indexOf(this.line.charAt(i)) != -1)
				i++;
			int j;
			int k;
			if (this.line.charAt(i) == '"') {
				i++;
				if ((j = i) >= this.line.length())
					break;
				do {
					j++;
					if (j >= this.line.length())
						break;
				} while (this.line.charAt(j) != '"');

				this.args.add(this.line.substring(i, j));
				j++;
			} else {
				j = i + 1;

				while ((j < this.line.length())
						&& (" \t,:;&\\".indexOf(k = this.line.charAt(j)) == -1)
						&& (k < 256)) {
					j++;
				}
				if ((j < this.line.length()) && (this.line.charAt(i) == '&')
						&& (this.line.charAt(j) == '&')) {
					j++;
				}
				this.args.add(this.line.substring(i, j));
			}

			while ((j < this.line.length())
					&& (" \t".indexOf(this.line.charAt(j)) != -1)) {
				j++;
			}
			this.argCont = false;
			if (j < this.line.length()) {
				if ((k = this.line.charAt(j)) == ':') {
					this.lineRest = this.line.substring(j + 1);
					break;
				}
				if (k > 255) {
					this.lineRest = this.line.substring(j);
					break;
				}
				if (k == 59)
					break;
				if (k == 44) {
					this.argCont = true;
					j++;
				}
			}
			i = j;
		} while (i < this.line.length());

		return this.args.size();
	}	
	
	protected void textStar() {
	}
	
	protected void textPage() {
	}
	
	protected void textSd() {
	}
	
	protected void textW() {
	}
	
	protected void textSharp() {
	}
	
	protected void textTilde() {
	}
	
	protected void textShow(String str1) {
		if (str1.length() > 0) {
			putMess(str1, this.lineCont);
		}
	}
	
	//to parser
	public void setFilePointer(int paramInt) throws IOException {
		this.readPos = paramInt;
	}	
	
	protected void addHistory() throws IOException {
	}
}
