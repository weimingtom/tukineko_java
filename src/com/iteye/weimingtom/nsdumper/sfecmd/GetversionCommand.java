package com.iteye.weimingtom.nsdumper.sfecmd;

import app.boya.tukineko.parser.NScripter;

public class GetversionCommand extends SFECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "getversion");
	}
	
	@Override
	public void execute() {
		debug("[SFECommand] getversion");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("getversion");
		} else {
			if (!ns.getArg(0).startsWith("%")) {
				ns.error("getversion");
			} else {
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = 999;
			}
			ns.makeLineRest(1);
		}		
	}
}
