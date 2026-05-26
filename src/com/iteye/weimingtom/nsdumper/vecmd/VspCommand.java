package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class VspCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("vsp");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] vsp");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("vsp");
		} else {
			ns.nd.sprite[ns.nd.evalNum(ns.getArg(0))].visible = 
					(ns.nd.evalNum(ns.getArg(1)) == 1);
			ns.makeLineRest(2);
		}		
	}
}
