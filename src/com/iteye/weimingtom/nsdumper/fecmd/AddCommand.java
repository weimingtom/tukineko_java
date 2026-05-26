package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class AddCommand extends FECommand {
	NScripter ns = NScripter.getInstance();	
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "add");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] add");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("add");
		} else {
			if (!ns.getArg(0).startsWith("%")) {
				ns.error("add");
			} else {
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] += ns.nd.evalNum(ns.getArg(1));
			}
			ns.makeLineRest(2);
		}		
	}
}
