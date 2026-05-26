package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class DefSpeedCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "defSpeed");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] defSpeed");
		
		if (ns.parseArgs(true) < 3) {
			ns.error("defSpeed");
		} else {
			ns.nd.defSpeedLow = ns.nd.evalNum(ns.getArg(0));
			ns.nd.defSpeedMiddle = ns.nd.evalNum(ns.getArg(1));
			ns.nd.defSpeedHigh = ns.nd.evalNum(ns.getArg(2));
			ns.makeLineRest(3);
		}		
	}
}
