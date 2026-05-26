package com.iteye.weimingtom.nsdumper.fcmd;

import app.boya.tukineko.parser.NScripter;

public class SavenumberCommand extends FCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "savenumber");
	}
	
	@Override
	public void execute() {
		debug("[FCommand] savenumber");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("savenumber");
		} else {
			ns.nd.savenumber = ns.nd.evalNum(ns.getArg(0));
			if (ns.nd.savenumber > 10) {
				ns.nd.savenumber = 10;
			}
			ns.makeLineRest(1);
		}
	}
}
