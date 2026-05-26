package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class StraliasCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "stralias");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] stralias");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("stralias");
		} else {
			ns.nd.stralias.put(ns.getArg(0), ns.getArg(1));
			ns.makeLineRest(2);
		}		
	}
}
