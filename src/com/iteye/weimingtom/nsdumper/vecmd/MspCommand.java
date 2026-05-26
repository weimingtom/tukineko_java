package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class MspCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "msp");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] msp");
		
		if (ns.parseArgs(true) < 4) {
			ns.error("msp");
		} else {
			int i = ns.nd.evalNum(ns.getArg(0));
			ns.nd.sprite[i].x += ns.nd.evalNum(ns.getArg(1));
			ns.nd.sprite[i].y += ns.nd.evalNum(ns.getArg(2));
			ns.nd.sprite[i].alpha += ns.nd.evalNum(ns.getArg(3));
			ns.makeLineRest(4);
		}		
	}
}
