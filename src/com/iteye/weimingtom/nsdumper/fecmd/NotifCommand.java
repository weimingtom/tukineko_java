package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class NotifCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "notif");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] notif");
		
		boolean bool = true; // ???
		int j = 1;
		int k = 0;
		int i1 = 0;
		int m = 0;
		ns.parseArgs(true);
		while (true) {
			String str = ns.getArg(k);
			if ("fchk".equals(str) == true) {
				k++;
				bool = ns.nd.fchk.containsKey(ns.nd.evalStr(ns.getArg(k)).toUpperCase());
			} else if ("lchk".equals(str) == true) {
				k++;
				bool = ns.nd.lchk.containsKey(ns.getArg(k).substring(1)
						.toUpperCase());
			} else {
				int n;
				if ((n = str.indexOf(">=")) >= 0) {
					i1 = n + 2;
					m = 0;
				} else if ((n = str.indexOf("<=")) >= 0) {
					i1 = n + 2;
					m = 1;
				} else if ((n = str.indexOf("<>")) >= 0) {
					i1 = n + 2;
					m = 2;
				} else if ((n = str.indexOf("!=")) >= 0) {
					i1 = n + 2;
					m = 2;
				} else if ((n = str.indexOf("==")) >= 0) {
					i1 = n + 2;
					m = 3;
				} else if ((n = str.indexOf("=")) >= 0) {
					i1 = n + 1;
					m = 3;
				} else if ((n = str.indexOf(">")) >= 0) {
					i1 = n + 1;
					m = 4;
				} else if ((n = str.indexOf("<")) >= 0) {
					i1 = n + 1;
					m = 5;
				}
				if (n < 0) {
					ns.error("if: " + n);
					return;
				}
				int i2 = ns.nd.evalNum(str.substring(0, n));
				int i3 = ns.nd.evalNum(str.substring(i1));
				switch (m) {
				case 0:
					bool = i2 >= i3;
					break;
				case 1:
					bool = i2 <= i3;
					break;
				case 2:
					bool = i2 != i3;
					break;
				case 3:
					bool = i2 == i3;
					break;
				case 4:
					bool = i2 > i3;
					break;
				case 5:
					bool = i2 < i3;
				}
			}
			if (bool) //notif
				j = 0;
			if ((!"&".equals(ns.getArg(k + 1))) && (!"&&".equals(ns.getArg(k + 1)))) {
				break;
			}
			k += 2;
		}
		if (j == 1) {
			ns.makeLineRest(k + 1);
		} else {
			ns.lineRest = null;
		}		
	}
}
