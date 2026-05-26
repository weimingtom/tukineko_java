package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class MovCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "mov");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] mov");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("move");
		} else {
			if (ns.getArg(0).startsWith("%") == true) {
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = ns.nd.evalNum(ns.getArg(1));
			} else if (ns.getArg(0).startsWith("$") == true) {
				ns.nd.valueStr[ns.nd.evalNum(ns.getArg(0).substring(1))] = ns.nd.evalStr(ns.getArg(1));
			} else {
				ns.error("mov");
			}
			ns.makeLineRest(2);
		}		
	}
}
