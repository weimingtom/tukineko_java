package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class RoffCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "roff");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] roff");
		
		ns.error("roff");
	}
}
