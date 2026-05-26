package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class QuakexCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("quakex");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] quakex");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("quakex");
		} else {
			for (int i = 0; i < ns.nd.evalNum(ns.getArg(0)); i++) {
				ns.nd.quakex += 1;
				ns.tn.paintB();
				try {
					Thread.sleep(ns.nd.evalNum(ns.getArg(1)));
				} catch (Exception e) {
					
				}
			}
			ns.nd.quakex = 0;
			ns.tn.paintB();
			ns.makeLineRest(2);
		}		
	}
}
