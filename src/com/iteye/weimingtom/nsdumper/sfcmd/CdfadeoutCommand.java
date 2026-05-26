package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class CdfadeoutCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "cdfadeout");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] cdfadeout");
	
		if (ns.parseArgs(true) < 1) {
			ns.error("cdfadeout");
		} else {
			ns.nd.cdfadeout = ns.nd.evalNum(ns.getArg(0));
			ns.makeLineRest(1);
		}		
	}
}
