package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class UnderlineCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "underline");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] underline");
		
		ns.error("underline");
	}
}
