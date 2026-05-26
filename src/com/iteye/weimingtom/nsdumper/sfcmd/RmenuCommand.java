package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class RmenuCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "rmenu");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] rmenu");
		
		int j;
		if ((j = ns.parseArgs(true)) % 2 != 0) {
			ns.error("rmenu");
		} else {
			for (int i = 0; i < j; i += 2) {
				if ("skip".equals(ns.nd.evalStr(ns.getArg(i + 1))) == true) {
					ns.nd.rmenu[0] = ns.nd.evalStr(ns.getArg(i));
					if ((ns.nd.rotate == true)
							&& (ns.nd.rmenu[0].length() >= 5)) {
						ns.nd.rmenu[0] = ns.nd.rmenu[0].substring(0, 5);
					}
					ns.tn.popupMenuAdd(ns.nd.rmenu[0]);
				} else if ("reset".equals(ns.nd.evalStr(ns.getArg(i + 1))) == true) {
					ns.nd.rmenu[1] = ns.nd.evalStr(ns.getArg(i));
					if ((ns.nd.rotate == true)
							&& (ns.nd.rmenu[1].length() >= 5)) {
						ns.nd.rmenu[1] = ns.nd.rmenu[1].substring(0, 5);
					}
					ns.tn.popupMenuAdd(ns.nd.rmenu[1]);
				} else if ("save".equals(ns.nd.evalStr(ns.getArg(i + 1))) == true) {
					ns.nd.rmenu[2] = ns.nd.evalStr(ns.getArg(i));
					if ((ns.nd.rotate == true)
							&& (ns.nd.rmenu[2].length() >= 5)) {
						ns.nd.rmenu[2] = ns.nd.rmenu[2].substring(0, 5);
					}
					ns.tn.createMenuSave(ns.nd.rmenu[2]);
				} else if ("load".equals(ns.nd.evalStr(ns.getArg(i + 1))) == true) {
					ns.nd.rmenu[3] = ns.nd.evalStr(ns.getArg(i));
					if ((ns.nd.rotate == true)
							&& (ns.nd.rmenu[3].length() >= 5)) {
						ns.nd.rmenu[3] = ns.nd.rmenu[3].substring(0, 5);
					}
					ns.tn.createMenuLoad(ns.nd.rmenu[3]);
				} else if ("lookback"
						.equals(ns.nd.evalStr(ns.getArg(i + 1))) == true) {
					ns.nd.rmenu[4] = ns.nd.evalStr(ns.getArg(i));
					if ((ns.nd.rotate == true)
							&& (ns.nd.rmenu[4].length() >= 5)) {
						ns.nd.rmenu[4] = ns.nd.rmenu[4].substring(0, 5);
					}
					ns.tn.popupMenuAdd(ns.nd.rmenu[4]);
				} else if ("windowerase".equals(ns.nd
						.evalStr(ns.getArg(i + 1))) == true) {
					ns.nd.rmenu[5] = ns.nd.evalStr(ns.getArg(i));
					if ((ns.nd.rotate == true)
							&& (ns.nd.rmenu[5].length() >= 5))
						ns.nd.rmenu[5] = ns.nd.rmenu[5].substring(0, 5);
					ns.tn.popupMenuAdd(ns.nd.rmenu[5]);
				} else {
					ns.error("rmenu: " + ns.nd.evalStr(ns.getArg(i)));
				}
			}
		}		
	}
}
