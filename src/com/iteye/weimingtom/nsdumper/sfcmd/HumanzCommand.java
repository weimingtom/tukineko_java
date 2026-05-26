package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class HumanzCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "humanz");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] humanz");
		
		ns.error("humanz");
	}
}
