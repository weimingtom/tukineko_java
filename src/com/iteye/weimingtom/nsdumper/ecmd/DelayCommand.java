package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class DelayCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "delay");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] delay");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("delay");
		} else {
			ns.tn.wait(ns.nd.evalNum(ns.getArg(0)), true);
			ns.makeLineRest(1);
		}
	}
}
