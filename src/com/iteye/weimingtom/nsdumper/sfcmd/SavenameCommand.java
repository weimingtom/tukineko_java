package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class SavenameCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "savename");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] savename");
		
		if (ns.parseArgs(true) < 3) {
			ns.error("savename");
		} else {
			ns.nd.savenameSave = ns.nd.evalStr(ns.getArg(0));
			ns.nd.savenameLoad = ns.nd.evalStr(ns.getArg(1));
			ns.nd.savenameTitle = ns.nd.evalStr(ns.getArg(2));
			ns.makeLineRest(3);
		}		
	}
}
