package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class LookbackcolorCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "lookbackcolor");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] lookbackcolor");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("lookbackcolor");
		} else {
			ns.nd.lookbackcolor = ns.nd.evalColor(ns.getArg(0));
			ns.makeLineRest(1);
		}		
	}
}
