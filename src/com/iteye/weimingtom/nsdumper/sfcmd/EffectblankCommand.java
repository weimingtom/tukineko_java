package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class EffectblankCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "effectblank");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] effectblank");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("effectblank");
		} else {
			ns.nd.effectblank = ns.nd.evalNum(ns.getArg(0));
			ns.makeLineRest(1);
		}		
	}
}
