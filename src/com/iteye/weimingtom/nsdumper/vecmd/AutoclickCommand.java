package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class AutoclickCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "autoclick");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] autoclick");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("autoclick");
		} else {
			ns.nd.autoclick = ns.nd.evalNum(ns.getArg(0));
			ns.makeLineRest(1);
		}
	}
}
