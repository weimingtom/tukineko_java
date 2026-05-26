package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class CspCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("csp");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] csp");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("csp");
		} else {
			int i = ns.nd.evalNum(ns.getArg(0));
			if (i >= 0) {
				ns.nd.sprite[i].visible = false;
			} else {
				for (int i2 = 0; i2 < 50; i2++) {
					ns.nd.sprite[i2].visible = false;
				}
			}
			ns.makeLineRest(1);
		}		
	}
}
