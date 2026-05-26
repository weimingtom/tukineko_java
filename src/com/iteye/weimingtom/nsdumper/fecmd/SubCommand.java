package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class SubCommand extends FECommand {
	NScripter ns = NScripter.getInstance();	
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "sub");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] sub");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("sub");
		} else if (!ns.getArg(0).startsWith("%")) {
			ns.error("sub");
		} else {
			ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] -= ns.nd.evalNum(ns.getArg(1));
		}		
	}
}
