package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class TransmodeCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "transmode");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] transmode");
		
		ns.error("transmode");
	}
}
