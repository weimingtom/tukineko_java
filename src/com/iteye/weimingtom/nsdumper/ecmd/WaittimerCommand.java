package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class WaittimerCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "waittimer");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] waittimer");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("waittimer");
		} else {
			int i = ns.nd.evalNum(ns.getArg(0)) - ns.tn.timerRead();
			if (i > 0) {
				ns.tn.wait(i, false);
			}
			ns.makeLineRest(1);
		}		
	}
}
