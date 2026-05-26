package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class NumaliasCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "numalias");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] numalias");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("numalias");
		} else {
			ns.nd.numalias.put(ns.getArg(0), ns.getArg(1));
			ns.makeLineRest(2);
		}		
	}
}
