package com.iteye.weimingtom.nsdumper.sfecmd;

import app.boya.tukineko.parser.NScripter;

public class MousecursorCommand extends SFECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "mousecursor");
	}
	
	@Override
	public void execute() {
		debug("[SFECommand] mousecursor");
		
		ns.error("mousecursor");
	}
}
