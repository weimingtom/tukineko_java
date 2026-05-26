package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class DsoundCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "dsound");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] dsound");
		
		ns.error("dsound");
	}
}
