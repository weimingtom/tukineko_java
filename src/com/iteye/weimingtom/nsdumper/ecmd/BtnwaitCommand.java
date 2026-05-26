package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.entry.NsButton;
import app.boya.tukineko.parser.NScripter;

public class BtnwaitCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "btnwait");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] btnwait");

		if (ns.parseArgs(true) < 1) {
			ns.error("btnwait");
		} else if (ns.nd.btn.size() == 0) {
			ns.nd.click = false;
			do {
				try {
					Thread.sleep(100L);
				} catch (Exception e) {
				}
				if (ns.nd.click) {
					break;
				}
			} while (ns.storageState == 0);
		} else {
			ns.nd.btnVisible = true;
			ns.nd.click = false;
			int k = -1;
			NsButton localNsButton;
			while (ns.storageState == 0) {
				if (ns.nd.click == true) {
					ns.nd.click = false;
					k = -1;
					for (int j = 0; j < ns.nd.btn.size(); j++) {
						localNsButton = ns.nd.btn.elementAt(j);
						if ((localNsButton.x > ns.nd.clickX) || 
							(ns.nd.clickX >= localNsButton.x + localNsButton.width) || 
							(localNsButton.y > ns.nd.clickY) || 
							(ns.nd.clickY >= localNsButton.y + localNsButton.height)) {
							continue;
						}
						k = j;
						break;
					}
					if (ns.nd.btnSel != k) {
						ns.nd.btnSel = k;
					} else {
						if (ns.nd.btnSel >= 0) {
							break;
						}
					}
					ns.tn.paintB();
				}
				try {
					Thread.sleep(100L);
				} catch (Exception e) {

				}
			}
			if (!ns.getArg(0).startsWith("%")) {
				ns.error("btnwait");
			} else if (k >= 0) {
				localNsButton = ns.nd.btn.elementAt(k);
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = localNsButton.no;
			}
			ns.nd.btnVisible = false;
			ns.makeLineRest(1);
		}
	}
}
