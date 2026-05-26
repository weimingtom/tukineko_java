package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class GosubCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "gosub");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] gosub");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("gosub");
		} else if (ns.nd.gosubPos >= 8) {
			ns.error("gosub: nesting");
		} else {
			try {
				ns.nd.gosub[ns.nd.gosubPos].retpos = ns.getFilePointer();
				ns.nd.gosub[ns.nd.gosubPos].rest = ns.lineRest;
				ns.nd.gosubPos += 1;
				ns.gotoLabel(ns.getArg(0));
			} catch (Exception e) {
				ns.error("gosub");
			}
			ns.makeLineRest(1);
		}		
	}
}
