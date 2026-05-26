package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class GlobalonCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "globalon");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] globalon");
		
		ns.setMsRest();
		ns.nd.globalon = true;
		ns.loadGlobalData();		
	}
}
