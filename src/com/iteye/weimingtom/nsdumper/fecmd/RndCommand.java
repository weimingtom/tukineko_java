package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class RndCommand extends FECommand {
	NScripter ns = NScripter.getInstance();	
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "rnd");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] rnd");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("rnd");
		} else {
			if (ns.getArg(0).startsWith("%") == true) {
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = (int) (Math.random() * ns.nd.evalNum(ns.getArg(1)));
			} else {
				ns.error("rnd");
			}
			ns.makeLineRest(2);
		}		
	}
}
