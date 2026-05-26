package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class LsphCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "lsph");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] lsph");
		
		int i = ns.parseArgs(true);
		if ((i != 4) && (i != 5)) {
			ns.error("lsp|lsph");
		} else {
			int j = ns.nd.evalNum(ns.getArg(0));
			ns.nd.sprite[j].image = ns.nd.evalStr(ns.getArg(1));
			ns.nd.sprite[j].x = ns.nd.evalNum(ns.getArg(2));
			ns.nd.sprite[j].y = ns.nd.evalNum(ns.getArg(3));
			if (i == 5) {
				ns.nd.sprite[j].alpha = ns.nd.evalNum(ns.getArg(4));
			}
			ns.nd.sprite[j].visible = false;
		}			
	}
}
