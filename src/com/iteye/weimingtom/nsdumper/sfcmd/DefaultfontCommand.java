package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class DefaultfontCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "defaultfont");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] defaultfont");
		
		ns.error("defaultfont");
	}
}
