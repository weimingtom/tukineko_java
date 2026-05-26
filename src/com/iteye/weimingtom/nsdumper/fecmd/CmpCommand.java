package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class CmpCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "cmp");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] cmp");
		
		if (ns.parseArgs(true) < 3) {
			ns.error("cmp");
		} else if (!ns.getArg(0).startsWith("%")) {
			ns.error("cmp");
		} else {
			ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = ns.nd.evalStr(ns.getArg(1)).compareTo(ns.getArg(2));
			ns.makeLineRest(3);
		}		
	}
}
