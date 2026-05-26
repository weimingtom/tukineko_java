package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class EndCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "end");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] end");
		
		ns.setMsRest();
		ns.exitFlag = true;
	}
}
