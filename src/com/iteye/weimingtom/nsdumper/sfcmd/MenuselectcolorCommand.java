package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class MenuselectcolorCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "menuselectcolor");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] menuselectcolor");
		
		if (ns.parseArgs(true) < 3) {
			ns.error("menuselectcolor");
		} else {
			ns.nd.menuselectcolorOn = ns.nd.evalColor(ns.nd
					.evalStr(ns.getArg(0)));
			ns.nd.menuselectcolorOut = ns.nd.evalColor(ns.nd
					.evalStr(ns.getArg(1)));
			ns.nd.menuselectcolorNosave = ns.nd.evalColor(ns.nd
					.evalStr(ns.getArg(2)));
			ns.makeLineRest(3);
		}		
	}
}
