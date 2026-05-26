package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class IncCommand extends FECommand {
	NScripter ns = NScripter.getInstance();		
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "inc");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] inc");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("inc");
		} else {
			if (!ns.getArg(0).startsWith("%")) {
				ns.error("inc");
			} else {
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] += 1;
			}
			ns.makeLineRest(1);
		}		
	}
}
