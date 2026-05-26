package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.entry.NsColor;
import app.boya.tukineko.parser.NScripter;

public class BgCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "bg");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] bg");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("bg");
		} else {
			if ("black".equals(ns.getArg(0)) == true) {
				ns.nd.bgColor = NsColor.black;
				ns.nd.bgImage = null;
			} else if ("white".equals(ns.getArg(0)) == true) {
				ns.nd.bgColor = NsColor.white;
				ns.nd.bgImage = null;
			} else if (ns.getArg(0).startsWith("#") == true) {
				ns.nd.bgColor = ns.nd.evalColor(ns.getArg(0));
				ns.nd.bgImage = null;
			} else {
				ns.nd.bgColor = null;
				ns.nd.bgImage = ns.nd.evalStr(ns.getArg(0));
			}
			ns.nd.bgEffect = ns.nd.evalNum(ns.getArg(1));
			for (int i = 0; i < 3; i++) {
				ns.nd.shell[i] = null;
			}
			ns.tn.paintB();
			ns.makeLineRest(2);
		}		
	}
}
