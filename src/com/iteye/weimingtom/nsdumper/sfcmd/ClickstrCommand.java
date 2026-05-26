package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class ClickstrCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "clickstr");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] clickstr");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("clickstr");
		} else {
			ns.nd.clickstr = ns.nd.evalStr(ns.getArg(0));
			ns.nd.clickstrLine = ns.nd.evalNum(ns.getArg(1));
			ns.makeLineRest(2);
		}		
	}
}
