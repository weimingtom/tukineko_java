package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class MenusetwindowCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "menusetwindow");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] menusetwindow");
		
		if (ns.parseArgs(true) < 7) {
			ns.error("menusetwindow");
		} else {
			ns.nd.menusetwindowFx = ns.nd.evalNum(ns.getArg(0));
			ns.nd.menusetwindowFy = ns.nd.evalNum(ns.getArg(1));
			ns.nd.menusetwindowSx = ns.nd.evalNum(ns.getArg(2));
			ns.nd.menusetwindowSy = ns.nd.evalNum(ns.getArg(3));
			ns.nd.menusetwindowBold = ns.nd.evalBoolean(ns.getArg(4));
			ns.nd.menusetwindowShadow = ns.nd.evalBoolean(ns.getArg(5));
			ns.nd.menusetwindowColor = ns.nd.evalColor(ns.getArg(6));
			ns.makeLineRest(7);
		}		
	}
}
