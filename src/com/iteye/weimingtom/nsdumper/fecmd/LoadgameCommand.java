package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class LoadgameCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "loadgame");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] loadgame");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("loadgame");
		} else {
			ns.storageState = 2;
			ns.loadLocalData("SAVE" + ns.nd.evalNum(ns.getArg(0)) + ".DAT");
			ns.makeLineRest(1);
		}		
	}
}
