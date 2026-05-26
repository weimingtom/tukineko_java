package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class SavegameCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "savegame");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] savegame");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("savegame");
		} else {
			ns.storageState = 1;
			ns.saveLocalData("SAVE" + ns.nd.evalNum(ns.getArg(0)) + ".DAT");
			ns.makeLineRest(1);
		}		
	}
}
