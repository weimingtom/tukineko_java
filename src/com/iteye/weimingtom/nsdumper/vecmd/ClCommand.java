package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class ClCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("cl");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] cl");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("cl");
		} else {
			if ("l".equals(ns.nd.evalStr(ns.getArg(0))) == true) {
				ns.nd.shell[0] = null;
			} else if ("c".equals(ns.nd.evalStr(ns.getArg(0))) == true) {
				ns.nd.shell[1] = null;
			} else if ("r".equals(ns.nd.evalStr(ns.getArg(0))) == true) {
				ns.nd.shell[2] = null;
			} else {
				ns.nd.shell[0] = null;
				ns.nd.shell[1] = null;
				ns.nd.shell[2] = null;
			}
			ns.tn.paintB();
			ns.makeLineRest(2);
		}		
	}
}
