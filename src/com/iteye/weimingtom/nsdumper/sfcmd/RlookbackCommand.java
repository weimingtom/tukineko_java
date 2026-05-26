package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class RlookbackCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "rlookback");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] rlookback");
		
		ns.error("rlookback");
	}
}
