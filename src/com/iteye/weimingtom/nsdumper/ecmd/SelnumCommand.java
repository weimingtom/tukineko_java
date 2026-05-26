package com.iteye.weimingtom.nsdumper.ecmd;

import java.io.IOException;

import app.boya.tukineko.entry.NsSelect;
import app.boya.tukineko.parser.NScripter;

public class SelnumCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "selnum");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] selnum");
		
		if (ns.parseArgs(true) < 2) {
			ns.error("selnum");
		} else {
			if (!ns.getArg(0).startsWith("%")) {
				ns.error("selnum");
				return;
			}
			ns.nd.selnum = ns.nd.evalNum(ns.getArg(0).substring(1));
			int i = ns.getArgSize();
			int m = 0;
			while (true) {
				for (int j = m == 0 ? 1 : 0; j < i; j++) {
					ns.nd.select.addElement(new NsSelect(
							ns.getArg(j),
							Integer.toString(m++),
							ns.nd.text.getY(),
							ns.tn.putMess(ns.nd.text, ns.getArg(j), ns.nd.textcolor, false, false),
							false));
				}
				if (!ns.argCont) {
					break;
				}
				try {
					String paramString = ns.readLine();
					i = ns.parseArgs(false);
				} catch (IOException e) {
				}
			}
			if (!ns.nd.fadeFlag) {
				ns.tn.paintB();
			} else {
				ns.tn.paintF();
			}
			ns.selectWait();
		}		
	}
}
