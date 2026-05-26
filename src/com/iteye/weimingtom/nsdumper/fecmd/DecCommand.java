package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class DecCommand extends FECommand {
	NScripter ns = NScripter.getInstance();		
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "dec");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] dec");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("dec");
		} else {
			if (!ns.getArg(0).startsWith("%")) {
				ns.error("dec");
			} else {
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] -= 1;
			}
			ns.makeLineRest(1);
		}		
	}
}
